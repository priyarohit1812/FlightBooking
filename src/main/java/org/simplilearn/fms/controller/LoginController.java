package org.simplilearn.fms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.simplilearn.fms.entities.FlightSchedulePrice;
import org.simplilearn.fms.entities.User;
import org.simplilearn.fms.service.IUserService;
import org.simplilearn.fms.service.UserService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService userService = new UserService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String isAdminParam = request.getParameter("isAdmin");
		boolean isAdmin = false;
		if (isAdminParam != null) {
			isAdmin = Boolean.parseBoolean(isAdminParam);
		}
		request.setAttribute("isAdmin", isAdmin);
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
		User user = null;
		String fallBackUrl = "./login";
		String redirectURL = "";
		if (isAdmin) {
			user = this.userService.loginAsAdmin(username, password);
			fallBackUrl = fallBackUrl + "?isAdmin=" + isAdmin;
			redirectURL = "adminhome.jsp";
		} else {			
			user = this.userService.loginAsUser(username, password);
			FlightSchedulePrice flightSchedulePrice = (FlightSchedulePrice) session.getAttribute("flightSchedulePrice");
			if (flightSchedulePrice != null) {
				redirectURL = "./booking?id="+flightSchedulePrice.getId();
			} else {
				redirectURL = "userhome.jsp";
			}
			
		}

		if (user != null) {
			session.setAttribute("user", user);
			response.sendRedirect(redirectURL);
		} else {
			session.setAttribute("msg", "username/password is invalid");
			response.sendRedirect(fallBackUrl);
		}
	}

}
