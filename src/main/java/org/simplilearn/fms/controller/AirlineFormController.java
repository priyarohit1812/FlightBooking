package org.simplilearn.fms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simplilearn.fms.entities.Airline;
import org.simplilearn.fms.service.AirlineService;
import org.simplilearn.fms.service.IAirlineService;
import org.simplilearn.fms.utilities.Utility;

@WebServlet("/airlineform")
public class AirlineFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IAirlineService airlineService = new AirlineService();
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =Integer.parseInt(request.getParameter("id"));
		Airline airline = this.airlineService.get(id);
		request.setAttribute("airline", airline);
		RequestDispatcher rd = request.getRequestDispatcher("airlineform.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		int id =Integer.parseInt(request.getParameter("id"));
		
		
		Airline airline = new Airline();
		airline.setId(id);
		airline.setName(name);
		airline.setAddress(address);
		airlineService.save(airline);
		Utility.ShowAlert(request, response, "airlineform.jsp", "Airline saved successfully", "./airline");
			
	}

}
