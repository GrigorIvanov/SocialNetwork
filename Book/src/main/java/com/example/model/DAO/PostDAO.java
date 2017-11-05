package com.example.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.exceptions.InvalidDataException;
import com.example.exceptions.PostExeption;
import com.example.exceptions.UserExeption;
import com.example.model.DBConnection;
import com.example.model.Post;
import com.example.model.User;

@Component
public class PostDAO extends AbstractDAO implements IPostDAO {

	private static final String ADD_POST_WITH_PHOTO_STATEMENT = "INSERT INTO Posts VALUES (null,?,?,?)";
	private static final String UPDATE_PHOTO_STATEMENT = "UPDATE Posts SET photo = ? WHERE post_id = ?";
	private static final String GET_POST_BY_ID_STATEMENT = "SELECT * FROM Posts WHERE post_id= ?";
	private static final String REMOVE_POST_STATEMENT = "DELETE FROM Posts WHERE post_id= ?";
	private static final String ADD_POST_STATEMENT = "INSERT INTO Posts VALUES (null, ? , ? )";

	@Autowired
	private IUserDAO userDao;

	public int addPost(Post post) throws PostExeption, UserExeption, InvalidDataException {
		if (post != null) {
			if (post.getUrlPicture() == null) {
				System.out.println("asd");
				try {
					PreparedStatement ps = getCon().prepareStatement(ADD_POST_STATEMENT,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, post.getContent());
					ps.setInt(2, post.getPostedBy());// maybe some check for the user_id
					ps.executeUpdate();

					ResultSet rs = ps.getGeneratedKeys();
					rs.next();
					userDao.getUserById(post.getPostedBy()).getPosts().add(post);
					return rs.getInt(1);
				} catch (SQLException e) {
					throw new PostExeption("Can't add a new post", e);
				}
			}
			else {
				PreparedStatement ps;
				try {
					ps = getCon().prepareStatement(ADD_POST_WITH_PHOTO_STATEMENT,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, post.getContent());
					ps.setInt(2, post.getPostedBy());// maybe some check for the user_id
					ps.setString(3, post.getUrlPicture());
					ps.executeUpdate();
					ResultSet rs = ps.getGeneratedKeys();
					rs.next();
					userDao.getUserById(post.getPostedBy()).getPosts().add(post);
					return rs.getInt(1);
				} catch (SQLException e) {
					throw new PostExeption("Can't add a new post", e);
				}

				
			
			}
		} else {
			throw new PostExeption("The post doesnt exist");
		}
	}

	public void removePost(int postId) throws PostExeption, UserExeption, InvalidDataException {
		if (postId != 0) {
			try {
				PreparedStatement ps = getCon().prepareStatement(REMOVE_POST_STATEMENT);
				ps.setInt(1, postId);
				ps.executeUpdate();
				// getPostById(postId).getPostedBy().getPosts().remove(getPostById(postId));
				userDao.getUserById(getPostById(postId).getPostedBy()).getPosts().remove(getPostById(postId));
			} catch (SQLException e) {
				throw new PostExeption("Can't delete this post", e);
			}
		}
	}

	public Post getPostById(int postId) {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(GET_POST_BY_ID_STATEMENT);
			ps.setInt(1, postId);
			ResultSet result = ps.executeQuery();
			result.next();
			int id = result.getInt(1);
			String content = result.getString(2);

			// if(result.getObject(3) instanceof User){ IT GIVES SOME ERROR
			int postedBy = result.getInt(3);
			// }else{
			// User postedBy=null;
			// TODO Throw exception;
			// }

			return new Post(id, content, postedBy);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Post> getAllPosts() {
		// TODO TOBEDONE
		return null;
	}

	@Override
	public List<User> getAllPeopleWhoLikeThisPost() {

		return null;
	}

	@Override
	public void addPicture(String photo, Post post) throws PostExeption {

		try {
			PreparedStatement ps = getCon().prepareStatement(UPDATE_PHOTO_STATEMENT);
			ps.setString(1, photo);
			ps.setInt(2, post.getPostId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new PostExeption("This picture can't be added");
		}

	}

}
