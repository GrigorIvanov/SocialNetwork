package com.example.controller;

import java.io.File;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

@Controller
@SessionAttributes("user")
public class PostController extends HttpServlet {

	@Autowired
	IPostDAO posts;

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String post(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		try {
		if (session.getAttribute("user") != null) {
			Post post = new Post();
			model.addAttribute(post);
		}
		return "post";
		}catch (Exception e) {
			return "error";
		}

	}

	// @ModelAttribute("post");
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String posted(Model model, @Valid @ModelAttribute("post") Post post, BindingResult result,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (!result.hasErrors() && session.getAttribute("user") != null) {

				

				post.setPostedBy(((User) (session.getAttribute("user"))).getUserId());
				int id = posts.addPost(post);
				Post p = posts.getPostById(id);
				((User)(session.getAttribute("user"))).addPost(p);
				// ((User)(session.getAttribute("user"))).getPosts().stream().forEach(w ->
				// System.err.println(w));
				((User)(session.getAttribute("user"))).getPosts().stream().forEach(r -> System.out.println(r));

				return "home";

			} else {

				String error = result.getFieldError().toString();
				error += "" + result.getFieldError().getDefaultMessage();
				System.err.println(error);
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	@RequestMapping(value = "/ShowAllUserPosts", method = RequestMethod.GET)
	public String posts(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		try {
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			if (!user.equals(null)) {
				request.setAttribute("posts", user.getPosts());
				//request.setAttribute("user", user);
				return "showAllPosts";

			}

		}
		return "error";
		}catch (Exception e) {
			return "error";
		}

	}

	@RequestMapping(value = "/AllPosts", method = RequestMethod.GET)
	public String allPosts(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		try {
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			if (!user.equals(null)) {
				request.setAttribute("posts", posts.getAllPosts());
				return "AllPosts";

			}

		}
		return "error";
		}catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

		}

}
