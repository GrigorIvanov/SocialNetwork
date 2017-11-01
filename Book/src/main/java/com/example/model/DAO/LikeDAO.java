package com.example.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.exceptions.InvalidLikeException;
import com.example.model.Like;

public class LikeDAO extends AbstractDAO implements ILikeDAO {

	private static final String ADD_LIKE_STATEMENT = "INSERT INTO Likes VALUES(null, ? )";

	@Override
	public int addLike(Like like) throws InvalidLikeException {
		if (like != null) {
			if(!(like.getPost().getPeopleWhoLikeIt().containsKey(like.getUserWhoLikedIt().getEmail()))){
				like.getPost().getPeopleWhoLikeIt()
					.put(like.getUserWhoLikedIt().getEmail(), like.getUserWhoLikedIt());
			//TODO db needed ? 
				try {
					PreparedStatement ps = getCon().prepareStatement(ADD_LIKE_STATEMENT,
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1,like.getUserWhoLikedIt().getUserId());
					ps.executeUpdate();
					ResultSet rs = ps.getGeneratedKeys();
					rs.next();
					return rs.getInt(1);
				} catch (SQLException e) {
					throw new InvalidLikeException("You can't like this");
				}
				
				
			}else{
				throw new InvalidLikeException( "You have already liked it");
			}
		}
		return 0;
	}

	@Override
	public void removeLike(Like like) throws InvalidLikeException {
		if (like != null) {
			if(like.getPost().getPeopleWhoLikeIt().containsKey(like.getUserWhoLikedIt().getEmail())){
				like.getPost().getPeopleWhoLikeIt().remove(like.getUserWhoLikedIt().getEmail());
			}else{
				throw new InvalidLikeException ( "You havent liked the post");
			}
		}
	}

}
