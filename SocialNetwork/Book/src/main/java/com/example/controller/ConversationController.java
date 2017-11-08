package com.example.controller;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.exceptions.ConversationException;
import com.example.exceptions.InvalidDataException;
import com.example.model.Conversation;
import com.example.model.Message;
import com.example.model.User;
import com.example.model.DAO.IConversationDAO;
import com.example.model.DAO.IMessageDAO;
import com.example.model.DAO.UserDAO;



@Controller
public class ConversationController {

	@Autowired
	IConversationDAO conversationDAO;

	@Autowired
	IMessageDAO messeageDAO;

	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/showAllMyConversations", method = RequestMethod.GET)
	public String showConversations(HttpServletRequest request, HttpSession session, Model viewModel) {
		try {
		User user = (User) session.getAttribute("user");
		ArrayList<Conversation> myconverastions = null;
		myconverastions = (ArrayList<Conversation>) user.getConversations();
		Collection<User> friends = null;
		try {
			friends = userDAO.allFriends(user);
		} catch (InvalidDataException e) {
			e.printStackTrace();
			return "error";
		}
		if (myconverastions == null || friends == null) {
			return "error";
		} else {
			viewModel.addAttribute("list", myconverastions);
			viewModel.addAttribute("friends", friends);
			return "showAllMyConversations";
		}
		}catch(Exception e) {
			e.printStackTrace();
			return "exception";
		}
	}

	@RequestMapping(value = "/openConversation", method = RequestMethod.GET)
	public String openConversation(@ModelAttribute Message message, HttpServletRequest request, Model viewModel,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		int conversationId = Integer.parseInt(request.getParameter("conversationId"));
		
		conversationDAO.MakeConversation(user, convo);
		ArrayList<Message> messages = null;
		try {
			messages =    getMessages(conversationId);
		} catch (MessageException e) {
			e.printStackTrace();
			return "error";
		}
		if (messages == null) {
			return "error";
		} else {
			viewModel.addAttribute("listMSG", messages);
			return "openConversation";
		}
	}

	@RequestMapping(value = "/newMessage", method = RequestMethod.GET)
	public synchronized String sendMessage(@ModelAttribute Message message, HttpServletRequest request,
			HttpSession session, Model viewModel) {
		User user = (User) session.getAttribute("user");
		message.setMessageId(user.getUserId());
		try {
			messeageDAO.sendMessage(user, message, convo);
		} catch (MessageException e) {
			e.printStackTrace();
			return "error";
		}
		return "forward:openConversation";
	}

	@RequestMapping(value = "/createConversation", method = RequestMethod.GET)
	public synchronized String createConversation(@ModelAttribute Message message, HttpServletRequest request,
			HttpSession session, Model viewModel) {
		User user = (User) session.getAttribute("user");
		int friendId = Integer.parseInt(request.getParameter("friendId"));
		int conversationId;
		try {
			conversationId = conversationDAO.createConversation(friendId);
		} catch (UserException | ConversationException e) {
			e.printStackTrace();
			return "error";
		}
		try {
			conversationDAO.setConversationToUsers(user.getUserId(), conversationId);
		} catch (ConversationException e) {
			e.printStackTrace();
			return "error";
		}
		try {
			conversationDAO.setConversationToUsers(friendId, conversationId);
		} catch (ConversationException e) {
			e.printStackTrace();
			return "error";
		}
		return "forward:showAllMyConversations";
	}

}