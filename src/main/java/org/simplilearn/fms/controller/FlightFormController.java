package org.simplilearn.fms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simplilearn.fms.entities.Airline;
import org.simplilearn.fms.entities.Flight;
import org.simplilearn.fms.service.AirlineService;
import org.simplilearn.fms.service.FlightService;
import org.simplilearn.fms.service.IAirlineService;
import org.simplilearn.fms.service.IFlightService;
import org.simplilearn.fms.utilities.Utility;

@WebServlet("/flightform")
public class FlightFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IFlightService flightService = new FlightService();
    private IAirlineService airlineService = new AirlineService();
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =Integer.parseInt(request.getParameter("id"));
		Flight flight = this.flightService.get(id);
		List<Airline> airlines = this.airlineService.getAll();
		request.setAttribute("flight", flight);
		request.setAttribute("airlines", airlines);
		RequestDispatcher rd = request.getRequestDispatcher("flightform.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String model = request.getParameter("model");
		int id =Integer.parseInt(request.getParameter("id"));
		int airlineId =Integer.parseInt(request.getParameter("airlineId"));
		
		Airline airline = this.airlineService.get(airlineId);
		
		Flight flight = new Flight(id, model, airline);
		flightService.save(flight);
		Utility.ShowAlert(request, response, "flightform.jsp", "Flight saved successfully", "./flight");
			
	}

}
