<%@page import="org.simplilearn.fms.entities.Flight"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Master</title>
</head>
<body>
	<%
	List<Flight> flights = (List<Flight>) request.getAttribute("flights");
	String visibility = "visible";
	if (flights.isEmpty()) {
		visibility = "hidden";
	}
	%>
	<a href="index.jsp">Back</a>
	<a href="flightform?id=0">Add Flight</a>
	<table border="1" style="visibility: <%=visibility%>;">
		<tr>
			<td>Name</td>
			<td>Airline</td>
			<td>Update Flight</td>
			<td>Delete Flight</td>
		</tr>
		<%
		for (Flight flight :flights ) {
		%>
		<tr>
			<td><%=flight.getModel()%></td>
			<td><%=flight.getAirline().getName()%></td>
			<td><a href="flightform?id=<%=flight.getId()%>">Update
					Flight</a></td>
			<td>
				<form action="./flight" method="post">
					<input type="hidden" name="id" value="<%=flight.getId()%>">
					<input type="submit" value="Delete">
				</form>
			</td>
		</tr>
		<%
		}
		%>
	</table>

	<p style="color: blue;">${requestScope.msg}</p>
</body>
</html>