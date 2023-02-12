<%@page import="org.simplilearn.fms.entities.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Form</title>
</head>
<body>
	<%
	User user = (User) request.getAttribute("user");
	String action = "./userform";

	if (user == null) {
		user = new User();
		user.setName("");
		user.setEmail("");
		user.setMobile("");
		user.setPassword("");
		user.setAdmin(true);
		action = action.trim() + "?id=0";
	} else {
		action = action.trim() + "?id=" + user.getId();

	}
	%>
	<a href="./user">Back</a>
	<form action="<%=action%>" method=post>
		Name:<input type="text" name="name" required="required"
			value="<%=user.getName()%>"><br> Email:<input
			type="text" name="email" required="required"
			value="<%=user.getEmail()%>"><br> Mobile:<input
			type="text" name="mobile" required="required"
			value="<%=user.getMobile()%>"><br> Password:<input
			type="password" name="password" required="required"
			value="<%=user.getPassword()%>"><br> <input
			type="hidden" name="isAdmin" value="<%=user.isAdmin()%>"><br>
		<input type="submit" value="save">
	</form>
</body>
</html>