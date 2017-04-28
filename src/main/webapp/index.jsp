
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Advert Application Lab8</title>
</head>
<body>

	<center><h1>Welcome to the NEU Store</h1>
	<h5>Place where you can buy different things for yourself or others</h5><br></center>
	<h3>Please login to continue ahead or if you are a new user kindly Sign Up by pressing the button below</h3>
	<a href="user/register.htm"><input type="submit" value="Sign Up"></a><br/><br><br>

	<h2>Login</h2>
	<form action="user/login" method="post">
	
		<table>
		
		<tr>
		    <td>User Name:</td>
		    <td><input name="username" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30" required="required"/></td>
		</tr>
		
		<tr>
		    <td colspan="2"><input type="submit" value="Login" /></td>
		</tr>
				
		</table>

	</form>


</body>
</html>

