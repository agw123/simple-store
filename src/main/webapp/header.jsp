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
<title>Bootstrap demo</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script>
	function goTo(locUrl) {
		window.location.href = locUrl + ".jsp";
	}
</script>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid justify-content-between">
			<a class="navbar-brand" href="#">Simple Store</a>
			<div>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<%
						boolean isAdmin = false;
						if (session != null && session.getAttribute("admin") != null) {
							isAdmin = (Boolean) session.getAttribute("admin");
						}
						%>
						<%
						if (isAdmin) {
						%>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="dashboard.jsp">Dashboard</a></li>
						<li class="nav-item"><form action="all-products" method="get">
								<input class="nav-link active" type="submit"
									value="All products">
							</form></li>
							<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="AddProduct">Add product</a></li>
						<%
						} else if (!isAdmin){
						%>
						<li class="nav-item"><form action="AddToCart" method="post">
								<input type="hidden" name="action" value="goToCart">
								<input class="nav-link active" type="submit" value="Cart">
							</form></li>
						<%
						}
						%>
						<form action="Login" method="post">
							<input type="hidden" name="action" value="logout"> <input
								class="btn btn-outline-success" type="submit" value="Logout">
						</form>
				</div>
			</div>
		</div>
	</nav>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>