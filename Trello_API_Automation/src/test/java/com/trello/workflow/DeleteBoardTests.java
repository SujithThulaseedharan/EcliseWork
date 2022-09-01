package com.trello.workflow;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.trello.base.TrelloBase;
import com.trello.delete.DeleteBoard;
import com.trello.get.GetBoards;

public class DeleteBoardTests extends TrelloBase{
	GetBoards getboards = new GetBoards();
	DeleteBoard deleteBoard = new DeleteBoard();
	
	@BeforeTest
	public void setup() throws IOException
	{
		TrelloBase.setupConnection();
	}
	
	@Test
	public void deleteAllBoards()
	{
		deleteBoard.deleteAllBoards();
		String idOfBoards[] = getboards.getIdOfAllBoards();
		Assert.assertEquals(idOfBoards.length, 0);
	}
	
}
