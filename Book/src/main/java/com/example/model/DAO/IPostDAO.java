package com.example.model.DAO;

import java.util.List;

import com.example.model.Post;


public interface IPostDAO {
	void addPost(Post post);

	void removePost(int postId);

	Post getPostById(int postId);
	
	List <Post> getAllPosts ();
}
