<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="org.simplilearn.fms.entities.Airline"%>
<%@page import="org.simplilearn.fms.entities.Airport"%>
<%@page import="org.simplilearn.fms.entities.Flight"%>
<%@page import="org.simplilearn.fms.entities.FlightSchedule"%>
<%@page import="org.simplilearn.fms.entities.FlightSchedulePrice"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking</title>
</head>
<body>
	<%
	FlightSchedulePrice flightSchedulePrice = (FlightSchedulePrice) session.getAttribute("flightSchedulePrice");
	FlightSchedule flightSchedule = flightSchedulePrice.getFlightSchedule();
	Flight flight = flightSchedule.getFlight();
	Airline airline = flight.getAirline();
	String flightName = airline.getName() + " - " + flight.getModel();
	String source = flightSchedule.getSource().getName() + ", " + flightSchedule.getSource().getCity();
	String destination = flightSchedule.getDestination().getName() + ", " + flightSchedule.getDestination().getCity();
	String departure = flightSchedule.getDeparture().toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	String arrival = flightSchedule.getArrival().toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	String seatType = flightSchedulePrice.getSeatType().getDescription();
	DecimalFormat df = new DecimalFormat("0.00");
	String price = df.format(flightSchedulePrice.getPrice());
	%>
	<a href="userhome.jsp">Back</a><br><br>
	<form action="./booking" method="post">
		Flight Name <input type="text" value="<%=flightName%>"
			readonly="readonly"><br> Source <input type="text"
			value="<%=source%>" readonly="readonly"><br> Destination<input
			type="text" value="<%=destination%>" readonly="readonly"><br>
		Departure<input type="datetime-local" value="<%=departure%>"
			readonly="readonly"><br> Arrival<input
			type="datetime-local" value="<%=arrival%>" readonly="readonly"><br>
		Seat Type<input type="text" value="<%=seatType%>" readonly="readonly"><br>
		Price &#x20B9; <input type="text" value="<%=price%>"
			readonly="readonly"><br> No of Seats <input
			type="number" name="noOfSeats" required="required"><br><br>
		<table>
		<thead><strong>Payment Info</strong></thead>
			<tr>
				<td>Name on Card</td>
				<td><input type="text" name="ccNo" maxlength="50"
					required="required"></td>
			</tr>
			<tr>
				<td>Card Number</td>
				<td><input type="text" name="ccNo" maxlength="16"
					required="required"></td>
			</tr>
			<tr>
				<td>Expiry</td>
				<td><input type="text" name="expMonth" placeholder="MM"
					maxlength="2" required="required"></td>
				<td>/</td>
				<td><input type="text" name="expYear" placeholder="YY"
					maxlength="2" required="required"></td>
			</tr>
			<tr>
				<td>CVV Number</td>
				<td><input type="text" name="cvv" maxlength="4"
					required="required"></td>
			</tr>
		</table>
		<input type="submit" value="Book">
	</form>
	<p style="color: red;">${sessionScope.msg}</p>
</body>
</html>