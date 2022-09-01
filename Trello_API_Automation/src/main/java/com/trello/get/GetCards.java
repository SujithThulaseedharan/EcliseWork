package com.trello.get;
import com.trello.base.TrelloBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetCards extends TrelloBase{
	
	public Response getCardById(String cardId)
	{
		return RestAssured
		.given().spec(commonSpecification)
		.pathParam("id", cardId)
		.when().get("/cards/{id}")
		.then().assertThat().statusCode(200).extract().response();
	}
	
	public String getCardId(Response cardRes)
	{
		return cardRes.jsonPath().get("id");
	}
	
	public int getCardPos(Response cardRes)
	{
		return Integer.parseInt(cardRes.jsonPath().get("pos").toString());
	}
	
	public String getCardName(Response cardRes)
	{
		return cardRes.jsonPath().get("Name");
	}
	
	public Response getAllCardsFromList(String listId)
	{
		return RestAssured
		.given().spec(commonSpecification)
		.pathParam("id", listId)
		.when().get("/lists/{id}/cards")
		.then().assertThat().statusCode(200).extract().response();
	}
	

}
