<%@page import="org.simplilearn.fms.entities.SeatType"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SeatType Master</title>
</head>
<body>
	<%
	List<SeatType> seatTypes = (List<SeatType>) request.getAttribute("seatTypes");
	String visibility = "visible";
	if (seatTypes.isEmpty()) {
		visibility = "hidden";
	}
	%>
	<a href="index.jsp">Back</a>
	<a href="seattypeform?id=0">Add SeatType</a>
	<table border="1" style="visibility: <%=visibility%>;">
		<tr>
			<td>Code</td>
			<td>Description</td>
			<td>Update SeatType</td>
			<td>Delete SeatType</td>
		</tr>
		<%
		for (SeatType seatType :seatTypes ) {
		%>
		<tr>
			<td><%=seatType.getType()%></td>
			<td><%=seatType.getDescription()%></td>
			<td><a href="seattypeform?id=<%=seatType.getId()%>">Update</a></td>
			<td>
				<form action="./seattype" method="post">
					<input type="hidden" name="id" value="<%=seatType.getId()%>">
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