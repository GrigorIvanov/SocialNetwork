package book.java.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.java.DAO.UserDAO;
import book.java.model.User;
import book.java.model.UserExeption;


@WebServlet("/Log")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserDAO ud = new UserDAO();
		User user = null;
		try {
			user = ud.getUserByEmail(email);
		} catch (UserExeption e) {
			response.getWriter().println("404");
			e.printStackTrace();
		}//This will be ud.getUserByEmail(email);
		if(user.getEmail().equals(email)&&matching(password, user.getPassword())){
			response.getWriter().println("Nike");
			//Redirect to the main page.
		}else{
			response.getWriter().println("404");
			//Redirect to the same page plus error message.
		}
		
		
		
		
//		if(email.equals("asd")&&password.equals("asd")){
//			response.getWriter().println("Nike");
//		}else{
//			response.getWriter().println("404");
//		}
		
		
	}
	//remains to see if it works.
	public static boolean matching(String orig, String compare){
	    String md5 = null;
	    try{
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(compare.getBytes());
	        byte[] digest = md.digest();
	        md5 = new BigInteger(1, digest).toString(16);

	        return md5.equals(orig);

	    } catch (Exception e) {
	        return false;
	    }
	}

}
