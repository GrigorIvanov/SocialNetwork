package SocialNetwork.DAO;

import SocialNetwork.model.User;
import SocialNetwork.model.UserExeption;

public interface IUserDAO {

	int addUser(User user) throws UserExeption;

	void removeUser(int userId) throws UserExeption;

	User getUserById(int userId);

	//User getUserByEmail(String email);

}