
package com.example.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.exceptions.InvalidDataException;
import com.example.exceptions.UserExeption;
import com.example.model.Conversation;
import com.example.model.DBConnection;
import com.example.model.Post;
import com.example.model.User;


@Component
public class UserDAO extends AbstractDAO implements IUserDAO {

	private static final String SHOW_ALL_POSTS_STATEMENT = "SELECT * FROM Posts WHERE user_id=?";
	private static final String UPDATE_PICTURE_STATEMENT = "UPDATE Users SET photo_id= ? WHERE user_id = ? ";
	private static final String DELETE_FRIEND_STATEMENT = "DELETE FROM Friends WHERE friend_id= ? AND user_id = ? ";
	private static final String SELECT_USER_BY_EMAIL_STATEMENT = "SELECT * FROM Users WHERE email = ?";
	private static final String INSERT_INTO_FRIENDS_STATEMENT = "INSERT INTO Friends VALUES ( ?, ?)";
	private static final String SELECT_USER_BY_ID_STATEMENT = "SELECT * FROM Users WHERE user_id= ?";
	private static final String DELETE_USER_STATEMENT = "DELETE FROM Users WHERE user_id= ?";
	private static final String ADD_USER_STATEMENT = "INSERT INTO Users VALUES (null, ? , ? , ?, md5(?),?)";

	public int addUser(User user) throws UserExeption {
		if (user != null) {
			try {
				PreparedStatement ps = getCon().prepareStatement(ADD_USER_STATEMENT, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getPassword());
				ps.setString(5,"avatar.jpg");
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
			String password = result.getString(5);

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
			 int id = result.getInt(1);
			 String firstName = result.getString(2);
			 String lastName = result.getString(3);
			 String userEmail = result.getString(4);
			 String password = result.getString(5);
			 return new User(id,firstName ,lastName ,userEmail  ,password);
	
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
				adder.getFriends().add(getUserByEmail(email));
			}
		} catch (UserExeption e) {
			System.out.println("This user can't be added to your friendslist");
		} catch(SQLException e){
			System.out.println("This friend can't be added");
		}
	}
	


	public void removeFriend(User remover, String email) throws InvalidDataException {
		try {
			if(getUserByEmail(email).isValidEmail(email)){
				if(getUserByEmail(email).getFriends().contains(getUserByEmail(email))){
					remover.getFriends().remove(email);
					int removedFriend= getUserByEmail(email).getUserId();
					PreparedStatement ps = getCon().prepareStatement(DELETE_FRIEND_STATEMENT);
					
					ps.setInt(1, removedFriend);
					ps.setInt(2, remover.getUserId());
					ps.executeUpdate();	
					
				}
				else{
					System.out.println("There is no such user in your friendslist");
				}
			}
		} catch (UserExeption e) {
			System.out.println("This user can't be removed from your friendslist");
		} catch (SQLException e) {
			throw new InvalidDataException ("The sql statement is wrong");
		}
	}

	@Override
	public void changeProfilPic(String photo, User user) throws InvalidDataException {
		if(photo != null && user != null) {
			user.setProfilPic(photo);
			try {
				PreparedStatement ps = getCon().prepareStatement(UPDATE_PICTURE_STATEMENT );
				ps.setString(1,photo);
				ps.setInt(2, user.getUserId());
				ps.executeUpdate();
				
			} catch (SQLException e) {
				throw new InvalidDataException ("The photo can't be added");
			}
			
		}	
	}

	public List showAllPosts(User user) throws InvalidDataException{
		List posts = new ArrayList<Post>();
		try {
			PreparedStatement ps=getCon().prepareStatement(SHOW_ALL_POSTS_STATEMENT);
			ps.setInt(1, user.getUserId());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Post post=new Post();
				post.setPostedBy(user.getUserId());
				post.setPostId(rs.getInt("post_id"));
				post.setContent(rs.getString("content"));
				post.setDate(rs.getTimestamp("date"));
				if(rs.getString("photo") != null) {
					post.setUrlPicture(rs.getString("photo"));
				}
				posts.add(post);
			}
			return posts;
		} catch (SQLException e) {
			throw new InvalidDataException( "Mysql statement failed");
		}
		
	}

	@Override
	public void changeFirstName(User user, String firstname) throws InvalidDataException {
		try {
			PreparedStatement ps=getCon().prepareStatement("UPDATE Users SET first_name = ? WHERE user_id=?");
			ps.setString(1, firstname);
			ps.setInt(2, user.getUserId());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			throw new InvalidDataException("You can't change your firstName");
		}
		
	}
	
	@Override
	public void changeLastName(User user, String lastname) throws InvalidDataException {
		try {
			PreparedStatement ps=getCon().prepareStatement("UPDATE Users SET last_name = ? WHERE user_id=?");
			ps.setString(1, lastname);
			ps.setInt(2, user.getUserId());
			ps.executeUpdate();
		
		} catch (SQLException e) {
			throw new InvalidDataException("You can't change your firstName");
		}
		
	}

	@Override
	public List<User> allUsers() throws InvalidDataException {
		
		List users = new ArrayList<User>();
		try {
			PreparedStatement ps=getCon().prepareStatement("SELECT * FROM Users");			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user=new User();

				user.setUserId(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setPassword(rs.getString("password"));
				user.setProfilPic(rs.getString("photo_id"));
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			throw new InvalidDataException( "Mysql statement failed");
		}
		
	}
	
	

	@Override
	public List<User> allFriends(User user) throws InvalidDataException, UserExeption {
		
		List<User> friends = new ArrayList<User>();
		
		try {
			PreparedStatement ps=getCon().prepareStatement("SELECT * FROM Friends WHERE user_id=?");
			ps.setInt(1,user.getUserId() );
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User friend = this.getUserById(rs.getInt(1));
				
				
				if (!user.getFriends().contains(friend)) 
					friends.add(friend);
			}
			
			return friends;
		} catch (SQLException e) {
			throw new InvalidDataException( "Mysql statement failed");
		}
	}

	@Override
	public List<Conversation> getAllConversations(User user) throws InvalidDataException {
		List<Conversation> convos=new ArrayList<Conversation>();
		
		try {
			PreparedStatement ps=getCon().prepareStatement("SELECT * FROM Chat_user WHERE user_id=?");
			ps.setInt(1,user.getUserId() );
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Conversation convo= new Conversation();
					convo.setConversationId(rs.getInt("conversation_id" ));
					convo.setTitle(rs.getString("title"));
				convos.add(convo);
			}
			
			return convos;
		} catch (SQLException e) {
			throw new InvalidDataException( "Mysql statement failed");
		}
	}
	
	

	
}

