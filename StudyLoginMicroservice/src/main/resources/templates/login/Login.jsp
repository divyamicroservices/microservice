<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login JSP</title>
</head>
<body >

<form action="/login/authenticate" method="post">
	<div align="center">
	<h1 align="center">Login Page </h1>
	<table >
	<tr><td><input type="text" name="username"></td></tr>
	<tr><td><input type="password" name="password"></td></tr>
	
	<tr><td align="center"><input  type="submit" name="login"></td></tr>
	
	</table>
	</div>
</form>
</body>
</html>