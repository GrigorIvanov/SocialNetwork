package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.example.exceptions.InvalidDataException;
import com.example.exceptions.PostExeption;
import com.example.exceptions.UserExeption;
import com.example.model.Post;
import com.example.model.DAO.IPostDAO;
import com.oreilly.servlet.MultipartRequest;

@Controller
@SessionAttributes("user")
public class uploaddFileController extends HttpServlet {

	@Autowired
	IPostDAO posts;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(Model model,@ModelAttribute("post") Post post, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		try {
			
			model.addAttribute("post",new Post());
			if (session.getAttribute("user") != null) {
				MultipartFile file = null ;
				request.setAttribute("file", file);
				return "upload";
			}
			return "error";
		} catch (Exception e) {
			return "error";
		}

	}
	
	
	
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploaded(Model model,@ModelAttribute("post") Post post, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

		String UPLOAD_DIR = "Book"+File.separator+"src"+File.separator+"main"+File.separator+"webapp"+File.separator+"static"+File.separator+"img";
		String applicationPath = request.getServletContext().getRealPath("");
		String[] pat= applicationPath.split(File.separator+".metadata"+File.separator);
		applicationPath = pat[0];
		String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
		try {
			try {
				//@SuppressWarnings("unchecked")
//				HashMap<String, Object> posts = ((java.util.HashMap<String,Object>)((HashMap<String, Object>) model).get("post"));
//				Post post = (Post) posts.get("post");
				System.err.println(post);
				((IPostDAO) posts).addPicture(((File)request.getAttribute("file")).getName(), post);
			} catch (PostExeption | UserExeption | InvalidDataException e) {
				e.printStackTrace();
				return "error";
			}
			
			MultipartRequest m = new MultipartRequest(request, uploadFilePath);
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}  
		return "home";
		
	}

}
