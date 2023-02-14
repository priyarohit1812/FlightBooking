<%@page import="java.text.DecimalFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="org.simplilearn.fms.entities.Airline"%>
<%@page import="org.simplilearn.fms.entities.Flight"%>
<%@page import="org.simplilearn.fms.entities.FlightSchedule"%>
<%@page import="org.simplilearn.fms.entities.FlightSchedulePrice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Result</title>
</head>
<body>
	<a href="userhome.jsp">Back</a>
	<%
	List<FlightSchedulePrice> flightSchedulePrices = (List<FlightSchedulePrice>) request.getAttribute("flightSchedulePrices");
	String visibility = "visible";
	if (flightSchedulePrices.isEmpty()) {
		visibility = "hidden";
	}
	%>
	<table border="1" style="visibility: <%=visibility%>;">
		<tr>
			<td>Flight</td>
			<td>Source</td>
			<td>Destination</td>
			<td>Departure</td>
			<td>Arrival</td>
			<td>Seat Type</td>
			<td>Price</td>
			<td>Action</td>
		</tr>
		<%
		for (FlightSchedulePrice flightSchedulePrice : flightSchedulePrices) {
			int id = flightSchedulePrice.getId();
			FlightSchedule flightSchedule = flightSchedulePrice.getFlightSchedule();
			Flight flight = flightSchedule.getFlight();
			Airline airline = flight.getAirline();
			String flightName = airline.getName() + " - " +flight.getModel();
			String source = flightSchedule.getSource().getName() + ", " + flightSchedule.getSource().getCity();
			String destination = flightSchedule.getDestination().getName() + ", " + flightSchedule.getDestination().getCity();
			String departure = flightSchedule.getDeparture().toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			String arrival = flightSchedule.getArrival().toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			String seatType = flightSchedulePrice.getSeatType().getDescription();
			DecimalFormat df=new DecimalFormat("0.00");
			String price = df.format(flightSchedulePrice.getPrice());
		%>
		<tr>
			<td><%=flightName%></td>
			<td><%=source%></td>
			<td><%=destination%></td>
			<td><%=departure%></td>
			<td><%=arrival%></td>
			<td><%=seatType%></td>
			<td>&#x20B9; <%=price%></td>
			<td><a href="booking?id=<%=id%>">Book</a></td>			
		</tr>
		<%
		}
		%>
	</table>

	<p style="color: blue;">${requestScope.msg}</p>
</body>
</html>