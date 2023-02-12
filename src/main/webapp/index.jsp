<%@page import="org.simplilearn.fms.entities.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Booking System</title>
</head>
<body>
	<%
	User user = (User) session.getAttribute("user");
	if (user != null && user.isAdmin()) {
		response.sendRedirect("adminhome.jsp");
	} else {
		response.sendRedirect("userhome.jsp");
	}
	%>
</body>
</html>