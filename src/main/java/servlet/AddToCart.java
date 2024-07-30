package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartItem;
import bean.Product;
import bean.ShoppingSession;
import bean.User;
import service.Customer;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productId = request.getParameter("productId");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user.getUserType() == 0) {
			Customer customer = new Customer(user.getId(), user.getUsername());
			
			switch (action) {
			case "addProductToCart":
                ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("productsAddedList");
                double total = 0.0;
                if (products == null) {
                    products = new ArrayList<>();
                }

                Product product = customer.findProduct(Long.parseLong(productId));

                if (product != null) {
                    products.add(product);
                }

                Map<Long, Integer> productCountMap = new HashMap<>();
                for (Product p : products) {
                    total += p.getPrice();
                    productCountMap.put(p.getId(), productCountMap.getOrDefault(p.getId(), 0) + 1);
                }

                session.setAttribute("productsAddedList", products);
                session.setAttribute("productCountMap", productCountMap);

                long customerId = customer.getId();

                ShoppingSession shoppingSession = customer.retrieveShoppingSession(customerId);

                if (shoppingSession == null) {
                    customer.createShoppingSession(customerId, total, LocalDate.now());
                    shoppingSession = customer.retrieveShoppingSession(customerId);
                } else {
                    customer.updateShoppingSession(shoppingSession.getId(), total, LocalDate.now());
                }

                CartItem cartItem = customer.retrieveCartItemByProduct(Long.parseLong(productId), shoppingSession.getId());

                if (cartItem == null) {
                    customer.createCartItem(shoppingSession.getId(), Long.parseLong(productId), 1); // Assuming quantity of 1 for add to cart
                } else {
                    customer.updateCartItem(cartItem.getQuantity() + 1, Long.parseLong(productId), shoppingSession.getId());
                }
				response.sendRedirect("all-products");
				break;
			case "goToCart":
                ShoppingSession shoppingSessionRetrieved = customer.retrieveShoppingSession(customer.getId());
                if (shoppingSessionRetrieved != null) {
                    ArrayList<CartItem> cartItems = customer.retrieveCartItem(shoppingSessionRetrieved.getId());
                    ArrayList<Product> productsAddedList = new ArrayList<>();
                 
                    Map<Long, Integer> productCountMapRetrieved = new HashMap<>();
                    total = 0.0;
                    for (CartItem item : cartItems) {
                        Product p = customer.findProduct(item.getProductId());
                        if (p != null) {
                            productsAddedList.add(p);
                            total += p.getPrice() * item.getQuantity();
                            productCountMapRetrieved.put(p.getId(), item.getQuantity());
                        }
                    }

                    session.setAttribute("productsAddedList", productsAddedList);
                    session.setAttribute("productCountMap", productCountMapRetrieved);
                }
				request.getRequestDispatcher("cart.jsp").forward(request, response);
				break;
			case "DeleteFromCart":
			    ShoppingSession sessionToDeleteFrom = customer.retrieveShoppingSession(customer.getId());
			    if (sessionToDeleteFrom != null) {
			        customer.updateCartItemQuantity(Long.parseLong(productId), sessionToDeleteFrom.getId());
			    }
				request.getRequestDispatcher("cart.jsp").forward(request, response);
				break;
			}

		}

	}

}
