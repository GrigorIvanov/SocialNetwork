package com.example.model.DAO;

import com.example.model.Conversation;
import com.example.model.User;

public interface IConversationDAO {
	
	void MakeConversation(User member, Conversation convo);
	void AddUserToConversation(User user, Conversation convo);
	
}
