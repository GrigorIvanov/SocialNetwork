package com.example.model.DAO;

import com.example.model.Conversation;
import com.example.model.Message;
import com.example.model.User;

public interface IMessageDAO {
	void sendMessage(User sender,Message message, Conversation convo);
}
