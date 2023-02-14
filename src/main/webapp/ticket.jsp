<%@page import="java.util.ArrayList"%>
<%@page import="org.simplilearn.fms.entities.User"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="org.simplilearn.fms.entities.Airline"%>
<%@page import="org.simplilearn.fms.entities.Flight"%>
<%@page import="org.simplilearn.fms.entities.FlightSchedule"%>
<%@page import="org.simplilearn.fms.entities.FlightSchedulePrice"%>
<%@page import="org.simplilearn.fms.entities.Ticket"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Tickets</title>
</head>
<body>
	<%
	User user = (User)session.getAttribute("user");
	List<Ticket> tickets = new ArrayList<>(user.getTickets());
	String visibility = "visible";
	if (tickets.isEmpty()) {
		visibility = "hidden";
	}
	%>
	<a href="userhome.jsp">Back</a>
	<table border="1" style="visibility: <%=visibility%>;">
		<tr>
			<td>Flight</td>
			<td>Source</td>
			<td>Destination</td>
			<td>Departure</td>
			<td>Arrival</td>
			<td>Seat Type</td>
			<td>No. of Seats</td>
			<td>Price</td>
			<td>Total Price</td>
			<td>Action</td>
		</tr>
		<%
		for (Ticket ticket : tickets) {
			int id = ticket.getId();
			FlightSchedulePrice flightSchedulePrice = ticket.getPrice();
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
			int noOfSeats = ticket.getNoOfSeat();
			String totalPrice = df.format(ticket.getTotalPrice());
		%>
		<tr>
			<td><%=flightName%></td>
			<td><%=source%></td>
			<td><%=destination%></td>
			<td><%=departure%></td>
			<td><%=arrival%></td>
			<td><%=seatType%></td>
			<td><%=noOfSeats%></td>
			<td>&#x20B9; <%=price%></td>
			<td>&#x20B9; <%=totalPrice%></td>
			<td><a href="ticketview.jsp?id=<%=id%>">View</a></td>
		</tr>
		<%
		}
		%>
	</table>

</body>
</html>