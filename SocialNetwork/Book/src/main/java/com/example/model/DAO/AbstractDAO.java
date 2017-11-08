package com.example.model.DAO;

import java.sql.Connection;

import com.example.model.DBConnection;


public class AbstractDAO {

	private Connection con;
	
	public AbstractDAO() {
		con = DBConnection.getInstance().getConnection();
	}

	public Connection getCon() {
		
		return con;
	
	}


}
