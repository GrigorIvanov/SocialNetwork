package SocialNetwork.javaBook;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import SocialNetwork.DAO.UserDAO;
import SocialNetwork.model.DBConnection;
import SocialNetwork.model.User;
import SocialNetwork.model.UserExeption;

public class UserTestDAO {

	private UserDAO userDao = new UserDAO();
	private User testUser = new User("Someone", "Smith", "eXtreamEmail@gmail.com", "1983-3-17", "testPass123");
	Connection con = DBConnection.getInstance().getConnection();

	@Test
	public void testAddAndRemoveUser() throws UserExeption, SQLException {
		int id = userDao.addUser(testUser);
		ResultSet rs = con.createStatement()
				.executeQuery("SELECT * FROM Users WHERE email = '" + testUser.getEmail() + "';");
		// Check if there is user registered with that email.
		assertTrue(rs.next());
		// Check if there are more users registered with the same email.
		assertFalse(rs.next());
		userDao.removeUser(id);
		rs = con.createStatement().executeQuery("SELECT * FROM Users WHERE email = '" + testUser.getEmail() + "';");
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
		assertNotEquals(user.getFirstName(), "");
		assertNotEquals(user.getLastName(), "");
		assertNotEquals(user.getPassword(), "");
		assertNotEquals(user.getEmail(), "");
		assertNotEquals(user.getBirthDate(), null);
		// Remove the testUser
		userDao.removeUser(id);

	}

}
