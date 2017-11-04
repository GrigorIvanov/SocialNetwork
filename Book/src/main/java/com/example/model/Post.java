package com.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class Post {
	private int postId;
	@NotBlank
	private String content;
	private int postedBy;
	private String urlPicture;
	private List<Integer> peopleWhoLikeIt = Collections.synchronizedList(new ArrayList<Integer>());

	public Post(String content, int postedBy) {
		this.setContent(content);
		this.setPostedBy(postedBy);
	}

	public Post() {

	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", content=" + content + ", postedBy=" + postedBy + "]";
	}

	public List<Integer> getPeopleWhoLikeIt() {
		return peopleWhoLikeIt;
	}

	public Post(int postId, String content, int postedBy) {
		this(content, postedBy);
		this.postId = postId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		if (isNotNull(postId)) {
			this.postId = postId;
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if (isNotNull(content)) {
			this.content = content;
		}
	}

	public int getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(int postedBy) {
		if (postedBy != 0) {
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
