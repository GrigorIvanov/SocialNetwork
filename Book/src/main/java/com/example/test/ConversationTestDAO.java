package com.example.test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.example.exceptions.ConversationException;
import com.example.exceptions.UserExeption;
import com.example.model.Conversation;
import com.example.model.User;
import com.example.model.DAO.AbstractDAO;
import com.example.model.DAO.ConversationDAO;

public class ConversationTestDAO extends AbstractDAO{

	private ConversationDAO testDAO= new ConversationDAO();
	private Conversation testConvo= new Conversation("HIRE US");
	private User testUser1=new User("Kobrata", "Pulew", "klichko@nestruwa.bg", "produljawamenapred"); 
	private User testUser2=new User("Vlado", "Klitchko", "Nqkuw@amatior.bg","silensum,sponqma");
	
	@Test
	public void testMakingConversatinAndAddingUsers() throws ConversationException, SQLException, UserExeption {
		int id= testDAO.MakeConversation(testUser1, testConvo);
		ResultSet rs = getCon().createStatement()
				.executeQuery("SELECT * FROM Conversations WHERE conversation_id = '" + id + "';");
		assertTrue(rs.next());
		assertFalse(rs.next());
		
	}
		
	@Test
	public void test() {
	}

}
