package com.example.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.exceptions.InvalidLikeException;
import com.example.model.Like;

public class LikeDAO extends AbstractDAO implements ILikeDAO {

	@Override
	public void addLike(Like like) throws InvalidLikeException {
		if (like != null) {
			if(!(like.getPost().getPeopleWhoLikeIt().containsKey(like.getUserWhoLikedIt().getEmail()))){
				like.getPost().getPeopleWhoLikeIt()
					.put(like.getUserWhoLikedIt().getEmail(), like.getUserWhoLikedIt());
			//TODO db needed ? 
			}else{
				throw new InvalidLikeException( "You have already liked it");
			}
		}
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
