package com.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Conversation {
	private int conversationId;
	private String title;
	private List<User> members = Collections.synchronizedList(new ArrayList<User>());
	private List<Message> messages = Collections.synchronizedList(new ArrayList<Message>());

	
	public Conversation(int conversationId, String text) {
		this.conversationId = conversationId;
		this.title = text;
	}

	public Conversation(String title) {
		this.title = title;
	}

	public int getConversationId() {
		return conversationId;
	}

	public void setConversationId(int conversationId) {
		this.conversationId = conversationId;
	}

	public List<User> getMembers() {
		return members;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String text) {
		this.title = text;
	}

	public void setMessages(List messages) {
		this.messages = messages;
	}
	
}

