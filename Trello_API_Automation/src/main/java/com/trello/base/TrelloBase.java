/**
 * 
 * Author : Mithila Amte
 * **/

package com.trello.base;

import java.io.IOException;

import com.trello.utils.TrelloUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class TrelloBase {

	public static RequestSpecification commonSpecification;
	
	public static void setupConnection() throws IOException {
	commonSpecification = new RequestSpecBuilder().setBaseUri(TrelloUtils.getProperty("baseUri"))
			.setContentType("application/json")
	.addQueryParam("key", TrelloUtils.getProperty("trellokey"))
	.addQueryParam("token", TrelloUtils.getProperty("trellotoken"))
	.build();		
	}

	public static void tearDown() {
		System.out.println("Test Complete");
	}
}
