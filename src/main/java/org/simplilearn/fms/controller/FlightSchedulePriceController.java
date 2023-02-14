package org.simplilearn.fms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simplilearn.fms.entities.FlightSchedulePrice;
import org.simplilearn.fms.service.FlightSchedulePriceService;
import org.simplilearn.fms.service.IFlightSchedulePriceService;
import org.simplilearn.fms.utilities.Utility;

@WebServlet("/flightscheduleprice")
public class FlightSchedulePriceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IFlightSchedulePriceService flightSchedulePriceService = new FlightSchedulePriceService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FlightSchedulePrice> flightSchedulePrices  = this.flightSchedulePriceService.getAll();		
		request.setAttribute("flightSchedulePrices", flightSchedulePrices);
		RequestDispatcher rd = request.getRequestDispatcher("flightscheduleprice.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Id =Integer.parseInt(request.getParameter("id"));		
		this.flightSchedulePriceService.delete(Id);		
		Utility.ShowAlert(request, response, "flightscheduleprice.jsp", "Flight Schedule Price deleted successfully", "./flightscheduleprice");
	}

}
