package com.example.model.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.example.exceptions.InvalidDataException;
import com.example.exceptions.InvalidLikeException;
import com.example.exceptions.UserExeption;
import com.example.model.Like;
@Component
public class LikeDAO extends AbstractDAO implements ILikeDAO {

	//private static final String ADD_LIKE_STATEMENT = "INSERT INTO Likes VALUES(null, ? )";
	private static final String ADD_LIKE_STATEMENT = "INSERT INTO Likes VALUES(?, ?)";
	private IUserDAO userDao;
	@Override
	public void addLike(Like like) throws InvalidLikeException, UserExeption, InvalidDataException {
		if (like != null) {										//like.getuserwholikedit.getemail
			if(!(like.getPost().getPeopleWhoLikeIt().contains(userDao.getUserById(like.getUserWhoLikedIt()).getEmail()))){
				like.getPost().getPeopleWhoLikeIt()
					.add(like.getUserWhoLikedIt());
			
				try {
					PreparedStatement ps = getCon().prepareStatement(ADD_LIKE_STATEMENT);
					ps.setInt(1,like.getPost().getPostId());
					ps.setInt(2,like.getUserWhoLikedIt());
				
					ps.executeUpdate();
					
				} catch (SQLException e) {
					throw new InvalidLikeException("You can't like this");
				}
				
			}else{
				throw new InvalidLikeException( "You have already liked it");
			}
		}
	}

	@Override
	public void removeLike(Like like) throws InvalidLikeException, UserExeption, InvalidDataException {
		if (like != null) {
			if(like.getPost().getPeopleWhoLikeIt().contains(userDao.getUserById(like.getUserWhoLikedIt()).getEmail())){
				like.getPost().getPeopleWhoLikeIt().remove(userDao.getUserById(like.getUserWhoLikedIt()).getEmail());
				
			}else{
				throw new InvalidLikeException ( "You havent liked the post");
			}
		}
	}

}
