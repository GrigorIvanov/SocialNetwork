package com.example.model;

public class Message {
	private int messageId;
	private Conversation convo;
	private User sender;
	private String content;
	
	
	public Message(int messageId, Conversation convo, User sender, String content) {
		this.messageId = messageId;
		this.convo = convo;
		this.sender = sender;
		this.content = content;
	}

	
	public User getSender() {
		return sender;
	}


	public void setSender(User string) {
		this.sender = string;
	}


	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public Conversation getConvo() {
		return convo;
	}

	public void setConvo(Conversation convo) {
		this.convo = convo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
