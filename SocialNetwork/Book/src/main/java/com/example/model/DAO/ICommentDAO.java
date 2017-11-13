package com.example.model.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.exceptions.CommentException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.UserExeption;
import com.example.model.Comment;
import com.example.model.Post;
import com.example.model.User;

@Component
public interface ICommentDAO {
	void addComment(Comment comment) throws CommentException;

	public List<Comment> showComents(int postId) throws CommentException, UserExeption, InvalidDataException;
	void updateComment(int commentId, String content) throws CommentException;

	void deleteComment(int commentId) throws CommentException;

	// void removeComment(Comment comment);
}