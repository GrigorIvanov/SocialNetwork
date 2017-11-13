package com.example.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.exceptions.CommentException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.UserExeption;
import com.example.model.Comment;
import com.example.model.User;

@Component
public class CommentDAO extends AbstractDAO implements ICommentDAO {

	private static final String ADD_COMMENT_STATEMENT = "INSERT INTO Comments VALUES(?,?,?)";
	private static final String DELETE_COMMENT_BY_ID_SQL = "DELETE FROM Comments WHERE comment_id=?";
	private static final String UPDATE_COMMENT_BY_ID_SQL = "UPDATE Comments SET text=? WHERE comment_id=?";
	@Autowired
	IPostDAO postDAO;

	@Override
	public void addComment(Comment comment) throws CommentException {
		try {
			PreparedStatement ps = getCon().prepareStatement(ADD_COMMENT_STATEMENT);
			ps.setInt(1, comment.getCommentedPost().getPostId());
			ps.setString(2, comment.getContent());
			ps.setInt(3, comment.getCommenter().getUserId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new CommentException("You can't comment on this post" + e);
		}
	}

	@Override
	public List<Comment> showComents(int postId) throws CommentException, UserExeption, InvalidDataException {
		try {
			PreparedStatement ps = getCon().prepareStatement("SELECT * FROM Comments WHERE post_id=?");
			ps.setInt(1, postId);
			ResultSet result = ps.executeQuery();
			List<Comment> comments = new ArrayList<Comment>();
			while (result.next()) {
				Comment comment = new Comment();
				comment.setCommentedPost(postDAO.getPostById(postId));
				comment.setContent(result.getString(2));
				User commenter = new UserDAO().getUserById(result.getInt(3));
				comment.setCommenter(commenter);
				comments.add(comment);
			}
			return comments;
		} catch (SQLException e) {
			throw new CommentException("You can't get the posts comments");
		}
	}

	public void updateComment(int id, String content) throws CommentException {
		try {
			PreparedStatement ps = getCon().prepareStatement(UPDATE_COMMENT_BY_ID_SQL);
			ps.setString(1, content);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new CommentException("Comment can't be edited right now, please try again later.", e);
		}
	}

	public void deleteComment(int commentId) throws CommentException {
		try {
			PreparedStatement ps = getCon().prepareStatement(DELETE_COMMENT_BY_ID_SQL);
			ps.setInt(1, commentId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new CommentException("Comment can't be deleted right now, please try again later.", e);
		}

	}

}
