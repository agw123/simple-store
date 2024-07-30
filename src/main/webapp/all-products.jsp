<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="bean.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Simple Store</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-6 my-5">
				<h1>Hello ${username}</h1>
				<h1>All products</h1>
				<%
				List<Product> productsList = (List<Product>) request.getAttribute("productsList");

				if (productsList != null && !productsList.isEmpty()) {
				%>
				<div class="row">
					<%
					for (Product product : productsList) {
						String productId = String.valueOf(product.getId());
					%>
					<div class="col-sm-4 mb-3">
						<div class="card">
							<div class="card-body">
								<h5 onclick="enableEdit('<%=productId%>')" class="card-title"
									id="product-name-<%=productId%>"><%=product.getName()%></h5>
								<form action="UpdateProduct" method="post"
									id="update-form-<%=productId%>" style="display: none;">
									<input type="hidden" name="productId" value="<%=productId%>">
									<input class="form-control" type="text" name="newName"
										value="<%=product.getName()%>">
									<button type="submit" class="btn btn-success mt-2">Save</button>
								</form>
								<p class="card-text">
									Category:<%
								out.println(product.getCategory());
								%>
								</p>
								<p class="card-text">
									Price:<%
								out.println(product.getPrice());
								%>
								</p>
								<p>
									Id: <small> <%
 out.println(product.getId());
 %></small>
								</p>
								<%
								//boolean isAdmin = false;
								if (session != null && session.getAttribute("admin") != null) {
									isAdmin = (Boolean) session.getAttribute("admin");
								}
								%>
								<%
								if (!isAdmin) {
								%>
								<form action="AddToCart" method="post">
									<input type="hidden" name="action" value="addProductToCart">
									<input type="hidden" name="productId" value='<%=productId%>'>
									<input type="submit" class="btn btn-primary"
										value="Add to cart">
								</form>
								<%
								} else {
								%>
								<div class="d-flex justify-content-between">
									<button class="btn btn-outline-warning"
										onclick="enableEdit('<%=productId%>')">Update</button>
									<form action="DeleteProduct" method="post">
										<input type="hidden" name="productId" value='<%=productId%>'>
										<input type="submit" class="btn btn-outline-secondary"
											value="Delete">
									</form>
								</div>
								<%
								}
								%>
							</div>
						</div>
					</div>
					<%
					}
					%>
				</div>
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
	<script>
	function enableEdit(productId) {
	    // Get the h5 element and form element by their IDs
	    var nameElement = document.getElementById("product-name-" + productId);
	    var formElement = document.getElementById("update-form-" + productId);

	    // Hide the h5 element
	    nameElement.style.display = 'none';
	    
	    // Show the form
	    formElement.style.display = 'block';
	}
	<%Boolean productDeleted = (Boolean) request.getAttribute("productDeleted");
if (productDeleted != null && !productDeleted) {%>
		alert("Unable to delete the product.");
	<%}%>
		
	</script>
</body>
</html>