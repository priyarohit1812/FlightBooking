<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="org.simplilearn.fms.entities.Airline"%>
<%@page import="org.simplilearn.fms.entities.Flight"%>
<%@page import="org.simplilearn.fms.entities.FlightSchedule"%>
<%@page import="org.simplilearn.fms.entities.FlightSchedulePrice"%>
<%@page import="org.simplilearn.fms.service.AirportService"%>
<%@page import="org.simplilearn.fms.service.IAirportService"%>
<%@page import="org.simplilearn.fms.entities.Airport"%>
<%@page import="java.util.List"%>
<%@page import="org.simplilearn.fms.entities.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Home</title>
</head>
<body>
	<%
	IAirportService airportService = new AirportService();
	List<Airport> airports = airportService.getAll();
	User user = (User) session.getAttribute("user");	
	String name = "There";
	if (user != null) {
		name = user.getName();
	}
	%>
	<h4>
		Hello
		<%=name%>, Welcome to Flight Management System
	</h4>
	<%
	if (user == null) {
	%>
	<a href="./login">Login</a>
	<a href="./login?isAdmin=true">Login as Admin</a>
	<%
	} else {
	%>
	<a href="ticket.jsp">My Tickets</a>
	<a href="./logout">Logout</a>
	<br>
	<br>
	<%
	}
	%>
	<br>
	<br>
	<form action="./search" method=post>
		From : <select name="from" required="required">
			<option value="0"></option>
			<%
			for (Airport airport : airports) {
				int id = airport.getId();
				String city = airport.getCity();
			%>
			<option value="<%=id%>"><%=city%></option>
			<%
			}
			%>
		</select> To : <select name="to" required="required">
			<option value="0"></option>
			<%
			for (Airport airport : airports) {
				int id = airport.getId();
				String city = airport.getCity();
			%>
			<option value="<%=id%>"><%=city%></option>
			<%
			}
			%>
		</select> Departure Date:<input type="datetime-local" name="departure"
			required="required"> <input type="submit" value="search">
	</form>		
</body>
</html>