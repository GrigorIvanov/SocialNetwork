package com.example.model.DAO;

import org.springframework.stereotype.Component;

import com.example.exceptions.ConversationException;
import com.example.exceptions.UserExeption;
import com.example.model.Conversation;
import com.example.model.User;
@Component
public interface IConversationDAO {
	
	int MakeConversation(User member, Conversation convo) throws ConversationException, UserExeption;
	void AddUserToConversation(User user, Conversation convo) throws ConversationException, UserExeption;
	Conversation getConversationById(int convoId) throws UserExeption;
	void setConversationToUsers(int userId, int conversationId) throws ConversationException;
}
