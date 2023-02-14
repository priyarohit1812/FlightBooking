<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashSet"%>
<%@page import="org.simplilearn.fms.entities.Flight"%>
<%@page import="java.util.Set"%>
<%@page import="org.simplilearn.fms.entities.Airline"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Airline Form</title>
</head>
<body>
	<%
	Airline airline = (Airline) request.getAttribute("airline");
	String action = "./airlineform";
	String visibility = "visible";
	if (airline == null) {
		Set<Flight> emptySet = new HashSet<>();
		airline = new Airline();
		airline.setName("");
		airline.setAddress("");
		airline.setFlights(emptySet);
		action = action.trim() + "?id=0";
	} else {
		action = action.trim() + "?id=" + airline.getId();
	}
	Set<Flight> flightsSet = airline.getFlights();
	if(flightsSet.isEmpty()){
		visibility = "hidden";
	}
	List<Flight> flights = new ArrayList<Flight>(flightsSet);
	%>
	<a href="./airline">Back</a>
	<form action="<%=action%>" method=post>
		Name:<input type="text" name="name" required="required"
			value="<%=airline.getName()%>"><br> Address:<textarea rows="3"
			name="address" required="required">
			<%=airline.getAddress()%></textarea><br><input
			type="submit" value="save">
	</form>
	
</body>
</html>