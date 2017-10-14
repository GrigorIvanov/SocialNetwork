package SocialNetwork.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import SocialNetwork.model.DBConnection;
import SocialNetwork.model.Post;
import SocialNetwork.model.User;

public class PostDAO implements IPostDao {

	private static final String REMOVE_POST_STATEMENT = "DELETE FROM Posts WHERE post_id= ?";
	private static final String ADD_POST_STATEMENT = "INSERT INTO Posts VALUES (null, ? , ? )";

	public void addPost(Post post) {
		if (post != null) {
			Connection con = DBConnection.getInstance().getConnection();

			try {
				PreparedStatement ps = con.prepareStatement(ADD_POST_STATEMENT,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, post.getContent());
				ps.setObject(2, post.getPostedBy());
				

				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				post.setPostId(rs.getInt(1));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void removePost(int postId) {
		if(postId != 0){ 
			Connection con= DBConnection.getInstance().getConnection();
			try {
				PreparedStatement ps= con.prepareStatement(REMOVE_POST_STATEMENT);
				ps.setInt(1, postId);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
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

}
