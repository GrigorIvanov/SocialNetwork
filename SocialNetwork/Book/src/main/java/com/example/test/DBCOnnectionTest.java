package com.example.test;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Test;

import com.example.model.DAO.AbstractDAO;

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
