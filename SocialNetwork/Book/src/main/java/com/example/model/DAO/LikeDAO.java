package com.example.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.LinkException;

import org.springframework.stereotype.Component;

import com.example.exceptions.InvalidDataException;
import com.example.exceptions.InvalidLikeException;
import com.example.exceptions.UserExeption;
import com.example.model.Like;

@Component
public class LikeDAO extends AbstractDAO implements ILikeDAO {

	private static final String UNLIKE_STATEMENT = "DELETE FROM Likes WHERE post_id=? AND user_id= ?";
	private static final String CHECK_IF_USER_LIKED_IT_STATEMENT = "SELECT * FROM likes WHERE post_id=? and user_id= ? ";
	private static final String ADD_LIKE_STATEMENT = "INSERT INTO Likes VALUES(?, ?)";
	
	
	
	@Override
	public void clickLike(Like like) throws InvalidLikeException, UserExeption, InvalidDataException {
		try {
			PreparedStatement check = getCon().prepareStatement(CHECK_IF_USER_LIKED_IT_STATEMENT);
			check.setInt(1, like.getPost().getPostId());
			check.setInt(2, like.getUserWhoLikedIt());
			ResultSet result =check.executeQuery();
			int likeId=0;
			if(result.next()) {
				likeId=result.getInt(1);
			}
			
			if (likeId == 0) {
				PreparedStatement add = getCon().prepareStatement(ADD_LIKE_STATEMENT);
				add.setInt(1, like.getPost().getPostId());
				add.setInt(2, like.getUserWhoLikedIt());
				add.executeUpdate();
			}else {
				PreparedStatement remove=getCon().prepareStatement(UNLIKE_STATEMENT);
				remove.setInt(1,like.getPost().getPostId());
				remove.setInt(2,like.getUserWhoLikedIt());
				remove.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			throw new InvalidLikeException("There is a problem with the sql requsts");
		}
		
		
	}
	
}
