package com.example.model.DAO;

import com.example.exceptions.ConversationException;
import com.example.model.Conversation;
import com.example.model.User;

public interface IConversationDAO {
	
	int MakeConversation(User member, Conversation convo) throws ConversationException;
	void AddUserToConversation(User user, Conversation convo);
	
}
