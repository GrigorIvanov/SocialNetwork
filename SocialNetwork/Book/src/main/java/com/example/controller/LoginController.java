package com.example.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.math.BigInteger;
import java.security.MessageDigest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.exceptions.InvalidDataException;
import com.example.exceptions.PostExeption;
import com.example.exceptions.UserExeption;
import com.example.model.User;
import com.example.model.DAO.IUserDAO;

@Controller
@SessionAttributes("user")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	IUserDAO users;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String login(Model model) {
		try {
			User user = new User();
			model.addAttribute(user);

			return "login";
		} catch (Exception e) {
			return "index";
		}

	}

	// @ModelAttribute("user");
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String loginFeedback(Model model, @Valid @ModelAttribute("user") User user, BindingResult result,
			HttpSession session, RedirectAttributes redir) throws UserExeption, PostExeption, InvalidDataException {
		try {
			System.out.println();
			if (!result.hasErrors()) {

				User u = users.getUserByEmail(user.getEmail());
				user.setEmail(u.getEmail());
				user.setFirstName(u.getFirstName());
				// user.setUserId(u.getUserId());
				user.setLastName(u.getLastName());
				user.setUserId(u.getUserId());

				users.showAllPosts(user).stream().forEach(post-> user.addPost(post));

				System.out.println(u.getUserId());
				if (matching(u.getPassword(), user.getPassword()) && session.getAttribute("user") != null) {
					System.out.println(session.getAttribute("user"));
					// redir.addAttribute("user", session.getAttribute("user"));
					return "redirect:/home";
				}
			} else {
				
				String error = result.getFieldError().getDefaultMessage().toString();
				String field = result.getFieldError().getField().toString();
				String loginErrorMessage = field + " " + error;
				model.addAttribute("loginError", loginErrorMessage);

				return "login";
			}
			return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	@RequestMapping(value = "/registrer", method = RequestMethod.GET)
	public String register(Model model) {
		try {
			User user = new User();
			model.addAttribute(user);

			String confirmPassword = "";
			model.addAttribute("confirmPassword", confirmPassword);
			return "register";
		} catch (Exception e) {
			return "error";
		}

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerFeedback(Model model, @Valid @ModelAttribute("user") User user, BindingResult result,
			HttpSession session, RedirectAttributes redir)

			throws UserExeption, PostExeption, InvalidDataException {
		try {
			if (!result.hasErrors() && session.getAttribute("user") != null) {
				User u = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
				System.out.println(u);
				int id = users.addUser(u);
				u.setUserId(id);
				users.getUserById(id);
				u.setUserId(id);

				user.setEmail(u.getEmail());
				user.setFirstName(u.getFirstName());
				user.setUserId(u.getUserId());
				user.setLastName(u.getLastName());
				user.setUserId(u.getUserId());
				System.out.println(session.getAttribute("user"));
				// redir.addAttribute("user", session.getAttribute("user"));
				return "redirect:/home";
			} else {

				String error = result.getFieldError().getDefaultMessage().toString();
				String field = result.getFieldError().getField().toString();
				String registerErrorMessage = field + " " + error;
				model.addAttribute("registerError", registerErrorMessage);
				System.out.println(registerErrorMessage);

				return "login";
			}
		} catch (Exception e) {
			return "error";
		}

	}

	/**
	 * This method is comparing two md5 Strings and returns true if they mach.
	 * 
	 * @param orig
	 *            String
	 * @param compare
	 *            String
	 * @return boolean
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
