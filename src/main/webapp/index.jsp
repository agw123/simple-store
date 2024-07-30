<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Simple Store</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-6 my-5">
				<h1>WELCOME TO SIMPLE STORE</h1>
				<h2>Login</h2>
				<form action="Login" method="post">
					<input type="hidden" name="action" value="login">
					<div class="form-group">
						<label for="username">Username</label> <input type="text"
							class="form-control" id="username" name="username"
							aria-describedby="username" placeholder="Enter username" value='<%=request.getParameter("username")!=null ? request.getParameter("username") : ""%>'>
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input
							type="password" class="form-control" id="password"
							name="password" placeholder="Enter password" value='<%=request.getParameter("password")!=null ? request.getParameter("password") : ""%>'>
					</div>
					<input type="submit" class="btn btn-primary" value="Login">
				</form>
				<%
				boolean user = false;
				Boolean loginAttemptObj = (Boolean) request.getAttribute("loginAttempt");
				boolean loginAttempt = (loginAttemptObj != null) ? loginAttemptObj : false;
				%>
				<%
				if (loginAttempt==true) {
				%>
				<p>You're not registered yet. Would you like to register?</p>
				<form action="Login" method="post">
					<input type="hidden" name="action" value="register"> 
					<input type="hidden"
							class="form-control" id="username" name="username"
							aria-describedby="username" placeholder="Enter username" value='<%=request.getParameter("username")%>'> 
					<input type="hidden" class="form-control" id="password"
						name="password" placeholder="Enter password"
						value='<%=request.getParameter("password")%>'> <input
						type="submit" class="btn btn-primary" value="Register">
				</form>
				<%
				}
				%>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>