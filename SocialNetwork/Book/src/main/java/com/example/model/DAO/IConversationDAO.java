package com.example.model.DAO;

import java.sql.SQLException;

import com.example.exceptions.ConversationException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.UserExeption;
import com.example.model.Conversation;
import com.example.model.User;

public interface IConversationDAO {
	
	int MakeConversation(User member, Conversation convo) throws ConversationException, UserExeption;
	void AddUserToConversation(User user, Conversation convo) throws ConversationException, UserExeption;
	
}
