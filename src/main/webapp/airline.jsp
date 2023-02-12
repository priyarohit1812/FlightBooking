<%@page import="org.simplilearn.fms.entities.Airline"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Airline Master</title>
</head>
<body>
	<%
	List<Airline> airlines = (List<Airline>) request.getAttribute("airlines");
	String visibility = "visible";
	if (airlines.isEmpty()) {
		visibility = "hidden";
	}
	%>
	<a href="index.jsp">Back</a>
	<a href="airlineform?id=0">Add Airline</a>
	<table border="1" style="visibility: <%=visibility%>;">
		<tr>
			<td>Name</td>
			<td>Address</td>
			<td>Update Airline</td>
			<td>Delete Airline</td>
		</tr>
		<%
		for (Airline airline :airlines ) {
		%>
		<tr>
			<td><%=airline.getName()%></td>
			<td><%=airline.getAddress()%></td>
			<td><a href="airlineform?id=<%=airline.getId()%>">Update
					Airline</a></td>
			<td>
				<form action="./airline" method="post">
					<input type="hidden" name="id" value="<%=airline.getId()%>">
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