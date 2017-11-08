package com.example.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.exceptions.CommentException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.PostExeption;
import com.example.exceptions.UserExeption;
import com.example.model.Comment;
import com.example.model.Post;
import com.example.model.User;

@Component 
public class CommentDAO extends AbstractDAO implements ICommentDAO {

	private static final String ADD_COMMENT_STATEMENT = "INSERT INTO Comments VALUES(?,?,?)";

	@Override
	public void addComment(Comment comment) throws CommentException {
		try {
			PreparedStatement ps = getCon().prepareStatement(ADD_COMMENT_STATEMENT);
			ps.setInt(1, comment.getCommentedPost().getPostId());
			ps.setString(2, comment.getContent());
			ps.setInt(3, comment.getCommenter().getUserId());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
		throw new CommentException ("You can't comment on this post" + e);
		}		
	}

	@Override
	public List<Comment> showComents(Post post) throws CommentException, UserExeption, InvalidDataException {
		try {
			PreparedStatement ps = getCon().prepareStatement( "SELECT * FROM Comments WHERE post_id=?" );
			ps.setInt(1, post.getPostId());
			ResultSet result=ps.executeQuery();
			List<Comment> comments=new ArrayList<Comment>();
			while(result.next()) {
				
				String content=result.getString("text");
				int userId=result.getInt("user_id");
				User commenter= new UserDAO().getUserById(post.getPostedBy());
						
				Comment comment= new Comment(post,content,commenter);
				comments.add(comment);
			}
			return comments;
		} catch (SQLException e) {
			throw new CommentException("You can't get the posts comments");
		}
	}
	

}
