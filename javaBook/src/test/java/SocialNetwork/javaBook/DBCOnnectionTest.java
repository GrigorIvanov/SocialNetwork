package SocialNetwork.javaBook;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import SocialNetwork.model.DBConnection;

public class DBCOnnectionTest {

	@Test
	public void TestConnection(){
		Connection c = DBConnection.getInstance().getConnection();
		assertNotNull(c);
		
	}
	
	@Test
	public void TestSelect() throws SQLException{
		Connection c = DBConnection.getInstance().getConnection();
		c.prepareStatement("SELECT * FROM users;");
	}

}
