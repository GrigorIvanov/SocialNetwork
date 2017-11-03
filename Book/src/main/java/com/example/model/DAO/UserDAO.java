
package com.example.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.example.exceptions.InvalidDataException;
import com.example.exceptions.UserExeption;
import com.example.model.DBConnection;
import com.example.model.Post;
import com.example.model.User;

@Component
public class UserDAO extends AbstractDAO implements IUserDAO {

	private static final String SELECT_USER_BY_EMAIL_STATEMENT = "SELECT * FROM users WHERE email = ?";
	private static final String INSERT_INTO_FRIENDS_STATEMENT = "INSERT INTO Friends VALUES ( ?, ?)";
	private static final String INSERT_INTO_POSTS_STATEMENT = "INSERT INTO Posts VALUES ( ?, ?, ?)";
	private static final String SELECT_USER_BY_ID_STATEMENT = "SELECT * FROM Users WHERE user_id= ?";
	private static final String DELETE_USER_STATEMENT = "DELETE FROM Users WHERE user_id= ?";
	private static final String ADD_USER_STATEMENT = "INSERT INTO Users VALUES (null, ? , ? , ?, md5(?))";

	public int addUser(User user) throws UserExeption {
		if (user != null) {
			try {
				PreparedStatement ps = getCon().prepareStatement(ADD_USER_STATEMENT, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getPassword());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				return rs.getInt(1);
			} catch (SQLException e) {
				throw new UserExeption("Can't add this user.", e);
			}
		}
		return 0;
	}

	public void removeUser(int userId) throws UserExeption {
		if (userId != 0) {
			try {
				PreparedStatement ps = getCon().prepareStatement(DELETE_USER_STATEMENT);
				ps.setInt(1, userId);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new UserExeption("Can't remove this user.", e);
			}
		}
	}

	public User getUserById(int userId) throws UserExeption, InvalidDataException {
		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_USER_BY_ID_STATEMENT);
			ps.setInt(1, userId);
			ResultSet result = ps.executeQuery();
			result.next();
			int id = result.getInt(1);
			String firstName = result.getString(2);
			String lastName = result.getString(3);
			String email = result.getString(4);
			String birthDate = result.getString(5);
			String password = result.getString(6);

			return new User(id, firstName, lastName, email, password);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserExeption("Can't find user with that ID.", e);
		}

	}

	

	 public User getUserByEmail(String email) throws UserExeption, InvalidDataException {
		 Connection con= DBConnection.getInstance().getConnection();
		 try {
			 PreparedStatement ps= con.prepareStatement(SELECT_USER_BY_EMAIL_STATEMENT);
			 ps.setString(1, email);
			 ResultSet result = ps.executeQuery();
			 result.next();
			 String firstName = result.getString(2);
			 String lastName = result.getString(3);
			 String userEmail = result.getString(1);
			 String birthDate = result.getString(5);
			 String password = result.getString(6);
			 return new User(firstName ,lastName ,userEmail  ,password);
	
		 } catch (SQLException e) {
			 e.printStackTrace();
			 throw new UserExeption("Can't find user with that email.", e);
		 }
	 }

	public void addFriend(User adder, String email) throws InvalidDataException {
		try {
			if(getUserByEmail(email).isValidEmail(email) && getUserByEmail(email)!= null){
				//we should check if he is in the DB
				PreparedStatement ps = getCon().prepareStatement(INSERT_INTO_FRIENDS_STATEMENT, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, adder.getUserId());
				ps.setInt(2, getUserByEmail(email).getUserId());
				adder.getFriendlist().add(getUserByEmail(email));
			}
		} catch (UserExeption e) {
			System.out.println("This user can't be added to your friendslist");
		} catch(SQLException e){
			System.out.println("This friend can't be added");
		}
	}
	public void addPost(User adder, Post post) {
		try {
			if(!((adder.equals(null) && post.equals(null)))){
				adder.getPosts().add(post);
				PreparedStatement ps = getCon().prepareStatement(INSERT_INTO_POSTS_STATEMENT, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, post.getPostId());
				ps.setString(2, post.getContent());
				ps.setInt(3, adder.getUserId());
			}
		} catch(SQLException e){
			System.out.println("This friend can't be added");
		}
	}

	public void removeFriend(User remover, String email) throws InvalidDataException {
		try {
			if(getUserByEmail(email).isValidEmail(email)){
				if(getUserByEmail(email).getFriendlist().contains(getUserByEmail(email))){
					remover.getFriendlist().remove(email);
					int removedFriend= getUserByEmail(email).getUserId();
			//		PreparedStatement ps = getCon().prepareStatement("DELETE FROM Friends WHERE friend_id= removedFriend ");
				}
				else{
					System.out.println("There is no such user in your friendslist");
				}
			}
		} catch (UserExeption e) {
			System.out.println("This user can't be removed from your friendslist");
		}
	}

	

}

