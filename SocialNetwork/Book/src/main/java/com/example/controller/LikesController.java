package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.exceptions.InvalidDataException;
import com.example.exceptions.InvalidLikeException;
import com.example.exceptions.PostExeption;
import com.example.exceptions.UserExeption;
import com.example.model.Like;
import com.example.model.User;
import com.example.model.DAO.ILikeDAO;
import com.example.model.DAO.IPostDAO;

@Controller
@SessionAttributes("user")
public class LikesController {

	@Autowired
	ILikeDAO likeDao;
	@Autowired
	IPostDAO postDao;

	@RequestMapping(value = "/newLike", method = RequestMethod.GET)
	public String likePost(HttpServletRequest request, HttpSession session, Model model) throws UserExeption {
		String id = request.getParameter("postId");
		int postid = Integer.parseInt(id);
		User u = (User) session.getAttribute("user");

		System.out.println(postDao.getPostById(postid));
		Like like = new Like(u.getUserId(), postDao.getPostById(postid));
		System.err.println(like);

		try {
			likeDao.clickLike(like);
		} catch (InvalidLikeException e) {
			e.printStackTrace();
			return "error";
		} catch (InvalidDataException e) {
			e.printStackTrace();
			return "error";
		}
		return "showLikes";
	}

	@RequestMapping(value = "/showAllLikes", method = RequestMethod.GET)
	public String showLikes(HttpServletRequest request, Model viewModel) throws InvalidDataException {
		String postId = request.getParameter("postId");
		int myPost = Integer.parseInt(postId);
		List<User> likes = null;
		try {
			
				likes = postDao.getAllPeopleWhoLikeThisPost(postDao.getPostById(myPost));
			
		} catch (PostExeption e) {
			e.printStackTrace();
			return "error";
		}
		if (likes == null) {
			return "error";
		} else {
			viewModel.addAttribute("likes",likes);
			likes.stream().forEach(l->System.err.println(l));
			return "showLikes";
		}
	}
}