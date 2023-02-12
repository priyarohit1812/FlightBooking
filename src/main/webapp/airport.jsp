<%@page import="org.simplilearn.fms.entities.Airport"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Airport Master</title>
</head>
<body>
	<%
	List<Airport> airports = (List<Airport>) request.getAttribute("airports");
	String visibility = "visible";
	if (airports.isEmpty()) {
		visibility = "hidden";
	}
	%>
	<a href="index.jsp">Back</a>
	<a href="airportform?id=0">Add Airport</a>
	<table border="1" style="visibility: <%=visibility%>;">
		<tr>
			<td>Name</td>
			<td>City</td>
			<td>State</td>
			<td>Country</td>
			<td>Pin code</td>
		</tr>
		<%
		for (Airport airport :airports ) {
		%>
		<tr>
			<td><%=airport.getName()%></td>
			<td><%=airport.getCity()%></td>
			<td><%=airport.getState()%></td>
			<td><%=airport.getCountry()%></td>
			<td><%=airport.getPincode()%></td>
			<td><a href="airportform?id=<%=airport.getId()%>">Update
					Airport</a></td>
			<td>
				<form action="./airport" method="post">
					<input type="hidden" name="id" value="<%=airport.getId()%>">
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