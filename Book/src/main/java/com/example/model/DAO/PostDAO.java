package com.example.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.exceptions.PostExeption;
import com.example.model.DBConnection;
import com.example.model.Post;
import com.example.model.User;
@Component
public class PostDAO extends AbstractDAO implements IPostDAO {

	private static final String REMOVE_POST_STATEMENT = "DELETE FROM Posts WHERE post_id= ?";
	private static final String ADD_POST_STATEMENT = "INSERT INTO Posts VALUES (null, ? , ? )";

	public int addPost(Post post) throws PostExeption {
		if (post != null) {
			System.out.println("asd");
			try {
				PreparedStatement ps = getCon().prepareStatement(ADD_POST_STATEMENT,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, post.getContent());
				ps.setObject(2, post.getPostedBy().getUserId());
				ps.executeUpdate();
				
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				post.getPostedBy().getPosts().add(post);
				return rs.getInt(1);
			} catch (SQLException e) {
				throw new PostExeption( "Can't add a new post",e);
			}
		}
		return 0;
	}

	public void removePost(int postId) throws PostExeption {
		if(postId != 0){ 
			try {
				PreparedStatement ps= getCon().prepareStatement(REMOVE_POST_STATEMENT);
				ps.setInt(1, postId);
				ps.executeUpdate();
				getPostById(postId).getPostedBy().getPosts().remove(getPostById(postId));
			} catch (SQLException e) {
				throw new PostExeption( "Can't delete this post",e);
			} 
		}		
	}

	public Post getPostById(int postId) {
		Connection con= DBConnection.getInstance().getConnection();
		try {
			PreparedStatement ps= con.prepareStatement("SELECT * FROM posts WHERE post_id= ?");
			ps.setInt(1, postId);
			ResultSet result= ps.executeQuery();
			
			int id=result.getInt(1);
			String content =result.getString(2);
			
			//if(result.getObject(3) instanceof User){ IT GIVES SOME ERROR 
				User postedBy=(User) result.getObject(3);
			//}else{
			//	User postedBy=null;
				//TODO Throw exception;
			//}
			
			return new Post(id,content, postedBy);
			
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

}
