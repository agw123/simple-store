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
 * Servlet implementation class DeleteProduct
 */
@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user.getUserType() == 1) {
			Admin admin = new Admin(user.getId(), user.getUsername());
			int deletedProduct = admin.deleteProduct(Long.parseLong(productId));
	        if (deletedProduct == 1) {
	            request.setAttribute("productDeleted", true);
	            response.sendRedirect("all-products");
	            return;
	        } else {
	            request.setAttribute("productDeleted", false);
	        }
	    } else {
	        request.setAttribute("productDeleted", false);
	    }
	    request.getRequestDispatcher("all-products.jsp").forward(request, response);
	}

}
