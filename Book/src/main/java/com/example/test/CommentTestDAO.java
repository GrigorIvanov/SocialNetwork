package com.example.test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.example.exceptions.CommentException;
import com.example.exceptions.PostExeption;
import com.example.exceptions.UserExeption;
import com.example.model.Comment;
import com.example.model.Post;
import com.example.model.User;
import com.example.model.DAO.AbstractDAO;
import com.example.model.DAO.CommentDAO;
import com.example.model.DAO.PostDAO;
import com.example.model.DAO.UserDAO;

public class CommentTestDAO extends AbstractDAO{

	CommentDAO testDao=new CommentDAO();
	UserDAO userDao= new UserDAO();
	PostDAO postDao=new PostDAO();
	
	User user= new User("Some", "One", "someone@abv.bg", "someonebla");
	Post post= new Post("Whats up", user);
	Comment testCom= new Comment( post, "Hire me, pls", user);
	
	@Test
	public void testWriteComment() throws UserExeption, PostExeption, CommentException, SQLException {
		userDao.addUser(user);
		postDao.addPost(post);
		testDao.addComment(testCom);
		
		ResultSet rs = getCon().createStatement()
			.executeQuery("SELECT * FROM Comments WHERE post_id = '" + post.getPostId() + "AND user_id "+ user.getUserId() + "';");
		
		assertTrue(rs.next());
		assertFalse(rs.next());
	
	}

}
