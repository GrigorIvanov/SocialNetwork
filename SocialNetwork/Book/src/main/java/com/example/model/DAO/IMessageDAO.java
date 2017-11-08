package com.example.model.DAO;

import org.springframework.stereotype.Component;

import com.example.model.Conversation;
import com.example.model.Message;
import com.example.model.User;
@Component
public interface IMessageDAO {
	void sendMessage(User sender,Message message, Conversation convo);
}
