package book.java.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Reg")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 2L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String date = request.getParameter("date");
		response.getWriter().println(username);
		response.getWriter().println(password);
		response.getWriter().println(email);
		response.getWriter().println(date);
	}

}
