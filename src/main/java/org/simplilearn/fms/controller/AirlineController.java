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
import org.simplilearn.fms.service.AirlineService;
import org.simplilearn.fms.service.IAirlineService;
import org.simplilearn.fms.utilities.Utility;

@WebServlet("/airline")
public class AirlineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IAirlineService airlineService = new AirlineService();   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Airline> airlines  = airlineService.getAll();
		request.setAttribute("airlines", airlines);
		RequestDispatcher rd = request.getRequestDispatcher("airline.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Id =Integer.parseInt(request.getParameter("id"));		
		airlineService.delete(Id);		
		Utility.ShowAlert(request, response, "airline.jsp", "Airline deleted successfully", "./airline");
	}

}
