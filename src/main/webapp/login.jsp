<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<%
	boolean isAdmin = (boolean) request.getAttribute("isAdmin");
	%>
	<h1>Flight Booking System</h1>
	<form action="./login" method="post">
		<input type="hidden" name=isAdmin value="<%=isAdmin%>"> Email
		or Mobile<input type="text" name="username" required="required"><br>
		Password<input type="password" name="password" required="required"><br>
		<input type="submit" value="submit">
		<%
		if (!isAdmin) {
		%>
		<a href="register.jsp">Register</a>
		<%
		}
		%>
	</form>
	<p style="color: red;">${sessionScope.msg}</p>
</body>
</html>