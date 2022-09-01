package com.trello.create;

import com.trello.base.TrelloBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateLists extends TrelloBase{
	
	
	public Response createlistsInABoard(String boardId, String listName)
	{
		return RestAssured
		.given().spec(commonSpecification)
		.queryParam("idBoard", boardId)
		.queryParam("name", listName)
		.when().post("/lists")
		.then().assertThat().statusCode(200).extract().response();
	}

	public Response createlistsInABoardWithPosition(String boardId, String listName, int pos)
	{
		pos = 1;
		return RestAssured
		.given().spec(commonSpecification)
		.queryParam("idBoard", boardId)
		.queryParam("name", listName)
		.queryParam("pos", pos)
		.when().post("/lists")
		.then().assertThat().statusCode(200).extract().response();
	}
	
	
}
