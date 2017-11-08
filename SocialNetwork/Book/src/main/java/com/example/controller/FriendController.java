package com.example.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.exceptions.InvalidDataException;
import com.example.model.User;
import com.example.model.DAO.IUserDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@Controller
@SessionAttributes("user")
public class FriendController {

	@Autowired
	IUserDAO userDAO;


	@RequestMapping(value = "/friends", method = RequestMethod.GET)
	public String showFriends(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		Collection<User> friends = null;
		try {
			friends = userDAO.allFriends(user);
		} catch (InvalidDataException e) {
			e.printStackTrace();
			return "error";
		}
		model.addAttribute(friends);
		return "friends";
	}


	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public void searchUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String query = request.getParameter("query");
		System.err.println(query);
		if (query == null) {
			response.setContentType("text/json");
			ArrayList<User> users = null;
			try {
				users = (ArrayList<User>) userDAO.allUsers();
			} catch (InvalidDataException e) {
				response.sendRedirect("error");
				e.printStackTrace();
			}
			Gson gson = new GsonBuilder().create();
			if (users == null) {
				response.sendRedirect("error");
			} else {
				response.getWriter().println(gson.toJson(users));
			}
		} else {
			response.setContentType("text/json");
			ArrayList<User> users = null;
			try {
				users = (ArrayList<User>) userDAO.allUsers();
			} catch (InvalidDataException e) {
				response.sendRedirect("error");
				e.printStackTrace();
			}
			if (users == null) {
				response.sendRedirect("error");
			} else {
				users.removeIf((a) -> !a.getFirstName().startsWith(query));
				System.err.println(users.size());
				Gson gson = new GsonBuilder().create();
				response.getWriter().println(gson.toJson(users));
			}
		}

	}
}