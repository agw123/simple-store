<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="bean.Product"%>

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
<style>
.back-arrow {
	font-weight: bold;
	font-size: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="mt-5">
			<a class="back-arrow" href="all-products"><span>&#8592;</span>Back</a>
		</div>
		<div class="row justify-content-center">
			<div class="col-7 my-5">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Your cart</h5>
						<%
						ArrayList<Product> productsAddedList = (ArrayList<Product>) session.getAttribute("productsAddedList");
						HashMap<Long, Integer> productCountMap = (HashMap<Long, Integer>) session.getAttribute("productCountMap");
						double totalPrice = 0.0;

						if (productsAddedList != null && !productsAddedList.isEmpty() && productCountMap != null) {
						%>
						<table class="table table-hover">
							<thead>
								<tr>
									<th scope="col">Id</th>
									<th scope="col">Name</th>
									<th scope="col">Category</th>
									<th scope="col">Price</th>
									<th scope="col">Quantity</th>
									<th scope="col">Delete from cart</th>
								</tr>
							</thead>
							<tbody>
								<%
								for (Map.Entry<Long, Integer> entry : productCountMap.entrySet()) {
									long productId = entry.getKey();
									int productCount = entry.getValue();
									Product product = null;

									for (Product p : productsAddedList) {
										if (p.getId() == productId) {
									product = p;
									break;
										}
									}

									if (product != null) {
										double productPrice = product.getPrice() * productCount;
										totalPrice += productPrice;
								%>
								<tr>
									<th scope="row"><%=productId%></th>
									<td><%=product.getName()%></td>
									<td><%=product.getCategory()%></td>
									<td><%=productPrice%></td>
									<td><%=productCount%></td>
									<td>
										<form action="AddToCart" method="post">
											<input type="hidden" name="action" value="DeleteFromCart">
											<input type="hidden" name="productId" value='<%=productId%>'>
											<input type="submit" class="btn btn-primary-outline"
												value="Delete">
										</form>
									</td>
								</tr>
								<%
									}
								}
								%>
							</tbody>
						</table>
						<hr />
						<strong class="my-5">Total price: <%=totalPrice%></strong>
						<div class="row justify-content-center">
							<div class="col-6 my-5">
								<div class="d-flex justify-content-between">
									<form action="ConfirmOrder" method="post">
										<input type="hidden" name="action" value="submit"> <input
											type="submit" class="btn btn-outline-warning"
											value="Confirm order">
									</form>
									<form action="ConfirmOrder" method="post">
										<input type="hidden" name="action" value="cancel"> <input
											type="submit" class="btn btn-outline-secondary"
											value="Cancel order">
									</form>
								</div>
							</div>
						</div>
						<%
						} else {
						%>
						<p>Your cart is empty</p>
						<%
						}
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
		<script>
    <% 
    Boolean orderdCreated = (Boolean) request.getAttribute("orderdCreated");
    if (orderdCreated != null) { 
        if (!orderdCreated) { 
    %>
    alert("Unable to place the order, please try later.");
    <%  
        } else {
    %>
    alert("Order placed successfully!");
    <% 
        }
    } 
    %>
</script>
</body>
</html>