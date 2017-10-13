package SocialNetwork.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO implements IUserDAO {

	private static final String SELECT_USER_BY_ID_STATEMENT = "SELECT * FROM users WHERE user_id= ?";
	private static final String DELETE_USER_STATEMENT = "DELETE FROM users WHERE user_id= ?";
	private static final String ADD_USER_STATEMENT = "INSERT INTO users VALUES (null, ? , ? , ?, ?)";

	public void addUser(User user) {
		if (user != null) {
			Connection con = DBConnection.getInstance().getConnection();

			try {
				PreparedStatement ps = con.prepareStatement(ADD_USER_STATEMENT,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getBirthDate());

				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				user.setUserId(rs.getInt(1));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void removeUser(int userId) {
		if(userId != 0){ 
			Connection con= DBConnection.getInstance().getConnection();
			try {
				PreparedStatement ps= con.prepareStatement(DELETE_USER_STATEMENT);
				ps.setInt(1, userId);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}

	public User getUserById(int userId) {
		Connection con= DBConnection.getInstance().getConnection();
		try {
			PreparedStatement ps= con.prepareStatement(SELECT_USER_BY_ID_STATEMENT);
			ps.setInt(1, userId);
			ResultSet result= ps.executeQuery();
			
			int id=result.getInt(1);
			String firstName=result.getString(2);
			String lastName=result.getString(3);
			String email=result.getString(4);
			String birthDate=result.getString(5);

			return new User(id,firstName,lastName,email,birthDate);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
		
	}

//	public User getUserByEmail(String email) {
//		Connection con= DBConnection.getInstance().getConnection();
//		try {
//			PreparedStatement ps= con.prepareStatement("SELECT * FROM users WHERE email = ?");
//			
//			return new User(firstName,lastName,email,birthDate);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} 
//	}

}
