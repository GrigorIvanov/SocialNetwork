package com.example.controller;

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

import com.example.model.Post;
import com.example.model.User;
import com.example.model.DAO.IPostDAO;
import com.example.model.DAO.IUserDAO;

@Controller
@SessionAttributes("user")
public class PostController {

	@Autowired
	IPostDAO posts;
	@Autowired
	IUserDAO users;
	
	
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String login(Model model, HttpSession session) {
		if (session.getAttribute("user") != null) {
			Post post = new Post();
			model.addAttribute(post);
		}

		return "post";
	}

	// @ModelAttribute("post");
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String loginFeedback(Model model, @Valid @ModelAttribute("post") Post post, BindingResult result,
			HttpSession session) {
		try {
			if (!result.hasErrors() && session.getAttribute("user") != null) {
				User u = ((User)session.getAttribute("user"));
				User p  = users.getUserById(u.getUserId());
				System.err.println(p);
				post.setPostedBy(p);
				int id = posts.addPost(post);
				Post w = posts.getPostById(id);
				
				

				System.err.println(w);
				System.err.println(post.getPostId());
				System.err.println(post.getContent());
				return "home";

			} else {

				String error = result.getFieldError().getDefaultMessage().toString();
				String field = result.getFieldError().getField().toString();
				String loginErrorMessage = field + " " + error;
				model.addAttribute("loginError", loginErrorMessage);

				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
