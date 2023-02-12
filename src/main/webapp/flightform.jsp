<%@page import="org.simplilearn.fms.entities.Airline"%>
<%@page import="java.util.List"%>
<%@page import="org.simplilearn.fms.entities.Flight"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Form</title>
</head>
<body>
	<%
	Flight flight = (Flight) request.getAttribute("flight");
	List<Airline> airlines = (List<Airline>) request.getAttribute("airlines");
	String action = "./flightform";
	int alId = 0;
	if (flight == null) {
		flight = new Flight();
		flight.setModel("");
		action = action.trim() + "?id=0";
	} else {
		action = action.trim() + "?id=" + flight.getId();
		if (flight.getAirline() != null) {
			alId = flight.getAirline().getId();
		}

	}
	%>
	<a href="./flight">Back</a>
	<form action="<%=action%>" method=post>
		Name:<input type="text" name="model" required="required"
			value="<%=flight.getModel()%>"><br> Airline: <select
			name="airlineId" required="required">
			<option value="0"></option>
			<%
			for (Airline airline : airlines) {
				int airlineId = airline.getId();
				String selected = "";
				if (airlineId == alId) {
					selected = "selected";
				}
			%>
			<option value="<%=airline.getId()%>" <%=selected%>><%=airline.getName()%></option>
			<%
			}
			%>
		</select> <br> <input type="submit" value="save">
	</form>
</body>
</html>