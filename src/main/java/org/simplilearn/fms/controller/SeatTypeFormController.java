package org.simplilearn.fms.controller;

import java.io.IOException;

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

@WebServlet("/seattypeform")
public class SeatTypeFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ISeatTypeService seatTypeService = new SeatTypeService();
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =Integer.parseInt(request.getParameter("id"));
		SeatType seatType = this.seatTypeService.get(id);
		request.setAttribute("seatType", seatType);
		RequestDispatcher rd = request.getRequestDispatcher("seattypeform.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String description = request.getParameter("description");
		int id =Integer.parseInt(request.getParameter("id"));
		
		
		SeatType seatType = new SeatType(id, code, description);
		seatTypeService.save(seatType);
		Utility.ShowAlert(request, response, "seattypeform.jsp", "Seat Type saved successfully", "./seattype");
			
	}

}
