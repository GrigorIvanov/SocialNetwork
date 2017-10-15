package SocialNetwork.DAO;

import java.sql.Connection;

import SocialNetwork.model.DBConnection;

public class AbstractDAO {

	private Connection con;
	
	public AbstractDAO() {
		con = DBConnection.getInstance().getConnection();
	}

	public Connection getCon() {
		
		return con;
	
	}


}
