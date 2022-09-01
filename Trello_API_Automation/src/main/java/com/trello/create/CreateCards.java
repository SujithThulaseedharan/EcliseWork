package com.trello.create;

import com.trello.base.TrelloBase;
import com.trello.get.GetCards;
import com.trello.get.GetLists;

import io.restassured.RestAssured;
import io.restassured.response.Response;
public class CreateCards extends TrelloBase{
	
	CreateLists createLists = new CreateLists();
	GetLists getLists = new GetLists();
	GetCards getCards = new GetCards();
	
	
	public Response createCardsInList(String listId, String nameOfCard)
	{
		return RestAssured
		.given().spec(commonSpecification)
		.queryParam("idList", listId)
		.queryParam("name", nameOfCard)
		.when().post("/cards")
		.then().assertThat().statusCode(200).extract().response();
	}
	
	public Response createCardsInABoardWithPosition(String listId, String nameOfCard, int pos)
	{
		pos = 1;
		return RestAssured
		.given().spec(commonSpecification)
		.queryParam("idList", listId)
		.queryParam("name", nameOfCard)
		.queryParam("pos", pos)
		.when().post("/cards")
		.then().assertThat().statusCode(200).extract().response();
	}
	
	public Response createListWithCustomCards(String boardId,String listId, String ...cardName)
	{
		Response cardRes = null;
		int pos = 1;
		for(int i=0;i<cardName.length;i++)
		{
		cardRes = createCardsInABoardWithPosition(listId, cardName[i], 1);
		pos = getCards.getCardPos(cardRes);
		pos++;
		}
		return cardRes;
	}
}
