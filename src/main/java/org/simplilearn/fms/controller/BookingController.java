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
import org.simplilearn.fms.entities.Ticket;
import org.simplilearn.fms.entities.User;
import org.simplilearn.fms.service.FlightSchedulePriceService;
import org.simplilearn.fms.service.IFlightSchedulePriceService;
import org.simplilearn.fms.service.ITicketService;
import org.simplilearn.fms.service.IUserService;
import org.simplilearn.fms.service.TicketService;
import org.simplilearn.fms.service.UserService;
import org.simplilearn.fms.utilities.Utility;

@WebServlet("/booking")
public class BookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IFlightSchedulePriceService flightSchedulePriceService = new FlightSchedulePriceService();
	private ITicketService ticketService = new TicketService();
	private IUserService userService = new UserService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		FlightSchedulePrice flightSchedulePrice = (FlightSchedulePrice) session.getAttribute("flightSchedulePrice");
		if (flightSchedulePrice == null) {
			flightSchedulePrice = this.flightSchedulePriceService.get(id);
			session.setAttribute("flightSchedulePrice", flightSchedulePrice);
		} else if (flightSchedulePrice.getId() != id) {
			flightSchedulePrice = this.flightSchedulePriceService.get(id);
			session.setAttribute("flightSchedulePrice", flightSchedulePrice);
		}
		String redirectPath;
		if (user != null) {
			redirectPath = "booking.jsp";
		} else {
			redirectPath = "./login";
		}
		RequestDispatcher rd = request.getRequestDispatcher(redirectPath);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		FlightSchedulePrice flightSchedulePrice = (FlightSchedulePrice) session.getAttribute("flightSchedulePrice");
		int noOfSeats = Integer.parseInt(request.getParameter("noOfSeats"));
		double totalPrice = noOfSeats * flightSchedulePrice.getPrice();

		Ticket ticket = new Ticket(0, user, flightSchedulePrice, noOfSeats, totalPrice);
		this.ticketService.insert(ticket);
		int ticketId = ticket.getId();
		int userId = user.getId();
		user = this.userService.get(userId);
		session.setAttribute("user", user);
		session.setAttribute("flightSchedulePrice", null);
		Utility.ShowAlert(request, response, "booking.jsp", "Flight Ticket Booked Successfully",
				"ticketview.jsp?id=" + ticketId);
	}

}
