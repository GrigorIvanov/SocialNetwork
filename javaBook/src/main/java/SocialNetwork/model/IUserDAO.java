package SocialNetwork.model;

public interface IUserDAO {

	void addUser(User user);

	void removeUser(int userId);

	User getUserById(int userId);

	//User getUserByEmail(String email);

}