package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Like;
import com.example.model.Post;
import com.example.model.User;
import com.example.model.DAO.ILikeDAO;

@Controller
@SessionAttributes("user")
public class LikesController {
	
	@Autowired
	ILikeDAO postsDAO;
	
	
	@RequestMapping(value = "/likes", method = RequestMethod.GET)
	public String like(Model model, HttpSession session) {
		if (session.getAttribute("user") != null) {
			Like like = new Like();
			model.addAttribute(like);
		}

		return "like";
	}

	 //@ModelAttribute("like");
	@RequestMapping(value = "/likes", method = RequestMethod.POST)
	public String lieked(Model model,@ModelAttribute("like") Like like, BindingResult result, HttpSession session,  RedirectAttributes redir) {
		try {
			if (!result.hasErrors() && session.getAttribute("user") != null) {
				User u = (User) session.getAttribute("user");
				System.err.println(u);
				like.getUserWhoLikedIt();
//				int id = posts.addPost(post);
//				Post p = posts.getPostById(id);
				redir.addAttribute(session.getAttribute("user"));
				return "home";

			} else {
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	
}
