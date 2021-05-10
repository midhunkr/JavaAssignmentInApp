<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#8c9ba8">
<h1>Issue Book</h1>
<form action="ConfirmIssue">

<table>

	<tr>
		<td>Book_Id</td>
		<td><%= request.getParameter("bookid") %>
		</td>

	</tr>
	<tr>
		<td>UserName</td>
		<td>
		
		<%= request.getParameter("username").toUpperCase() %>
		</td>
		
	</tr>
	<tr>
		<td>Enter Date</td>
		<td>
			<input name="date_issued" type="date">
		</td>
		
	</tr>
	<tr>
		<td>Due Date</td>
		<td>
			<input name="due_date" type="date">
		</td>
		
	</tr>
	<tr>
	<td></td>
	<td><input type="submit" value="issue"></td>
	</tr>
</table>
<input type="hidden" value=<%= request.getParameter("username") %> name="username">
<input type="hidden" value=<%= request.getParameter("bookid") %> name="bookid">
</form>
</body>
</html>