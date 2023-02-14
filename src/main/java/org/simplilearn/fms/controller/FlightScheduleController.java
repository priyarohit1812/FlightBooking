package org.simplilearn.fms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simplilearn.fms.entities.FlightSchedule;
import org.simplilearn.fms.service.FlightScheduleService;
import org.simplilearn.fms.service.IFlightScheduleService;
import org.simplilearn.fms.utilities.Utility;

@WebServlet("/flightschedule")
public class FlightScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IFlightScheduleService flightScheduleService = new FlightScheduleService();   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FlightSchedule> flightSchedules  = this.flightScheduleService.getAll();		
		request.setAttribute("flightSchedules", flightSchedules);
		RequestDispatcher rd = request.getRequestDispatcher("flightschedule.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Id =Integer.parseInt(request.getParameter("id"));		
		this.flightScheduleService.delete(Id);		
		Utility.ShowAlert(request, response, "flightschedule.jsp", "Flight Schedule deleted successfully", "./flightschedule");
	}

}
