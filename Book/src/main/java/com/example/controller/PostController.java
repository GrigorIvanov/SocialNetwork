package com.example.controller;

import java.io.File;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.validation.Valid;



import org.apache.tomcat.util.http.fileupload.FileItem;
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

	private static final String UPLOAD_DIR = "Book/src/main/webapp/static/img";
	
	@RequestMapping(value = "/post", method = RequestMethod.GET )
	public String login(Model model, HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			
			Post post = new Post();
			FileItem file = null;
			model.addAttribute(post);
			request.setAttribute("file", file);
			

		}

		return "post";
	}

	
	// @ModelAttribute("post");
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String loginFeedback(Model model, @Valid @ModelAttribute("post") Post post, BindingResult result,
			HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		User u = (User) session.getAttribute("user");
		System.out.println(u);
		try {
			if (!result.hasErrors() && session.getAttribute("user") != null) {
				post.setPostedBy(u.getUserId());
				
				
//				System.out.println(post);
				int id = posts.addPost(post);
				Post p = posts.getPostById(id);
//				
//				 // gets absolute path of the web application
//		        String applicationPath = request.getServletContext().getRealPath("");
//		        String[] pat= applicationPath.split("/.metadata/");
//		        applicationPath = pat[0];
//		        System.out.println(applicationPath);
//		        // constructs path of the directory to save uploaded file
//		        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
//		         
//		        // creates the save directory if it does not exists
//		        File fileSaveDir = new File(uploadFilePath);
//		        if (!fileSaveDir.exists()) {
//		            fileSaveDir.mkdirs();
//		        }
//		        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
//		        
//		        String fileName = null;
//		        //Get all the parts from request and write it to the file on server
//		       // for (Part part : request.getParts()) {
//		        
//		            fileName = post.getFile().getName();
//		            ((Part) post.getFile()).write(uploadFilePath + File.separator + fileName);
//		            System.out.println("asdasd");
//		      //  }
		 
		     //   request.setAttribute("message", fileName + " File uploaded successfully!");
				return "upload";
	
			} else {

				String error = result.getFieldError().getDefaultMessage().toString();
				String field = result.getFieldError().getField().toString();
				String loginErrorMessage = field + " " + error;
				model.addAttribute("loginError", loginErrorMessage);
				System.out.println(loginErrorMessage);
				return "error";
			}
			}
		 catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	
	}@RequestMapping(value = "/upload", method = RequestMethod.GET )
	public String upload(Model model, HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			
		}

		return "home";
	}
	
}
	


	