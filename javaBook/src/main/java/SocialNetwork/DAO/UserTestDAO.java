package SocialNetwork.DAO;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import SocialNetwork.model.DBConnection;
import SocialNetwork.model.User;
import SocialNetwork.model.UserExeption;

public class UserTestDAO {

	private UserDAO userDao = new UserDAO();
	private User testUser = new User("Someone", "Smith", "eXtreamEmail@gmail.com", "1983-3-17", "testPass123");
                         
	@Test
	public void testAddAndRemoveUser() throws UserExeption, SQLException {
		
		int id = userDao.addUser(testUser);
		ResultSet rs = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM Users WHERE email = '"+testUser.getEmail()+"';");
		//If there is user registered with that email.
		assertTrue(rs.next());
		//If there are more users registered with the same email.
		assertFalse(rs.next());
		userDao.removeUser(id);
		rs = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM Users WHERE email = '"+testUser.getEmail()+"';");
		//If the user is removed.
		assertFalse(rs.next());
	}


}
