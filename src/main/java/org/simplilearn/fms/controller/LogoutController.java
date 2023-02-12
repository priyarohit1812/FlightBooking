package org.simplilearn.fms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.simplilearn.fms.entities.User;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		boolean isAdmin = user.isAdmin();
		
		session.setAttribute("user", null);
		if(isAdmin) {
			response.sendRedirect("./login?isAdmin=true");
		} else {
			response.sendRedirect("index.jsp");
		}
	}
}
