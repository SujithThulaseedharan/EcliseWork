package com.trello.create;

import com.trello.base.TrelloBase;
import com.trello.get.GetBoards;
import com.trello.get.GetLists;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateBoard extends TrelloBase{
	
	CreateLists createLists = new CreateLists();
	GetBoards getBoards = new GetBoards();
	GetLists getLists = new GetLists();
	
	public Response createBoardWithoutDefaultLists(String nameOfBoard)
	{
		Response response =
		RestAssured
		.given().queryParam("name", nameOfBoard).spec(commonSpecification)
		.queryParam("defaultLists", false)
		.when().post("/boards")
		.then().assertThat().statusCode(200).extract().response();
		return response;
	}
	
	public Response createBoardWithDefaultLists(String nameOfBoard)
	{
		Response response =
		RestAssured
		.given().queryParam("name", nameOfBoard).spec(commonSpecification)
		.queryParam("defaultLists", true)
		.when().post("/boards")
		.then().assertThat().statusCode(200).extract().response();
		return response;
	}
	
	public Response createBoardByName(String nameOfBoard)
	{
		Response response =
		RestAssured
		.given().queryParam("name", nameOfBoard).spec(commonSpecification)
		.when().post("/boards")
		.then().assertThat().statusCode(200).extract().response();
		return response;
	}
	
	public Response createBoardWithCustomLists(String nameOfBoard, String ...listName)
	{
		Response boardRes = createBoardWithoutDefaultLists(nameOfBoard);
		int pos = 1;
		for(int i=0;i<listName.length;i++)
		{
		Response listRes = createLists.createlistsInABoardWithPosition(getBoards.getBoardid(boardRes), listName[i], pos);
		pos = getLists.getListPosition(listRes);
		pos++;
		}
		return boardRes;
	}
}
