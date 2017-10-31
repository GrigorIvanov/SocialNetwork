package com.example.model.DAO;

import org.springframework.stereotype.Component;

import com.example.exceptions.UserExeption;
import com.example.model.User;
@Component
public interface IUserDAO  {

	int addUser(User user) throws UserExeption;

	void removeUser(int userId) throws UserExeption;

	User getUserById(int userId) throws UserExeption;

	User getUserByEmail(String email) throws UserExeption;
	
	void addFriend(User adder, String email);
	
	void removeFriend(User remover, String email);
	
	//TODO like unlike post
}