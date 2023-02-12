<%@page import="org.simplilearn.fms.entities.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Master</title>
</head>
<body>
	<%
	List<User> users = (List<User>) request.getAttribute("users");
	String visibility = "visible";
	if (users.isEmpty()) {
		visibility = "hidden";
	}
	%>
	<a href="index.jsp">Back</a>
	<a href="userform?id=0">Add User</a>
	<table border="1" style="visibility: <%=visibility%>;">
		<tr>
			<td>Name</td>
			<td>Email</td>
			<td>Mobile</td>
			<td>Update User</td>
			<td>Delete User</td>
		</tr>
		<%
		for (User user :users ) {
		%>
		<tr>
			<td><%=user.getName()%></td>
			<td><%=user.getEmail()%></td>
			<td><%=user.getMobile()%></td>
			<td><a href="userform?id=<%=user.getId()%>">Update</a></td>
			<td>
				<form action="./user" method="post">
					<input type="hidden" name="id" value="<%=user.getId()%>">
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