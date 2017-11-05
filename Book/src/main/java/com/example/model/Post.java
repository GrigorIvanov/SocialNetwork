package com.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Post {
	private int postId;
	//@NotBlank
	private MultipartFile file;


	private String content;
	private int postedBy;
	private String urlPicture;
	private List<Integer> peopleWhoLikeIt = Collections.synchronizedList(new ArrayList<Integer>());

	public Post(String content, int postedBy) {
		this.setContent(content);
		this.setPostedBy(postedBy);
	}

	
	
	public Post(String content, int postedBy, String urlPicture) {
		this.setContent(content);
		this.setPostedBy(postedBy);
		this.setUrlPicture(urlPicture);
	}



	public Post() {

	}

	public String getUrlPicture() {
		return urlPicture;
	}

	public void setUrlPicture(String urlPicture) {
			this.urlPicture = urlPicture;
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
		
			this.content = content;
		
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
	public MultipartFile getFile() {
		return file;
	}



	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
