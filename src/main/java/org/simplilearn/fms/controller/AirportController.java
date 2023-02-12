package org.simplilearn.fms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simplilearn.fms.entities.Airport;
import org.simplilearn.fms.service.AirportService;
import org.simplilearn.fms.service.IAirportService;
import org.simplilearn.fms.utilities.Utility;

@WebServlet("/airport")
public class AirportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IAirportService airportService = new AirportService();   
    
    public AirportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Airport> airports  = airportService.getAll();
		request.setAttribute("airports", airports);
		RequestDispatcher rd = request.getRequestDispatcher("airport.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Id =Integer.parseInt(request.getParameter("id"));		
		airportService.delete(Id);		
		Utility.ShowAlert(request, response, "airport.jsp", "Airport deleted successfully", "./airport");
	}

}
