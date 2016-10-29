package com.library.app.commontests.utils;

import static com.library.app.commontests.utils.JsonTestUtils.*;

import java.net.URISyntaxException;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//@URLBASE
//This class gets the URLBASE. When you deploy the application to wildfly the first path of the url (i.e.
//http://localhost:8080/myapplication is recieved in urlBase

//resourcePath if the path of the element we are gonna access. Example is categories
public class ResourceClient {
	private URL urlBase;
	private String resourcePath;

	public ResourceClient(final URL urlBase) {
		this.urlBase = urlBase;
	}

	public ResourceClient resourcePath(final String resourcePath) {
		this.resourcePath = resourcePath;
		return this;
	}

	// post the content with name of the file. Uses helper method getRequestFromFileOrEmptyIfNullFile.
	// The helper method just parses the file as json and
	// postWithContent() method takes care of posting the entity(body)
	public Response postWithFile(final String fileName) {
		return postWithContent(getRequestFromFileOrEmptyIfNullFile(fileName));
	}

	public Response postWithContent(final String content) {
		// TODO Auto-generated method stub
		return buildClient().post(Entity.entity(content, MediaType.APPLICATION_JSON));
	}

	public Response get() {
		return buildClient().get();
	}

	// buildCLient accesses the path and request for resource using the resourcePath.
	private Builder buildClient() {
		final Client resourceClient = ClientBuilder.newClient();
		return resourceClient.target(getFullURL(resourcePath)).request();
	}

	private String getFullURL(final String resourcePath) {
		try {
			return this.urlBase.toURI() + "api/" + resourcePath;
		} catch (final URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private String getRequestFromFileOrEmptyIfNullFile(final String fileName) {
		// TODO Auto-generated method stub
		if (fileName == null) {
			return "";
		}
		return readJsonFile(fileName);
	}

}
