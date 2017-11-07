package com.example.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.DAO.IPostDAO;

@Controller
@SessionAttributes("user")
public class uploaddFileController extends HttpServlet {

	@Autowired
	IPostDAO posts;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		try {
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
	public String uploaded(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (session.getAttribute("user") != null) {
				
				response.setContentType("image/jpeg,image/png");
				
				File file = (File) request.getAttribute("file");
				System.out.println(file);
				
				String slash = File.separator;
				String UPLOAD_DIR = "Book" + slash + "src" + slash + "main" + slash + "webapp" + slash + "static"
						+ slash + "img";
				String applicationPath = request.getServletContext().getRealPath("");
				String[] pat = applicationPath.split(slash + ".metadata" + slash);
				applicationPath = pat[0];
				String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
				
				
				String contentType = request.getContentType();
				if ((contentType.indexOf("multipart/form-data") >= 0)) {
				      DiskFileItemFactory factory = new DiskFileItemFactory();
				      
				    
				     
				      factory.setRepository(new File("c:\\temp"));

				     
				      ServletFileUpload upload = new ServletFileUpload(factory);
				      
				     
				     
				      
				      try { 
				         // Parse the request to get file items.
				         List fileItems = upload.parseRequest((RequestContext) request);

				         // Process the uploaded file items
				         Iterator i = fileItems.iterator();

				        
				         
				         while ( i.hasNext () ) {
				            FileItem fi = (FileItem)i.next();
				            if ( !fi.isFormField () ) {
				               // Get the uploaded file parameters
				               String fieldName = fi.getFieldName();
				               String fileName = fi.getName();
				               boolean isInMemory = fi.isInMemory();
				               long sizeInBytes = fi.getSize();
				            
				               // Write the file
				               if( fileName.lastIndexOf("\\") >= 0 ) {
				                  file = new File( uploadFilePath + 
				                  fileName.substring( fileName.lastIndexOf("\\"))) ;
				               } else {
				                  file = new File( uploadFilePath + 
				                  fileName.substring(fileName.lastIndexOf("\\")+1)) ;
				               }
				               fi.write( file ) ;
				               
				               
				            }
				         }
				        
				      } catch(Exception ex) {
				         System.out.println(ex);
				      }
				   } else {
				      
				   }
				
//				InputStream io = file.getInputStream();
//
//				
//
//				response.setContentType(uploadFilePath);
//				try (BufferedInputStream bis = new BufferedInputStream(io)) {
//					do {
//						int x = bis.read();
//						if (x != -1) {
//							response.getOutputStream().write(x);
//						} else {
//							break;
//						}
//					} while (true);
//				}
//				;
//				response.getOutputStream().close();

				return "showAllPosts";
			}
			return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

}
