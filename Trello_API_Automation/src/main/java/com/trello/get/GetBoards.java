package com.trello.get;

import java.util.HashMap;
import java.util.List;

import com.trello.base.TrelloBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBoards extends TrelloBase{
	
	public Response getBoardResponse()
	{
		return RestAssured
		.given().spec(commonSpecification)
		.when().get("/boards")
		.then().assertThat().statusCode(200).extract().response();
	}

	public String getBoardid(Response createdBoardResponse)
	{
		return createdBoardResponse.jsonPath().getString("id");
	}
	
	public String getBoardName(Response createdBoardResponse)
	{
		return createdBoardResponse.jsonPath().getString("name");
	}
	
	public Response getAllBoards()
	{
		return RestAssured
				.given().spec(commonSpecification)
				.when().get("/members/me/boards")
				.then().assertThat().statusCode(200).extract().response();
	}
	
	public String[] getIdOfAllBoards()
	{
		Response boardRes = getAllBoards();
		List<HashMap<String, String>> listOfJsons = boardRes.jsonPath().getList("");
		String names[] = new String[listOfJsons.size()];
		for(int i=0;i<listOfJsons.size();i++)
		{
		names[i] = listOfJsons.get(i).get("id");
		}
		return names;
	}
	
	public String[] getNamesOfAllBoards()
	{
		Response boardRes = getAllBoards();
		List<HashMap<String, String>> listOfJsons = boardRes.jsonPath().getList("");
		String names[] = new String[listOfJsons.size()];
		for(int i=0;i<listOfJsons.size();i++)
		{
		names[i] = listOfJsons.get(i).get("name");
		}
		return names;
	}
	
	
}
