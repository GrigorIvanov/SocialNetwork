package com.example.test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.exceptions.PostExeption;
import com.example.exceptions.UserExeption;
import com.example.model.Post;
import com.example.model.User;
import com.example.model.DAO.AbstractDAO;
import com.example.model.DAO.PostDAO;
import com.example.model.DAO.UserDAO;

public class PostTestDao extends AbstractDAO {

	private PostDAO testDao= new PostDAO();
	private UserDAO userDao= new UserDAO();
	private User testUser= new User(1,"Someone", "Smith", "eXtreamEmail@gmail.com", "bhbhhbbh");
	private Post testPost= new Post ("Testing is the path to success", testUser);
	
	@Test
	public void testAddAndRemovePost() throws PostExeption, SQLException, UserExeption {
		//userDao.addUser(testUser);
		int id= testDao.addPost(testPost);
		ResultSet rs=getCon().createStatement().executeQuery("SELECT * FROM Posts WHERE post_id= '" + testPost.getPostId() + "';");
		
		
		/**
		 * first we check if there is a post with this id;
		 * than if there is a post with duplicated id 
		 * and than if it was correctly removed
		 */
		
		assertTrue(rs.next());
		assertFalse(rs.next());
		testDao.removePost(id);
		assertFalse(rs.next());

	}	


	@Test
	public void testGetPostById() throws PostExeption {
		/**
		 * creating the post in the db
		 * and than extracting it from the db
		 */
		
		int id = testDao.addPost(testPost);
		Post post= testDao.getPostById(id);
		
		/**
		 * checking if the properties are what are expected
		 * and than removing it from the db
		 */
		assertNotNull(post);
		assertNotEquals(post.getPostId(),0);
		assertNotNull(post.getContent());
		assertNotNull(post.getPostedBy());
		testDao.removePost(id);
		
		
	}
	


}
