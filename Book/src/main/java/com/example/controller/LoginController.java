package com.example.controller;

import java.math.BigInteger;
import java.security.MessageDigest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.exceptions.InvalidDataException;
import com.example.exceptions.PostExeption;
import com.example.exceptions.UserExeption;
import com.example.model.Post;
import com.example.model.User;
import com.example.model.DAO.IPostDAO;
import com.example.model.DAO.IUserDAO;

@Controller
@SessionAttributes("user")
// @WebServlet("/Log")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	IUserDAO users;
	@Autowired
	IPostDAO posts;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String login(Model model) {
		User user = new User();
		model.addAttribute(user);

		return "login";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String loginFeedback(Model model, @Valid @ModelAttribute("user") User user, BindingResult result )
			throws UserExeption, PostExeption, InvalidDataException {
		if (!result.hasErrors()) {
			
			User u = users.getUserByEmail(user.getEmail());
			if (matching(u.getPassword(), user.getPassword())) {
				
				return "home";
			}
		}else {
		

			String error = result.getFieldError().getDefaultMessage().toString();
			String field = result.getFieldError().getField().toString();
			String loginErrorMessage = field+" "+error;
			model.addAttribute("loginError", loginErrorMessage);
			
			
		return "login";
		}
		return "error";
	}
	
	@RequestMapping(value = "/registrer", method = RequestMethod.GET)
	public String register(Model model) {
		User user = new User();
		model.addAttribute(user);
		
		String confirmPassword = "";
		model.addAttribute("confirmPassword", confirmPassword);
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerFeedback(Model model, @Valid @ModelAttribute("user") User user, BindingResult result,HttpServletRequest request)
			throws UserExeption, PostExeption, InvalidDataException {
			String pas = request.getParameter("confirmPassword");
			System.out.println(pas);
		if (!result.hasErrors()) {
			User u = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
			System.out.println(u);
			int id = users.addUser(u);
			u.setUserId(id);
			return "home";
		} else {
			
			String error = result.getFieldError().getDefaultMessage().toString();
			String field = result.getFieldError().getField().toString();
			String registerErrorMessage = field+" "+error;
			model.addAttribute("registerError", registerErrorMessage);
			System.out.println(registerErrorMessage);
			
			return "login";
		}
	}

//	@RequestMapping(value = "/user", method = RequestMethod.GET)
//	public String users(Model model, HttpServletRequest request)
//			throws UserExeption, PostExeption, InvalidDataException {
//		int id = users.addUser(new User("Someone", "Smith", "eXtreamEmail@gmail.com", "testPass123"));
//		User u = users.getUserById(id);
//		posts.addPost(new Post("something", u));
//		model.addAttribute(u);
//		model.addAttribute(u.getPosts());
//		return "users";
//	}

	/**
	 * This method is comparing two md5 Strings and returns true if they mach.
	 * @param orig
	 * String
	 * @param compare
	 * String
	 * @return
	 * boolean
	 */
	public static boolean matching(String orig, String compare) {
		String md5 = null;
		try {
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
