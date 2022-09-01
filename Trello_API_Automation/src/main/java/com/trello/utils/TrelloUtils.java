package com.trello.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;

public class TrelloUtils {
	
	public static String getProperty(String key) throws IOException
	{
		String value;
		FileReader fileReader = new FileReader("C:\\Users\\mithilaa\\git\\Trello_APITestingUsingRestAssured"
				+ "\\Trello_API_Automation\\src\\main\\java\\com\\trello\\configuration\\environment.properties");
		Properties p = new Properties();
		p.load(fileReader);
		value = p.getProperty(key);
		fileReader.close();
		return value;
	}
	
	public static String generateRandomBoardNames()
	{
		Faker faker = new Faker();
		Book book = faker.book();
		return "Board_" +book.title();
	}
	
	public static String generateRandomListNames()
	{
		Faker faker = new Faker();
		Book book = faker.book();
		return "List_" +book.author();
	}
	
	public static String generateRandomCardNames()
	{
		Faker faker = new Faker();
		Book book = faker.book();
		return "Card_" +book.genre();
	}
}
