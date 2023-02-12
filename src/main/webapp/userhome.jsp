<%@page import="org.simplilearn.fms.entities.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Home</title>
</head>
<body>
	<%
	User user = (User) session.getAttribute("user");
	String name = "There";
	if (user != null) {
		name = user.getName();
	}
	%>
	<h4>
		Hello
		<%=name%>, Welcome to Flight Management System
	</h4>
	<%
	if (user == null) {
	%>
	<a href="./login">Login</a>
	<br>
	<br>
	<a href="./login?isAdmin=true">Login as Admin</a>
	<%
	} else {
	%>
	<a href="./logout">Logout</a>
	<br>
	<br>
	<%
	}
	%>
</body>
</html>