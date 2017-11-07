package com.example.model.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.exceptions.InvalidDataException;
import com.example.exceptions.UserExeption;
import com.example.model.Post;
import com.example.model.User;
@Component
public interface IUserDAO  {

	int addUser(User user) throws UserExeption, InvalidDataException;

	void removeUser(int userId) throws UserExeption, InvalidDataException;

	User getUserById(int userId) throws UserExeption, InvalidDataException;

	User getUserByEmail(String email) throws UserExeption, InvalidDataException;
	
	void addFriend(User adder, String email) throws InvalidDataException;
	
	void removeFriend(User remover, String email) throws InvalidDataException;
	
	void changeProfilPic(String photo, User user) throws InvalidDataException;
	
	List <Post> showAllPosts(User user) throws InvalidDataException;

	void changeFirstName(User user, String firstname) throws InvalidDataException;
	
	void changeLastName(User user, String lasttname) throws InvalidDataException;

	List <User> allUsers() throws InvalidDataException;
	
	List <User> allFriends(User user) throws InvalidDataException, UserExeption;
}