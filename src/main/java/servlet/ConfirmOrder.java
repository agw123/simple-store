package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartItem;
import bean.OrderDetails;
import bean.OrderItems;
import bean.Product;
import bean.ShoppingSession;
import bean.User;
import service.Customer;

/**
 * Servlet implementation class ConfirmOrder
 */
@WebServlet("/ConfirmOrder")
public class ConfirmOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("productsAddedList");
		Customer customer = new Customer(user.getId(), user.getUsername());

		switch (action) {
		case "submit":

			long paymentId = UUID.randomUUID().getMostSignificantBits();
			ShoppingSession shoppingSession = customer.retrieveShoppingSession(customer.getId());
			ArrayList<CartItem> cartItems = customer.retrieveCartItem(shoppingSession.getId());
			int totalQuantity = 0;

			for (CartItem cart : cartItems) {
				totalQuantity = totalQuantity + cart.getQuantity();
			}
			int orderdCreated = customer.createOrderDetails(customer.getId(), paymentId, shoppingSession.getTotal(), totalQuantity,
					LocalDate.now());
			OrderDetails orderDetails = customer.retrieveOrderDetails(customer.getId(), LocalDate.now());
			for (CartItem cart : cartItems) {
				customer.createOrderItems(orderDetails.getId(), cart.getProductId(), totalQuantity);
			}
			if (user.getUserType() == 1) {
				if (orderdCreated == 1) {
					request.setAttribute("orderdCreated", true);
					request.getRequestDispatcher("all-product.jsp").forward(request, response);
					return;
				} else {
					request.setAttribute("orderdCreated", false);
				}
			} else {
				request.setAttribute("orderdCreated", false);
			}

			request.getRequestDispatcher("all-product.jsp").forward(request, response);
			break;
		case "cancel":
			session.setAttribute("productsAddedList", new ArrayList<>());
			request.getRequestDispatcher("all-products.").forward(request, response);
			break;
		}
	}

}
