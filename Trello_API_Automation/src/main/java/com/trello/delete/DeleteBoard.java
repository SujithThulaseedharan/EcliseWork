package com.trello.delete;

import com.trello.base.TrelloBase;
import com.trello.get.GetBoards;

import io.restassured.RestAssured;

public class DeleteBoard extends TrelloBase{
	
	GetBoards getBoards = new GetBoards();
		
	public void deleteBoardById(String boardId)
	{
		RestAssured
		.given().spec(commonSpecification)
		.pathParam("id", boardId)
		.when().delete("/boards/{id}")
		.then().assertThat().statusCode(200).extract().response();
	}
	
	public void deleteAllBoards()
	{
		String[] idsOfAllBoards = getBoards.getIdOfAllBoards();
		if(idsOfAllBoards.length != 0) {
		for(int i=0;i<idsOfAllBoards.length;i++)
		{
			deleteBoardById(idsOfAllBoards[i]);
		}
		}else
		{
			System.out.println("No Boards Found!");
		}
	}
	
}
