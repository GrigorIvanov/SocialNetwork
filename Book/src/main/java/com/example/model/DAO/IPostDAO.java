package com.example.model.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.model.Post;
import com.example.model.User;

@Component
public interface IPostDAO {
	void addPost(Post post);

	void removePost(int postId);

	Post getPostById(int postId);
	
	List <Post> getAllPosts ();
	
	List <User> getAllPeopleWhoLikeThisPost();
}
