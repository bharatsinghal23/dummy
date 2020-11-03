<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<center>
<h1>Welcome</h1>

<h2>Login Page</h2>

<form method="GET" action="/home">
	User Name : <input type="text" name="username" value="user"/><br><br>
	Password  : <input type="password" name="password" value="password"/><br><br>
	Mobile No  : <input type="number" name="number" value="number"/><br><br>
	<input type="submit" name="submit"/>
</form>
</center>

</body>
</html>