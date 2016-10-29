package com.library.app.commontests.utils;

import org.junit.Ignore;

@Ignore
// Used to hold the name of the resource that we will be accessing from our arquillian tests. The path is categoris that
// is defined in CategoryResource.Java as @PAth annotation.
public enum ResourceDefinitions {
	CATEGORY("categories");

	private String resourceName;

	private ResourceDefinitions(final String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceName() {
		return resourceName;
	}

}
