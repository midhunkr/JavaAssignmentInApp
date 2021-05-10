<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#539aa3">
<h1>Hi <%= request.getParameter("username").toUpperCase() %></h1>
<form action="SearchBook" method="get">
<h2>Search Books</h2> 
<h3>Search Using:</h3>
<select name="parameter">
<option value="BookName">Book Name</option>
<option value="AuthorName">Author </option>
<option value="Id">Id </option>

</select>
<h3>Enter Value: <input type="text" name="parametervalue"> </h3>
<input type="hidden" name="user_name" value=<%= request.getParameter("username") %>>
<input type="submit" value="Search">
</form>
</body>
</html>