package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Product;
import dao.DAOimpl;

/**
 * Servlet implementation class AllProducts
 */
@WebServlet("/all-products")
public class AllProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOimpl daoImpl = new DAOimpl();
		   List<Product> productsList = new ArrayList<>();
		    
		    // Assume you have a method in your DAO to get all products
		    productsList = daoImpl.getAllProducts();
		    
		    // Check if productsList is null, if so initialize it to an empty list
		    if (productsList == null) {
		        productsList = new ArrayList<>();
		    }

		    request.setAttribute("productsList", productsList);
		    request.getRequestDispatcher("all-products.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
