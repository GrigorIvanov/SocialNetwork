package com.example.model.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.exceptions.PostExeption;
import com.example.model.Post;
import com.example.model.User;

@Component
public interface IPostDAO {
	int addPost(Post post) throws PostExeption;

	void removePost(int postId) throws PostExeption;

	Post getPostById(int postId);
	
	List <Post> getAllPosts ();
	
	List <User> getAllPeopleWhoLikeThisPost();
}
