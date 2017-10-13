package SocialNetwork.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

	private static DBConnection instance=null;
	private Connection con=null;
	
	
	private DBConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found or failed to load");
		}
		
		final String DB_IP = "localhost"; // or maybe not ?? 
		final String DB_PORT = "3306";
		String DB_DBNAME = "javaBook";
		String DB_USER = "Grigor";
		String DB_PASS = "1";

		try{
			this.con = DriverManager.getConnection("jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_DBNAME, DB_USER, DB_PASS);
		} catch (SQLException e) {
			System.out.println("Invalid data");
		}
		
	}
	
	public static DBConnection getInstance(){
		synchronized(DBConnection.class){
			if(instance == null){
				instance = new DBConnection();
			}
		}
		return instance;
	}
	
	public Connection getConnection(){
		return con;
	}
	
	public void closeConnection(){
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("The closing has failed");
			}
		}
	}
}

