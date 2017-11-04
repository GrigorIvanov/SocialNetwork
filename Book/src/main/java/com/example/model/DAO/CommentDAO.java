package com.example.model.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.exceptions.CommentException;
import com.example.model.Comment;
import com.example.model.Post;
import com.example.model.User;

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


}
