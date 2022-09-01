package com.trello.workflow;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.trello.base.TrelloBase;
import com.trello.create.CreateBoard;
import com.trello.get.GetBoards;
import com.trello.update.UpdateBoard;
import com.trello.utils.TrelloUtils;

import io.restassured.response.Response;

public class UpdateBoardsTests extends TrelloBase{
	UpdateBoard updateBoard = new UpdateBoard();
	CreateBoard createBoard = new CreateBoard();
	GetBoards getBoards = new GetBoards();
	
	@BeforeMethod
	public void setup() throws IOException
	{
		TrelloBase.setupConnection();
	}
	
	@Test
	public void updateNameOfBoard()
	{
		String oldName = TrelloUtils.generateRandomBoardNames();
		Response boardRes = createBoard.createBoardWithDefaultLists(oldName);
		String boardId = getBoards.getBoardid(boardRes);
		Assert.assertEquals(getBoards.getBoardName(boardRes),oldName);
		String updatedName = TrelloUtils.generateRandomBoardNames();
		Response updatedRes = updateBoard.updateBoardName(boardId, updatedName);
		Assert.assertEquals(getBoards.getBoardName(updatedRes),updatedName);
	}
}
