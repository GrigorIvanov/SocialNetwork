//package com.example.controller;
//
//package com.socialNet.controller;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.omg.CORBA.UserException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.example.model.User;
//import com.example.model.DAO.IUserDAO;
//
//
//
//@Controller
//public class FriendController {
//
//	@Autowired
//	IUserDAO userDAO;
//
//	
//
//	@RequestMapping(value = "/search", method = RequestMethod.GET)
//	public void searchUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String query = request.getParameter("query");
//		System.err.println(query);
//		if (query == null) {
//			response.setContentType("text/json");
//			ArrayList<User> users = null;
//			try {
//				users = userDAO.getAllUsers();
//			} catch (UserException e) {
//				e.printStackTrace();
//				response.sendRedirect("error");
//			}
//			Gson gson = new GsonBuilder().create();
//			if (users == null) {
//				response.sendRedirect("error");
//			} else {
//				response.getWriter().println(gson.toJson(users));
//			}
//		} else {
//			response.setContentType("text/json");
//			ArrayList<User> users = null;
//			try {
//				users = userDAO.getAllUsers();
//			} catch (UserException e) {
//				e.printStackTrace();
//				response.sendRedirect("error");
//			}
//			if (users == null) {
//				response.sendRedirect("error");
//			} else {
//				users.removeIf((a) -> !a.getFirstName().startsWith(query));
//				System.err.println(users.size());
//				Gson gson = new GsonBuilder().create();
//				response.getWriter().println(gson.toJson(users));
//			}
//		}
//
//	}
//}