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
  <%@ include file="header.jsp" %>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-6 my-5">
				<h2>Add product</h2>
				<form action="AddProduct" method="post">
					<div class="form-group">
						<label for="name">Product name</label> <input type="text"
							class="form-control" id="name" name="name"
							aria-describedby="name" placeholder="es. T-shirt">
					</div>
					<div class="form-group">
						<label for="price">Price</label> <input type="text"
							class="form-control" id="price" name="price"
							placeholder="es 19.99">
					</div>
					<div class="form-group">
						<select class="form-select" name="category"
							aria-label="Default select example">
							<option selected>Category</option>
							<option value="spring">Spring</option>
							<option value="summer">Summer</option>
							<option value="autumn">Autumn</option>
							<option value="winter">Winter</option>
						</select>
					</div>
					<div class="d-flex justify-content-between">
						<input type="submit" class="btn btn-primary" value="Add product">
					</div>
				</form>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
<script>
    <% 
    Boolean productAdded = (Boolean) request.getAttribute("productAdded");
    if (productAdded != null) { 
        if (!productAdded) { 
    %>
    alert("Unable to add the product, please try later.");
    <%  
        } else {
    %>
    alert("Product added successfully!");
    <% 
        }
    } 
    %>
</script>
</body>
</html>