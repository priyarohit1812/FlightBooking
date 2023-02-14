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
import org.simplilearn.fms.entities.FlightSchedulePrice;
import org.simplilearn.fms.entities.SeatType;
import org.simplilearn.fms.service.FlightSchedulePriceService;
import org.simplilearn.fms.service.FlightScheduleService;
import org.simplilearn.fms.service.IFlightSchedulePriceService;
import org.simplilearn.fms.service.IFlightScheduleService;
import org.simplilearn.fms.service.ISeatTypeService;
import org.simplilearn.fms.service.SeatTypeService;
import org.simplilearn.fms.utilities.Utility;

@WebServlet("/flightschedulepriceform")
public class FlightSchedulePriceFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IFlightScheduleService flightScheduleService = new FlightScheduleService();
	private IFlightSchedulePriceService flightSchedulePriceService = new FlightSchedulePriceService();
	private ISeatTypeService seatTypeService = new SeatTypeService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		FlightSchedulePrice flightSchedulePrice = this.flightSchedulePriceService.get(id);
		List<FlightSchedule> flightSchedules = this.flightScheduleService.getAll();
		List<SeatType> seatTypes = this.seatTypeService.getAll();
		request.setAttribute("flightSchedulePrice", flightSchedulePrice);
		request.setAttribute("flightSchedules", flightSchedules);
		request.setAttribute("seatTypes", seatTypes);
		RequestDispatcher rd = request.getRequestDispatcher("flightschedulepriceform.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int flightScheduleId = Integer.parseInt(request.getParameter("flightSchedule"));
		int seatTypeId = Integer.parseInt(request.getParameter("seatType"));
		int availability = Integer.parseInt(request.getParameter("availability"));
		double price = Double.parseDouble(request.getParameter("price"));
		
		FlightSchedule flightSchedule = this.flightScheduleService.get(flightScheduleId);
		SeatType seatType = this.seatTypeService.get(seatTypeId);
		FlightSchedulePrice flightSchedulePrice = new FlightSchedulePrice(id, price, availability, flightSchedule, seatType);
		flightSchedulePriceService.save(flightSchedulePrice);
		Utility.ShowAlert(request, response, "flightschedulepriceform.jsp", "Flight Schedule Price saved successfully",
				"./flightscheduleprice");
	}

}
