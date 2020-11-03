<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<center>
<h1>Welcome <%= request.getParameter("username")%></h1>
<a href="/token">getTokan</a>
<a href="/logout">logout</a>
</form>
</center>

</body>
</html>