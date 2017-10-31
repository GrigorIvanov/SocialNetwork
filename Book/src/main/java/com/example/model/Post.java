package com.example.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Post {
	private int postId;
	private String content;
	private User postedBy;
	private Map <String, User> peopleWhoLikeIt = new ConcurrentHashMap();
	
	
	public Post(String content, User postedBy) {
		this.setContent(content);
		this.setPostedBy(postedBy);
	}
	

	

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", content=" + content + ", postedBy=" + postedBy + "]";
	}

	public Map<String, User> getPeopleWhoLikeIt() {
		return peopleWhoLikeIt;
	}
	
	public Post(int postId, String content, User postedBy) {
		this(content,postedBy);
		this.postId = postId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		if(isNotNull(postId)){
			this.postId = postId;
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if(isNotNull(content)){
			this.content = content;
		}
	}

	public User getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(User postedBy) {
		if(isNotNull(postedBy)){
			this.postedBy = postedBy;
		}
	}
	public boolean isNotNull(Object o) {
		if (o.equals(null)) {
			return false;
		} else {
			return true;
		}
	}
	
	
}
