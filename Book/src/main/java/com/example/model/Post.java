package com.example.model;

public class Post {
	private int postId;
	private String content;
	private User postedBy;
	
	public Post(String content, User postedBy) {
		this.setContent(content);
		this.setPostedBy(postedBy);
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
