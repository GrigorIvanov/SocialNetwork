package com.example.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.DAO.IPostDAO;
import com.example.model.DAO.IUserDAO;

@Controller
@SessionAttributes("user")
// @WebServlet("/Log")
public class testController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	IUserDAO users;
	@Autowired
	IPostDAO posts;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String login(Model model,HttpServletRequest request) {
		return "test";
	}
	
	

}
