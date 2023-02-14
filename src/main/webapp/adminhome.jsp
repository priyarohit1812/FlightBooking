<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
</head>
<body>
	<h4>Hi ${sessionScope.user.name}, Welcome to Flight Management
		System</h4>
		
	<a href="./user">User Master</a><br>
	<a href="./seattype">Seat Type Master</a><br>
	<a href="./airport">Airport Master</a><br>
	<a href="./airline">Airline Master</a><br>
	<a href="./flight">Flight Master</a><br>
	<a href="./flightschedule">Flight Schedule</a><br>
	<a href="./flightscheduleprice">Flight Schedule Price</a><br>
	<br><br><a href="./logout">Logout</a>
</body>
</html>