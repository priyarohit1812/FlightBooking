<%@page import="java.text.DecimalFormat"%>
<%@page import="org.simplilearn.fms.entities.SeatType"%>
<%@page import="org.simplilearn.fms.entities.FlightSchedulePrice"%>
<%@page import="java.util.List"%>
<%@page import="org.simplilearn.fms.entities.FlightSchedule"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Flight Schedule Price</title>
</head>
<body>
	<%
	FlightSchedulePrice flightSchedulePrice = (FlightSchedulePrice) request.getAttribute("flightSchedulePrice");
	List<FlightSchedule> flightSchedules = (List<FlightSchedule>) request.getAttribute("flightSchedules");
	List<SeatType> seatTypes = (List<SeatType>) request.getAttribute("seatTypes");
	String action = "./flightschedulepriceform";

	int scheduleId = 0;
	int seatTypeId = 0;
	DecimalFormat df = new DecimalFormat("0.00");
	String price;
	if (flightSchedulePrice == null) {
		flightSchedulePrice = new FlightSchedulePrice();
		action = action.trim() + "?id=0";
		price = df.format(0.0);
	} else {
		action = action.trim() + "?id=" + flightSchedulePrice.getId();
		if (flightSchedulePrice.getFlightSchedule() != null) {
			scheduleId = flightSchedulePrice.getFlightSchedule().getId();
		}
		if (flightSchedulePrice.getSeatType() != null) {
			seatTypeId = flightSchedulePrice.getSeatType().getId();
		}
		price = df.format(flightSchedulePrice.getPrice());
	}
	%>
	<a href="./flightscheduleprice">Back</a>
	<form action="<%=action%>" method=post>
		Flight Schedule<select name="flightSchedule" required="required">
			<option value="0"></option>
			<%
			for (FlightSchedule flightSchedule : flightSchedules) {
				int id = flightSchedule.getId();
				String selected = "";
				if (id == scheduleId) {
					selected = "selected";
				}
				String lable = flightSchedule.getLable();
			%>
			<option value="<%=id%>" <%=selected%>><%=lable%></option>
			<%
			}
			%>
		</select> Seat Type<select name="seatType" required="required">
			<option value="0"></option>
			<%
			for (SeatType type : seatTypes) {
				int id = type.getId();
				String selected = "";
				if (id == seatTypeId) {
					selected = "selected";
				}
				String lable = type.getDescription();
			%>
			<option value="<%=id%>" <%=selected%>><%=lable%></option>
			<%
			}
			%>
		</select> <br> Available Seats<input type="number" name="availability"
			required="required"
			value="<%=flightSchedulePrice.getAvailableSeat()%>"><br>Price:
		&#x20B9;<input type="number" name="price" required="required" step="0.01"
			value="<%=price%>"><br> <input type="submit"
			value="save">
	</form>
</body>
</html>