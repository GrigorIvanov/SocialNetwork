package book.java.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Conversation {
	private int conversationId;
	private String title;
	private Map<String, User> members= new ConcurrentHashMap<String, User>();
	private List messages = new LinkedList <Message>();
	
	public Conversation(int conversationId, String text) {
		this.conversationId = conversationId;
		this.title = text;
	}

	public int getConversationId() {
		return conversationId;
	}

	public void setConversationId(int conversationId) {
		this.conversationId = conversationId;
	}

	public Map<String, User> getMembers() {
		return members;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String text) {
		this.title = text;
	}
	
}

