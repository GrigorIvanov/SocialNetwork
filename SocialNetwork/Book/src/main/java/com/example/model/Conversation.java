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

	public void addMember(User user) {
		if(user!=null) {
			this.members.add(user);
		}
	}
	public void removeMember(User user) {
		if(user!=null) {
			this.members.remove(user);
		}
	}
	
	public List<User> getMembers(){
		return Collections.unmodifiableList(this.members);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String text) {
		this.title = text;
	}

	public void addMessage(Message text) {
		if(text!=null) {
			this.messages.add(text);
		}
	}
	public void removeMessage(Message text) {
		if(text!=null) {
			this.messages.remove(text);
		}
	}
	
	public List<Message> getMessages(){
		return Collections.unmodifiableList(this.messages);
	}
	
	
}

