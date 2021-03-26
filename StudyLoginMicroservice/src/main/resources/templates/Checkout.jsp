<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout</title>
</head>
<body>
<div align="right">
<form action="/login/logout" id="logoutForm" name="logoutForm" method="post">
	<input  type="submit" name="logout" value="Logout" onclick="javascript: document.logoutForm.submit();"></input>
</form>
</div>
	<%= request.getAttribute("message").toString()%>
</body>
</html>