<%@page import="org.simplilearn.fms.entities.SeatType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SeatType Form</title>
</head>
<body>
	<%
	SeatType seatType = (SeatType) request.getAttribute("seatType");
	String action = "./seattypeform";

	if (seatType == null) {
		seatType = new SeatType();
		seatType.setType("");
		seatType.setDescription("");
		action = action.trim() + "?id=0";
	} else {
		action = action.trim() + "?id=" + seatType.getId();

	}
	%>
	<a href="./seattype">Back</a>
	<form action="<%=action%>" method=post>
		Code:<input type="text" name="code" required="required"
			value="<%=seatType.getType()%>"><br> Description:<input
			type="text" name="description" required="required"
			value="<%=seatType.getDescription()%>"><br> <input
			type="submit" value="save">
	</form>
</body>
</html>