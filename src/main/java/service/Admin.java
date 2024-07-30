package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.Product;
import bean.User;
import dao.DAOimpl;

public class Admin extends User {
	
	DAOimpl daoImpl = new DAOimpl();

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(long id, String username, int userType) {
		super(id, username, userType);
		// TODO Auto-generated constructor stub
	}

	public Admin(long id, String username, String password, int userType) {
		super(id, username, password, userType);
		// TODO Auto-generated constructor stub
	}
	
	public Admin(long id, String username) {
		super(id, username);
	}
	
	public int addProduct(Product product) {
		return daoImpl.addProduct(product);
	}

	public Product updateProductName(long productId, String newName) {
		return daoImpl.updateProductName(productId, newName);
	}

	public int deleteProduct(long productId) {
		return daoImpl.deleteProduct(productId);
	}

}
