<%@page import="java.time.LocalDateTime"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="org.simplilearn.fms.entities.Flight"%>
<%@page import="org.simplilearn.fms.entities.Airport"%>
<%@page import="java.util.List"%>
<%@page import="org.simplilearn.fms.entities.FlightSchedule"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Schedule Form</title>
</head>
<body>
	<%
	FlightSchedule flightSchedule = (FlightSchedule) request.getAttribute("flightSchedule");
	List<Airport> airports = (List<Airport>) request.getAttribute("airports");
	List<Flight> flights = (List<Flight>) request.getAttribute("flights");
	String action = "./flightscheduleform";

	int asId = 0;
	int adId = 0;
	int fId = 0;
	if (flightSchedule == null) {
		flightSchedule = new FlightSchedule();
		flightSchedule.setDeparture(Timestamp.valueOf(LocalDateTime.now()));
		flightSchedule.setArrival(Timestamp.valueOf(LocalDateTime.now().plusHours(2)));
		action = action.trim() + "?id=0";
	} else {
		action = action.trim() + "?id=" + flightSchedule.getId();
		if (flightSchedule.getSource() != null) {
			asId = flightSchedule.getSource().getId();
		}
		if (flightSchedule.getDestination() != null) {
			adId = flightSchedule.getDestination().getId();
		}
		if (flightSchedule.getFlight() != null) {
			fId = flightSchedule.getFlight().getId();
		}

	}
	%>
	<a href="./flightschedule">Back</a>
	<form action="<%=action%>" method=post>
		Flight : <select name="flight" required="required">
			<option value="0"></option>
			<%
			for (Flight flight : flights) {
				int flightId = flight.getId();
				String selected = "";
				if (flightId == fId) {
					selected = "selected";
				}
			%>
			<option value="<%=flight.getId()%>" <%=selected%>><%=flight.getAirline().getName()%>
				-
				<%=flight.getModel()%></option>
			<%
			}
			%>
		</select> Source : <select name="source" required="required">
			<option value="0"></option>
			<%
			for (Airport airport : airports) {
				int airportId = airport.getId();
				String selected = "";
				if (airportId == asId) {
					selected = "selected";
				}
			%>
			<option value="<%=airport.getId()%>" <%=selected%>><%=airport.getName()%>,
				<%=airport.getCity()%></option>
			<%
			}
			%>
		</select> <br> Destination : <select name="destination"
			required="required">
			<option value="0"></option>
			<%
			for (Airport airport : airports) {
				int airportId = airport.getId();
				String selected = "";
				if (airportId == adId) {
					selected = "selected";
				}
			%>
			<option value="<%=airport.getId()%>" <%=selected%>><%=airport.getName()%>,
				<%=airport.getCity()%></option>
			<%
			}
			%>
		</select> <br> Departure Date:<input type="datetime-local"
			name="departure" required="required"
			value="<%=flightSchedule.getDeparture()%>"><br>Arrival
		Date:<input type="datetime-local" name="arrival" required="required"
			value="<%=flightSchedule.getArrival()%>"><br> <input
			type="submit" value="save">
	</form>
</body>
</html>