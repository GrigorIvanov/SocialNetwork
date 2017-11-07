package com.example.model.DAO;

import java.util.List;

import com.example.exceptions.CommentException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.UserExeption;
import com.example.model.Comment;
import com.example.model.Post;
import com.example.model.User;

public interface ICommentDAO {
	void addComment(Comment comment) throws CommentException;
    List<Comment> showComents (Post post) throws CommentException, UserExeption, InvalidDataException;
    
	//void removeComment(Comment comment);
}