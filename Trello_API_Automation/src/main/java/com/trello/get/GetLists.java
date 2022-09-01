package com.trello.get;

import java.util.HashMap;
import java.util.List;

import com.trello.base.TrelloBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetLists extends TrelloBase{

	public Response getAllListsFromBoard(String boardId)
	{
		return RestAssured
		.given().spec(commonSpecification)
		.pathParam("id", boardId)
		.when().get("/boards/{id}/lists")
		.then().assertThat().statusCode(200).extract().response();
	}
	
	public String[] getNamesOfListsFromBoard(String boardId)
	{
		Response listRes = getAllListsFromBoard(boardId);
		List<HashMap<String, String>> listOfJsons = listRes.jsonPath().getList("");
		String names[] = new String[listOfJsons.size()];
		for(int i=0;i<listOfJsons.size();i++)
		{
		names[i] = listOfJsons.get(i).get("name");
		}
		return names;
	}
	
	public int getListPosition(Response listRes)
	{
		return Integer.parseInt(listRes.jsonPath().get("pos").toString());
	}
	
	public String getListId(Response listRes)
	{
		return listRes.jsonPath().get("id");
	}
}
