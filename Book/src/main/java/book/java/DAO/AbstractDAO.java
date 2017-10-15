package book.java.DAO;

import java.sql.Connection;

import book.java.model.DBConnection;


public class AbstractDAO {

	private Connection con;
	
	public AbstractDAO() {
		con = DBConnection.getInstance().getConnection();
	}

	public Connection getCon() {
		
		return con;
	
	}


}
