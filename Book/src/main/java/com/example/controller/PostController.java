package com.example.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.exceptions.PostExeption;
import com.example.model.Post;
import com.example.model.User;
import com.example.model.DAO.IPostDAO;

@Controller
@SessionAttributes("user")
public class PostController extends HttpServlet {

	@Autowired
	IPostDAO posts;

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String login(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Post post = new Post();
			model.addAttribute(post);
		}
		return "post";
	}

	// @ModelAttribute("post");
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String loginFeedback(Model model, @Valid @ModelAttribute("post") Post post, BindingResult result,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (!result.hasErrors() && session.getAttribute("user") != null) {

				// String slash = File.separator;
				// String UPLOAD_DIR =
				// "Book"+slash+"src"+slash+"main"+slash+"webapp"+slash+"static"+slash+"img";
				// String applicationPath = request.getServletContext().getRealPath("");
				// String[] pat= applicationPath.split(slash+".metadata"+slash);
				// applicationPath = pat[0];
				// String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
				// String file = post.getFile().getAbsolutePath();
				// System.err.println(file);
				// System.err.println(post);
				//
				//
				//
				//
				// response.setContentType("image/jpeg");
				// try (BufferedInputStream bis = new BufferedInputStream(new
				// FileInputStream(new File(uploadFilePath)))) {
				// do {
				// int x = bis.read();
				// if ( x != -1) {
				// response.getOutputStream().write(x);
				// } else {
				// break;
				// }
				// }
				// while(true);
				// };
				// response.getOutputStream().close();

				// System.err.println(post);

				post.setPostedBy(((User) (session.getAttribute("user"))).getUserId());
				int id = posts.addPost(post);
				Post p = posts.getPostById(id);

				// ((User)(session.getAttribute("user"))).getPosts().stream().forEach(w ->
				// System.err.println(w));
				System.out.println(((User) (session.getAttribute("user"))).getPosts().get(1));

				return "home";

			} else {

				String error = result.getFieldError().toString();
				error += "" + result.getFieldError().getDefaultMessage();
				System.err.println(error);
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		if (session.getAttribute("user") != null) {

		}

		return "home";
	}

	@RequestMapping(value = "/ShowAllUserPosts", method = RequestMethod.GET)
	public String posts(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			if (!user.equals(null)) {
				request.setAttribute("posts", user.getPosts());
				request.setAttribute("user", user);
				return "showAllPosts";

			}

		}
		return "error";
	}



	@RequestMapping(value = "/AllPosts", method = RequestMethod.GET)
	public String allPosts(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws PostExeption {
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			if (!user.equals(null)) {
				
				request.setAttribute("posts", posts.getAllPosts());
				return "showAllPosts";

			}

		}
		return "error";
	}

}
