package com.example.model;

import org.springframework.stereotype.Component;

@Component
public class Like {
	
	private int userWhoLikedIt;
	private Post post;
	
	public Like(int userWhoLikedIt, Post post) {
		this.setUserWhoLikedIt(userWhoLikedIt);
		this.setPost(post);
	}
	public Like() {
		
	}


	public int getUserWhoLikedIt() {
		return userWhoLikedIt;
	}

	public void setUserWhoLikedIt(int userWhoLikedIt) {
		if(userWhoLikedIt!=0)
			this.userWhoLikedIt = userWhoLikedIt;
		
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		if(isNotNull(post))
			this.post = post;
	}
	public boolean isNotNull(Object o) {
		if (o.equals(null)) {
			return false;
		} else {
			return true;
		}
	}
	
}
