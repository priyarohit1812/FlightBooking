package org.simplilearn.fms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simplilearn.fms.entities.SeatType;
import org.simplilearn.fms.service.SeatTypeService;
import org.simplilearn.fms.service.ISeatTypeService;
import org.simplilearn.fms.utilities.Utility;

@WebServlet("/seattype")
public class SeatTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ISeatTypeService seatTypeService = new SeatTypeService(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SeatType> seatTypes  = seatTypeService.getAll();
		request.setAttribute("seatTypes", seatTypes);
		RequestDispatcher rd = request.getRequestDispatcher("seattype.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Id =Integer.parseInt(request.getParameter("id"));		
		seatTypeService.delete(Id);		
		Utility.ShowAlert(request, response, "seattype.jsp", "Seat Type deleted successfully", "./seattype");
	}

}
