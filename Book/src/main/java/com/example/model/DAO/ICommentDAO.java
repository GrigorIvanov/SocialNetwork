package com.example.model.DAO;

import com.example.exceptions.CommentException;
import com.example.model.Comment;
import com.example.model.Post;
import com.example.model.User;

public interface ICommentDAO {
	void addComment(Comment comment) throws CommentException;

	//void removeComment(Comment comment);
}