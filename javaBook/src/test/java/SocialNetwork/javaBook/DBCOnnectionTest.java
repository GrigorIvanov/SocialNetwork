package SocialNetwork.javaBook;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import SocialNetwork.DAO.AbstractDAO;
import SocialNetwork.model.DBConnection;

public class DBCOnnectionTest extends AbstractDAO {

	@Test
	public void TestConnection(){
		assertNotNull(getCon());
		
	}
	
	@Test
	public void TestSelect() throws SQLException{
		getCon().prepareStatement("SELECT * FROM users;");
	}

}
