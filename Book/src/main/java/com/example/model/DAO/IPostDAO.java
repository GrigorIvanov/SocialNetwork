package com.example.model.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.exceptions.InvalidDataException;
import com.example.exceptions.PostExeption;
import com.example.exceptions.UserExeption;
import com.example.model.Post;
import com.example.model.User;

@Component
public interface IPostDAO {
	int addPost(Post post) throws PostExeption, UserExeption, InvalidDataException;

	void removePost(int postId) throws PostExeption, UserExeption, InvalidDataException;

	Post getPostById(int postId);
	
	List <Post> getAllPosts ();
	
	List <User> getAllPeopleWhoLikeThisPost();
}
