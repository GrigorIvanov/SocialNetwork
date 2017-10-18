package javaBook;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import book.java.DAO.AbstractDAO;
import book.java.DAO.UserDAO;
import book.java.exceptions.UserExeption;
import book.java.model.User;

public class UserTestDAO extends AbstractDAO  {

	private UserDAO userDao = new UserDAO();
	private User testUser = new User("Someone", "Smith", "eXtreamEmail@gmail.com", "1983-3-17", "testPass123");

	@Test
	public void testAddAndRemoveUser() throws UserExeption, SQLException {
		int id = userDao.addUser(testUser);
		ResultSet rs = getCon().createStatement()
				.executeQuery("SELECT * FROM Users WHERE email = '" + testUser.getEmail() + "';");
		// Check if there is user registered with that email.
		assertTrue(rs.next());
		// Check if there are more users registered with the same email.
		assertFalse(rs.next());
		userDao.removeUser(id);
		rs = getCon().createStatement().executeQuery("SELECT * FROM Users WHERE email = '" + testUser.getEmail() + "';");
		// Check if the user is removed.
		assertFalse(rs.next());
	}

	@Test
	public void getUserById() throws UserExeption {
		// Create the testUser in the database
		int id = userDao.addUser(testUser);
		// Creating object from the database.
		User user = new UserDAO().getUserById(id);
		// Check if all properties are set.
		assertNotNull(user);
		assertNotEquals(user.getUserId(), 0);
		assertNotNull(user.getFirstName());
		assertNotNull(user.getFirstName());
		assertNotNull(user.getLastName());
		assertNotNull(user.getPassword());
		assertNotNull(user.getEmail());
		assertNotNull(user.getBirthDate());
		// Remove the testUser
		//userDao.removeUser(id);

	}

}
