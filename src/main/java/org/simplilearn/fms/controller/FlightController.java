package org.simplilearn.fms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simplilearn.fms.entities.Flight;
import org.simplilearn.fms.service.FlightService;
import org.simplilearn.fms.service.IFlightService;
import org.simplilearn.fms.utilities.Utility;

@WebServlet("/flight")
public class FlightController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IFlightService flightService = new FlightService();   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Flight> flights  = flightService.getAll();
		request.setAttribute("flights", flights);
		RequestDispatcher rd = request.getRequestDispatcher("flight.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Id =Integer.parseInt(request.getParameter("id"));		
		flightService.delete(Id);		
		Utility.ShowAlert(request, response, "flight.jsp", "Flight deleted successfully", "./flight");
	}

}
