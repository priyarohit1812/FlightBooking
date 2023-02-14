<%@page import="org.simplilearn.fms.entities.User"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="org.simplilearn.fms.entities.Airline"%>
<%@page import="org.simplilearn.fms.entities.Flight"%>
<%@page import="org.simplilearn.fms.entities.FlightSchedule"%>
<%@page import="org.simplilearn.fms.entities.FlightSchedulePrice"%>
<%@page import="org.simplilearn.fms.entities.Ticket"%>
<%@page import="org.simplilearn.fms.service.TicketService"%>
<%@page import="org.simplilearn.fms.service.ITicketService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
int id =Integer.parseInt(request.getParameter("id"));
ITicketService ticketService = new TicketService();
Ticket ticket = ticketService.get(id);
User user = ticket.getUser();
FlightSchedulePrice flightSchedulePrice = ticket.getPrice();
FlightSchedule flightSchedule = flightSchedulePrice.getFlightSchedule();
Flight flight = flightSchedule.getFlight();
Airline airline = flight.getAirline();
String name = user.getName();
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
<a href="ticket.jsp">Back</a><br><br>

<strong>Name</strong> : <%=name %><br>
<strong>Flight</strong> : <%=flightName %><br>
<strong>Source</strong> : <%=source %><br>
<strong>Destination</strong> : <%=destination %><br>
<strong>Departure</strong> : <%=departure %>&emsp;<strong>Arrival</strong> : <%=arrival %><br>
<strong>Seat Type</strong> : <%=seatType %>&emsp;<strong>No. of Seats</strong> : <%=noOfSeats %><br>
<strong>Sub Price</strong> : &#x20B9; <%=price %>&emsp;<strong>Total Price</strong> : &#x20B9; <%=totalPrice %><br>

</body>
</html>