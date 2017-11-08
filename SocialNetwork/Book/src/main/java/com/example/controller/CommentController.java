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
import com.example.model.User;
import com.example.model.DAO.ICommentDAO;
import com.example.model.DAO.IPostDAO;

@Controller
public class CommentController {

	@Autowired
	ICommentDAO commentDAO;
	@Autowired
	IPostDAO postDAO;

	@RequestMapping(value = "/newComment", method = RequestMethod.GET)
	public String createComment(@ModelAttribute Comment comment, HttpServletRequest request, HttpSession session) {
		int postId = Integer.parseInt(request.getParameter("postId"));
		User user = (User) session.getAttribute("user");
		try {
			commentDAO.addComment(comment);
		} catch (CommentException e) {
			e.printStackTrace();
			return "error";
		}
		return "forward:showAlMyComments";
	}

	@RequestMapping(value = "/showPostComments", method = RequestMethod.GET)
	public String showComments(@ModelAttribute Comment comment, HttpServletRequest request, Model viewModel) {
		String postId = request.getParameter("postId");
		int myPost = Integer.parseInt(postId);
		List<Comment> comments;
		try {
			try {
				comments = commentDAO.showComents(postDAO.getPostById(myPost));
				viewModel.addAttribute("commentList",comments);
				return "showAlMyComments";
			} catch (UserExeption e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (CommentException e) {
			e.printStackTrace();
			return "error";
		}
		return "showAlMyComments";
	}

	@RequestMapping(value = "/editcomment", method = RequestMethod.GET)
	public String editComment(HttpServletRequest request, Model viewModel) {
		int postId = Integer.parseInt(request.getParameter("postId"));
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		String content = request.getParameter("content");
		try {
			commentDAO.updateComment(commentId, content);
		} catch (CommentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "forward:showPostComments";
	}

	@RequestMapping(value = "/deletecomment", method = RequestMethod.GET)
	public String deleteComment(HttpServletRequest request, Model viewModel) {
		int postId = Integer.parseInt(request.getParameter("postId"));
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		try {
			commentDAO.deleteComment(commentId);
		} catch (CommentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "forward:showAlMyComments";
	}
}
