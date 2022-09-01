package com.trello.workflow;
import java.io.IOException;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.trello.base.TrelloBase;
import com.trello.create.CreateBoard;
import com.trello.create.CreateCards;
import com.trello.create.CreateLists;
import com.trello.get.GetBoards;
import com.trello.get.GetCards;
import com.trello.get.GetLists;
import com.trello.utils.TrelloUtils;

import io.restassured.response.Response;

public class CreateBoardTests extends TrelloBase{
	CreateBoard createBoard = new CreateBoard();
	GetBoards getBoards = new GetBoards();
	GetLists getLists = new GetLists();
	String listName[];
	CreateLists createLists = new CreateLists();
	CreateCards createCards = new CreateCards();
	GetCards getCards = new GetCards();
	
	@BeforeTest
	public void setup() throws IOException {
		TrelloBase.setupConnection();
	}
	
	@Test
	public void createBoardWithoutDefaultLists()
	{
		String boardName = TrelloUtils.generateRandomBoardNames();
		Response createBoardRes = createBoard.createBoardWithoutDefaultLists(boardName);
		Assert.assertEquals(getBoards.getBoardName(createBoardRes), boardName);
		String boardId = getBoards.getBoardid(createBoardRes);
		String[] namesofLists = getLists.getNamesOfListsFromBoard(boardId);
		Assert.assertEquals(namesofLists.length, 0);
		
	}
	
	@Test
	public void createBoardWithDefaultLists()
	{
		String boardName = TrelloUtils.generateRandomBoardNames();
		Response createBoardRes = createBoard.createBoardWithDefaultLists(boardName);
		String boardId = getBoards.getBoardid(createBoardRes);
		Assert.assertEquals(getBoards.getBoardName(createBoardRes), boardName);
		String[] namesofLists = getLists.getNamesOfListsFromBoard(boardId);
		Assert.assertEquals("To Do", namesofLists[0]);
		Assert.assertEquals("Doing", namesofLists[1]);
		Assert.assertEquals("Done", namesofLists[2]);
	}
	
	@Test
	public void createBoardWithCustomLists()
	{
		String givenListNames[] = {TrelloUtils.generateRandomListNames(),TrelloUtils.generateRandomListNames(), TrelloUtils.generateRandomListNames()};
		String givenBoardName = TrelloUtils.generateRandomBoardNames();
		Response boardRes = createBoard.createBoardWithCustomLists(givenBoardName, givenListNames);
		Assert.assertEquals(getBoards.getBoardName(boardRes), givenBoardName);
		String actualListNames[] = getLists.getNamesOfListsFromBoard(getBoards.getBoardid(boardRes));
		Boolean presenceOfList = false;
		for(int i=0;i<givenListNames.length;i++)
		{
		presenceOfList = Arrays.asList(givenListNames).contains(actualListNames[i]);
		}
		Assert.assertTrue(presenceOfList);
	}
	
	@Test
	public void createBoardWithCustomCards()
	{
		String givenBoardName = TrelloUtils.generateRandomBoardNames();
		String[] givenCardNames = {TrelloUtils.generateRandomCardNames(), TrelloUtils.generateRandomCardNames(), TrelloUtils.generateRandomCardNames()};
		Response boardRes = createBoard.createBoardWithoutDefaultLists(givenBoardName);
		Assert.assertEquals(getBoards.getBoardName(boardRes), givenBoardName);
		String boardId = getBoards.getBoardid(boardRes);
		Response listRes = createLists.createlistsInABoard(boardId, TrelloUtils.generateRandomListNames());
		String listId = getLists.getListId(listRes);
		Response cardRes = createCards.createListWithCustomCards(boardId, listId, givenCardNames);
	}
	
	

	@AfterTest
	public void teardown()
	{
		TrelloBase.tearDown();
	}
	
}
