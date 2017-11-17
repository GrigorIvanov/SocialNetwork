package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.exceptions.CommentException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.UserExeption;
import com.example.model.Comment;
import com.example.model.Post;
import com.example.model.User;
import com.example.model.DAO.ICommentDAO;
import com.example.model.DAO.IPostDAO;

@Controller
public class CommentController {

	@Autowired
	ICommentDAO commentDAO;
	@Autowired
	IPostDAO postDAO;

	

	@RequestMapping(value = "/showPostComments", method = RequestMethod.GET)
	public String showComments(@ModelAttribute Comment comment, @ModelAttribute Post post, HttpServletRequest request,
			Model viewModel) {
		String postId = request.getParameter("postId");
		int myPost = Integer.parseInt(postId);
		List<Comment> comments;
		System.err.println(postDAO.getPostById(myPost));

		try {
			try {
				comments = commentDAO.showComents(myPost);
				viewModel.addAttribute("commentList", comments);
				return "showAlMyComments";
			} catch (UserExeption e) {
				return "error";
			} catch (InvalidDataException e) {
				return "error";
			}
		} catch (CommentException e) {
			e.printStackTrace();
			return "error";
		}
	}

	@RequestMapping(value = "/newComment", method = RequestMethod.GET)
	public String createComment(@ModelAttribute Comment comment, Model viewModel, HttpServletRequest request,
			HttpSession session) {
	
		try {
			commentDAO.addComment(comment);
			viewModel.addAttribute("comment", comment);
		} catch (CommentException e) {
			e.printStackTrace();
			return "error";
		}
		return "showAlMyComments";
	}
}
