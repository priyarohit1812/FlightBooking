package org.simplilearn.fms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simplilearn.fms.entities.User;
import org.simplilearn.fms.service.UserService;
import org.simplilearn.fms.service.IUserService;
import org.simplilearn.fms.utilities.Utility;

@WebServlet("/userform")
public class UserFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserService userService = new UserService();
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =Integer.parseInt(request.getParameter("id"));
		User user = this.userService.get(id);
		request.setAttribute("user", user);
		RequestDispatcher rd = request.getRequestDispatcher("userform.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
		int id =Integer.parseInt(request.getParameter("id"));
		
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setPassword(password);
		user.setAdmin(isAdmin);
		userService.save(user);
		Utility.ShowAlert(request, response, "userform.jsp", "User saved successfully", "./user");
			
	}

}
