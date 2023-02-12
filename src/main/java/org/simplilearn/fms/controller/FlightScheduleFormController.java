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

import org.simplilearn.fms.entities.Airport;
import org.simplilearn.fms.entities.Flight;
import org.simplilearn.fms.entities.FlightSchedule;
import org.simplilearn.fms.service.AirportService;
import org.simplilearn.fms.service.FlightScheduleService;
import org.simplilearn.fms.service.FlightService;
import org.simplilearn.fms.service.IAirportService;
import org.simplilearn.fms.service.IFlightScheduleService;
import org.simplilearn.fms.service.IFlightService;
import org.simplilearn.fms.utilities.Utility;

@WebServlet("/flightscheduleform")
public class FlightScheduleFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IFlightScheduleService flightScheduleService = new FlightScheduleService();
	private IAirportService airportService = new AirportService();
	private IFlightService flightService = new FlightService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		FlightSchedule flightSchedule = this.flightScheduleService.get(id);
		List<Airport> airports = this.airportService.getAll();
		List<Flight> flights = this.flightService.getAll();
		request.setAttribute("flightSchedule", flightSchedule);
		request.setAttribute("airports", airports);
		request.setAttribute("flights", flights);
		RequestDispatcher rd = request.getRequestDispatcher("flightscheduleform.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int flightId = Integer.parseInt(request.getParameter("flight"));
		int sourceId = Integer.parseInt(request.getParameter("source"));
		int destinationId = Integer.parseInt(request.getParameter("destination"));
		Timestamp departure = Timestamp
				.valueOf(LocalDateTime.parse(request.getParameter("departure"), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		Timestamp arrival = Timestamp
				.valueOf(LocalDateTime.parse(request.getParameter("arrival"), DateTimeFormatter.ISO_LOCAL_DATE_TIME));

		Airport source = this.airportService.get(sourceId);
		Airport destination = this.airportService.get(destinationId);
		Flight flight = this.flightService.get(flightId);
		FlightSchedule flightSchedule = new FlightSchedule();
		flightSchedule.setId(id);
		flightSchedule.setFlight(flight);
		flightSchedule.setSource(source);
		flightSchedule.setDestination(destination);
		flightSchedule.setDeparture(departure);
		flightSchedule.setArrival(arrival);
		flightScheduleService.save(flightSchedule);
		Utility.ShowAlert(request, response, "flightscheduleform.jsp", "Flight Schedule saved successfully",
				"./flightschedule");

	}

}
