package servlet;

import java.io.IOException;
import java.util.Locale;
import java.util.stream.Stream;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.DAOimpl;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	User currentUser;
	HttpSession session = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		session.invalidate();
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sUser = request.getParameter("username");
		String sPassword = request.getParameter("password");
		String action = request.getParameter("action");
		
		DAOimpl daoImpl = new DAOimpl();

		request.setAttribute("loginAttempt", false);
		

		switch (action) {
		case "login":
			User currentUser = daoImpl.login(sUser, sPassword);
			if (currentUser != null) {
				session = request.getSession();
				session.setAttribute("user", currentUser);
				session.setAttribute("username", currentUser.getUsername());

				boolean admin = currentUser.getUserType() == 1 ? true : false;
				session.setAttribute("admin", admin);
				if (admin) {
					request.getRequestDispatcher("dashboard.jsp").forward(request, response);
				} else {
					response.sendRedirect("all-products");
				}
			} else if (currentUser == null) {
				request.setAttribute("loginAttempt", true);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			break;
		case "register":
			User newUser = daoImpl.register(sUser, sPassword);
			session = request.getSession();
			session.setAttribute("user", newUser);
			session.setAttribute("username", newUser.getUsername());

			boolean admin = newUser.getUserType() == 1 ? true : false;
			session.setAttribute("admin", admin);
			if (admin) {
				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			} else {
				response.sendRedirect("all-products");
			}
			break;
		case "logout":
				session = request.getSession();
			    session.invalidate();
				response.sendRedirect("index.jsp");
				break;
		}

	}

}
