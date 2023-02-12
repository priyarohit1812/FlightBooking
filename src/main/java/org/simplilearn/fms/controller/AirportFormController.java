package org.simplilearn.fms.controller;

import java.io.IOException;

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

@WebServlet("/airportform")
public class AirportFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IAirportService airportService = new AirportService();
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =Integer.parseInt(request.getParameter("id"));
		Airport airport = this.airportService.get(id);
		request.setAttribute("airport", airport);
		RequestDispatcher rd = request.getRequestDispatcher("airportform.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String pincode = request.getParameter("pincode");
		int id =Integer.parseInt(request.getParameter("id"));
		
		
		Airport airport = new Airport();
		airport.setId(id);
		airport.setName(name);
		airport.setCity(city);
		airport.setState(state);;
		airport.setCountry(country);
		airport.setPincode(pincode);
		airportService.save(airport);
		Utility.ShowAlert(request, response, "airportform.jsp", "Airport saved successfully", "./airport");
			
	}

}
