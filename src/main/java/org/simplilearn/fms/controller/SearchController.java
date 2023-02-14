package org.simplilearn.fms.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simplilearn.fms.entities.FlightSchedulePrice;
import org.simplilearn.fms.service.FlightScheduleService;
import org.simplilearn.fms.service.IFlightScheduleService;

@WebServlet("/search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IFlightScheduleService flightScheduleService = new FlightScheduleService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int fromId = Integer.parseInt(request.getParameter("from"));
		int toId = Integer.parseInt(request.getParameter("to"));
		String departure = request.getParameter("departure");
		Timestamp timestamp = Timestamp.valueOf(LocalDateTime.parse(departure, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		List<FlightSchedulePrice> flightSchedulePrices = this.flightScheduleService.search(fromId, toId, timestamp);
		request.setAttribute("flightSchedulePrices", flightSchedulePrices);
		RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
		rd.forward(request, response);
	}

}
