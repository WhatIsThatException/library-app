package com.library.app.category.resource;

import static com.library.app.commontests.category.CategoryForTestsRepository.*;
import static com.library.app.commontests.utils.FileTestNameUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gson.JsonObject;
import com.library.app.common.json.JsonReader;
import com.library.app.common.model.HttpCode;
import com.library.app.commontests.utils.JsonTestUtils;
import com.library.app.commontests.utils.ResourceClient;
import com.library.app.commontests.utils.ResourceDefinitions;

@RunWith(Arquillian.class)
/**
 * 
 * Arquillian creates a deployment for your deployable file in ear or war
 *
 */
public class CategoryResourceIntTest {
	@ArquillianResource
	private URL url;

	private ResourceClient resourceClient;

	private static final String PATH_RESOURCE = ResourceDefinitions.CATEGORY.getResourceName();

	// Code for archiving our application by using ShrinkWrap
	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class)
				.addPackages(true, "com.library.app") // add all the classes
				.addAsResource("persistence-integration.xml", "META-INF/persistence.xml") // add all the resources of
																							// JPA
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml") // beans.xml is used by CDI
				.setWebXML(new File("src/test/resources/web.xml")) // web.xml file is used to expose rest interfaces
				.addAsLibraries(
						Maven.resolver().resolve("com.google.code.gson:gson:2.3.1", "org.mockito:mockito-core:1.9.5")
								.withTransitivity().asFile()); // add dependency needed, gson and mockito.
																// It reads pom.xml and get the dependencies.

	}

	@Before
	public void initTestCase() {
		this.resourceClient = new ResourceClient(url);
	}

	// we can run our test within the container and we can run as a client.
	// Since we want to access our REST interfaces. SO, run as client
	@Test
	public void addValidCategoryAndFindIt() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE)
				.postWithFile(getPathFileRequest(PATH_RESOURCE, "category.json"));
		assertThat(response.getStatus(), is(equalTo(HttpCode.CREATED.getCode())));
		final Long id = JsonTestUtils.getIdFromJson(response.readEntity(String.class));

		final Response responseGet = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).get();
		assertThat(responseGet.getStatus(), is(equalTo(HttpCode.OK.getCode())));

		final JsonObject categoryAsJson = JsonReader.readAsJsonObject(responseGet.readEntity(String.class));
		assertThat(JsonReader.getStringOrNull(categoryAsJson, "name"), is(equalTo(java().getName())));

	}

}
