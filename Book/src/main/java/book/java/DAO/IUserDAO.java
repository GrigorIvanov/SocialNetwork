package book.java.DAO;

import book.java.exceptions.UserExeption;
import book.java.model.User;

public interface IUserDAO  {

	int addUser(User user) throws UserExeption;

	void removeUser(int userId) throws UserExeption;

	User getUserById(int userId) throws UserExeption;

	User getUserByEmail(String email) throws UserExeption;

}