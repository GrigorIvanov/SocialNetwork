package com.example.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.DAO.IUserDAO;
import com.example.model.DAO.UserDAO;
import com.example.exceptions.UserExeption;
import com.example.model.User;

@Controller
@SessionAttributes("user")
//@WebServlet("/Log")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	UserDAO users;
	
	
	@RequestMapping(value="/asd",method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) {
		return "index.html";
	}
	
	@RequestMapping(value="/user",method = RequestMethod.GET)
	public String users(Model model, HttpServletRequest request) throws UserExeption {
		int id  = users.addUser(new User("Someone", "Smith", "eXtreamEmail@gmail.com", "1983-3-17", "testPass123"));
		User u = users.getUserById(id);
		 //= new User("Emil", "Uzunov", "asd@", "1992-11-7", "asd");
//		User u = users.getUserByEmail(request.getParameter(email));
		model.addAttribute(u);
//		System.out.println(request.getAttribute("firstName"));
		u.getBirthDate();
		return "users";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserDAO ud = new UserDAO();
		User user = null;
		try {
			user = ud.getUserByEmail(email);
		} catch (UserExeption e) {
			response.getWriter().println("404");
			e.printStackTrace();
		}//This will be ud.getUserByEmail(email);
		if(user.getEmail().equals(email)&&matching(password, user.getPassword())){
			response.getWriter().println("Nike");
			//Redirect to the main page.
		}else{
			response.getWriter().println("404");
			//Redirect to the same page plus error message.
		}
		
		
		
		
//		if(email.equals("asd")&&password.equals("asd")){
//			response.getWriter().println("Nike");
//		}else{
//			response.getWriter().println("404");
//		}
		
		
	}
	//remains to see if it works.
	public static boolean matching(String orig, String compare){
	    String md5 = null;
	    try{
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(compare.getBytes());
	        byte[] digest = md.digest();
	        md5 = new BigInteger(1, digest).toString(16);

	        return md5.equals(orig);

	    } catch (Exception e) {
	        return false;
	    }
	}

}
