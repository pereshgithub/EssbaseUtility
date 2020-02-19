<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enter ESSBASE Details</title>
</head>
<body>

	<form action="essconnect" method="post">
	<pre>
		<label for="URL">ESSBASE Url</label> <input type="text" name="url" value="" class="form-control" id="url" placeholder="Enter ESSBASE Url">
		<label for="olapServer">OLAPServer</label> <input type="text" name = "olapServer" class="form-control" id="olapServer" placeholder="Enter OLAPServer name">	
		<label for="usernameId">Username</label> <input type="text" name="username" value="" class="form-control" id="username" placeholder="Enter Username">
		<label for="pwdId">Password</label> <input type="password" name="password" value="" class="form-control" id="password" placeholder="Enter Password">
		<input type="submit" value= "Connect" />
	</pre>
	</form>

</body>
</html>