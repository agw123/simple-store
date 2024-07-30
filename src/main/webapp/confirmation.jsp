<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Simple Store</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script>
	function goTo(locUrl) {
		window.location.href = locUrl + ".jsp";
	}
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-6 my-5">
				<h1>Are you sure you want to confirm the order?</h1>
				<div class="d-flex justify-content-between">
					<form action="ConfirmOrder" method="post">
						<input type="hidden" name="action" value="submit"> <input
							type="submit" class="btn btn-outline-warning" value="Confirm order">
					</form>
					<form action="ConfirmOrder" method="post">
						<input type="submit" class="btn btn-outline-secondary"
							value="Cancel order">
					</form>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>