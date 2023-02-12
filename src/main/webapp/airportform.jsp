<%@page import="org.simplilearn.fms.entities.Airport"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Airport Form</title>
</head>
<body>
	<%
	Airport airport = (Airport) request.getAttribute("airport");
	String action = "./airportform";

	if (airport == null) {
		airport = new Airport();
		airport.setName("");
		airport.setCity("");
		airport.setState("");
		airport.setCountry("");
		airport.setPincode("");
		action = action.trim() + "?id=0";
	} else {
		action = action.trim() + "?id=" + airport.getId();

	}
	%>
	<a href="./airport">Back</a>
	<form action="<%=action%>" method=post>
		Name:<input type="text" name="name" required="required"
			value="<%=airport.getName()%>"><br> City:<input
			type="text" name="city" required="required"
			value="<%=airport.getCity()%>"><br> State:<input
			type="text" name="state" required="required"
			value="<%=airport.getState()%>"><br> Country:<input
			type="text" name="country" required="required"
			value="<%=airport.getCountry()%>"><br> Pin code:<input
			type="number" name="pincode" required="required"
			value="<%=airport.getPincode()%>"><br> <input
			type="submit" value="save">
	</form>
</body>
</html>