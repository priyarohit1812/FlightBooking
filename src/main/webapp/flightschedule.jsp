<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="org.simplilearn.fms.entities.FlightSchedule"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Schedule</title>
</head>
<body>
	<%
	List<FlightSchedule> flightSchedules = (List<FlightSchedule>) request.getAttribute("flightSchedules");
	String visibility = "visible";
	if (flightSchedules.isEmpty()) {
		visibility = "hidden";
	}
	%>
	<a href="index.jsp">Back</a>
	<a href="flightscheduleform?id=0">Add FlightSchedule</a>
	<table border="1" style="visibility: <%=visibility%>;">
		<tr>
			<td>Flight</td>
			<td>Source</td>
			<td>Destination</td>
			<td>Departure Date</td>
			<td>Arrival Date</td>
			<td>Update FlightSchedule</td>
			<td>Delete FlightSchedule</td>
		</tr>
		<%
		for (FlightSchedule flightSchedule : flightSchedules) {
		%>
		<tr>
			<td><%=flightSchedule.getFlight().getAirline().getName() + " - " + flightSchedule.getFlight().getModel()%></td>
			<td><%=flightSchedule.getSource().getName() + ", " + flightSchedule.getSource().getCity()%></td>
			<td><%=flightSchedule.getDestination().getName() + ", " + flightSchedule.getDestination().getCity()%></td>
			<td><%=flightSchedule.getDeparture().toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)%></td>
			<td><%=flightSchedule.getArrival().toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)%></td>
			<td><a href="flightscheduleform?id=<%=flightSchedule.getId()%>">Update</a></td>
			<td>
				<form action="./flightschedule" method="post">
					<input type="hidden" name="id" value="<%=flightSchedule.getId()%>">
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