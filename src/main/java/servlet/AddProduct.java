package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Product;
import bean.User;
import service.Admin;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("add-product.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productName = request.getParameter("name");
		String price = request.getParameter("price");
		String category = request.getParameter("category");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user.getUserType() == 1) {
			Admin admin = new Admin(user.getId(), user.getUsername());
			int addProduct = admin.addProduct(new Product(productName, Double.parseDouble(price), category));
	        if (addProduct == 1) {
	            request.setAttribute("productAdded", true);
	            request.getRequestDispatcher("add-product.jsp").forward(request, response);
	            return;
	        } else {
	            request.setAttribute("productAdded", false);
	        }
	    } else {
	        request.setAttribute("productAdded", false);
	    }
	    request.getRequestDispatcher("add-product.jsp").forward(request, response);
	}
}
