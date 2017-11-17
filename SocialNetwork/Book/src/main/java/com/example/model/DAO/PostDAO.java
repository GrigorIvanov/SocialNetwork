package com.example.model.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	private static final String UPDATE_CONTENT_STATEMENT = "UPDATE Posts SET content= ? WHERE post_id= ?";
	private static final String GET_ALL_POSTS_BY_USER_STATEMENT = "SELECT * FROM Posts WHERE user_id=?";
	private static final String UPDATE_PHOTO_STATEMENT = "UPDATE Posts SET photo = ? WHERE post_id = ?";
	private static final String GET_POST_BY_ID_STATEMENT = "SELECT * FROM Posts WHERE post_id= ?";
	private static final String REMOVE_POST_STATEMENT = "DELETE FROM Posts WHERE post_id= ?";
	private static final String ADD_POST_STATEMENT = "INSERT INTO Posts VALUES (null, ? , ?,null,?)";

	@Autowired
	private IUserDAO userDao;

	public int addPost(Post post) throws PostExeption, UserExeption, InvalidDataException {
		if (post != null) {

			try {
				PreparedStatement ps = getCon().prepareStatement(ADD_POST_STATEMENT, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, post.getContent());
				ps.setInt(2, post.getPostedBy());// maybe some check for the user_id
				ps.setDate(3, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));

				ps.executeUpdate();

				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				userDao.getUserById(post.getPostedBy()).addPost(post);
				return rs.getInt(1);
			} catch (SQLException e) {
				throw new PostExeption("Can't add a new post", e);
			}
		} else {
			throw new PostExeption("The post doesnt exist");
		}
	}

	public void removePost(int postId) throws PostExeption, UserExeption, InvalidDataException {
		if (postId != 0) {
			try {
				if (getPostById(postId) != null) {
					PreparedStatement ps = getCon().prepareStatement(REMOVE_POST_STATEMENT);
					ps.setInt(1, postId);
					ps.executeUpdate();
					
					userDao.getUserById(getPostById(postId).getPostedBy()).removePost(getPostById(postId));
				}
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

			int postedBy = result.getInt(3);
			Date date = result.getDate(4);


			return new Post(id, content, postedBy, date);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Post> getAllPosts() throws PostExeption {

		try {
			PreparedStatement ps = getCon().prepareStatement("SELECT * FROM Posts");
			ResultSet result = ps.executeQuery();
			List posts = new ArrayList<Post>();
			while (result.next()) {
				int id = result.getInt("post_id");
				String content = result.getString("content");
				int user = result.getInt("user_id");
				String photo = null;
				if (result.getString("photo") != null) {
					photo = result.getString("photo");
				}
				Date date = result.getDate("date");
				Post post = new Post(id, content, user, date, photo);
				posts.add(post);
			}
			return posts;
		} catch (SQLException e) {
			throw new PostExeption("You can't get the user posts");
		}
	}

	@Override
	public List<User> getAllPeopleWhoLikeThisPost(Post post) throws PostExeption {

		List<User> likes = new ArrayList<User>();

		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement("SELECT * FROM Likes WHERE post_id=?");
			ps.setInt(1, post.getPostId());

			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = null;
				try {
					user = userDao.getUserById(rs.getInt(2));
				} catch (UserExeption e) {
					e.printStackTrace();
				} catch (InvalidDataException e) {
					e.printStackTrace();
				}

				likes.add(user);

			}

			return likes;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PostExeption("You can't see the people, who like it");
		}
	}

	@Override
	public void addPicture(String photo, Post post) throws PostExeption, UserExeption, InvalidDataException {

		try {
			userDao.getUserById(post.getPostedBy()).removePost(post);
			PreparedStatement ps = getCon().prepareStatement(UPDATE_PHOTO_STATEMENT);
			ps.setString(1, photo);
			ps.setInt(2, post.getPostId());
			ps.executeUpdate();
			userDao.getUserById(post.getPostedBy()).addPost(post);
		} catch (SQLException e) {
			throw new PostExeption("This picture can't be added to the post");
		}
	}
	@Override
	public void changeContent(String content, Post post) throws PostExeption, UserExeption, InvalidDataException {
		try {
			userDao.getUserById(post.getPostedBy()).removePost(post);
			PreparedStatement ps = getCon().prepareStatement(UPDATE_CONTENT_STATEMENT);
			ps.setString(1, content);
			ps.setInt(2, post.getPostedBy());
			ps.executeQuery();
			userDao.getUserById(post.getPostedBy()).addPost(post);
		} catch (SQLException e) {
			throw new PostExeption("The content of the post can't be changed");
		}
	}
	@Override
	public List<Post> getPostsOfUser(User user) throws PostExeption {
		try {
			PreparedStatement ps = getCon().prepareStatement(GET_ALL_POSTS_BY_USER_STATEMENT);
			ps.setInt(1, user.getUserId());
			ResultSet result = ps.executeQuery();
			List posts = new ArrayList<Post>();
			while (result.next()) {
				int id = result.getInt("post_id");
				String content = result.getString("content");
				String photo = null;
				if (result.getString("photo") != null) {
					photo = result.getString("photo");
				}
				Date date = result.getDate("date");
				Post post = new Post(id, content, user.getUserId(), date, photo);
				posts.add(post);
			}
			return posts;
		} catch (SQLException e) {
			throw new PostExeption("You can't get the user posts");
		}

	}

}