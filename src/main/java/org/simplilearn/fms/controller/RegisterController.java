package org.simplilearn.fms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.simplilearn.fms.entities.User;
import org.simplilearn.fms.service.IUserService;
import org.simplilearn.fms.service.UserService;
import org.simplilearn.fms.utilities.Utility;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService userService = new UserService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		User user = new User(0, name, password, email, mobile, false);
		
		boolean isInserted = userService.save(user);
		
		if (isInserted) {
			session.setAttribute("msg", "User registered Successfully");
			response.sendRedirect("./login");
		} else {
			Utility.ShowAlert(request, response, "register.jsp", "Fail to register the user", null);
		}		
	}

}
