package com.trello.update;

import com.trello.base.TrelloBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UpdateBoard extends TrelloBase{
	
	public Response updateBoardName(String boardId, String newBoardName)
	{
		return RestAssured
		.given().spec(commonSpecification)
		.queryParam("name", newBoardName)
		.pathParam("id", boardId)
		.when().put("boards/{id}")
		.then().extract().response();
		
	}
}
