package com.example.controller;

import java.io.File;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Post;
import com.example.model.DAO.IPostDAO;

@Controller
@MultipartConfig
@SessionAttributes("user")
public class UploaddFileController {

	@Autowired
	IPostDAO postDao;

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
	public String uploaded(Model model, @RequestParam("file") MultipartFile f, @ModelAttribute("post") Post post, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

//		String UPLOAD_DIR = "Book"+File.separator+"src"+File.separator+"main"+File.separator+"webapp"+File.separator+"static"+File.separator+"img";
//		String applicationPath = request.getServletContext().getRealPath("");
//		String[] pat= applicationPath.split(File.separator+".metadata"+File.separator);
//		applicationPath = pat[0];
//		String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
		String uploadFilePath = "/home/emo/Desktop";
		try {
			File f1 = new File(uploadFilePath + File.separator + f.getName() + File.separator + ".jpg");
			System.out.println(f1.getAbsolutePath());
			f1.createNewFile();
			f.transferTo(f1);
			postDao.addPicture(f.getName(), post);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}  
		return "home";
		
	}

}
