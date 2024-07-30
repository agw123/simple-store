package service;

import java.time.LocalDate;
import java.util.ArrayList;

import bean.CartItem;
import bean.OrderDetails;
import bean.OrderItems;
import bean.Product;
import bean.ShoppingSession;
import bean.User;
import dao.DAOimpl;

public class Customer extends User {

	long id;
	String username;
	DAOimpl daoImpl = new DAOimpl();

	public Customer() {}

	public Customer(long id, String username) {
		super(id, username);
	}

	public Customer(long id, String username, String password, int userType) {
		super(id, username, password, userType);
	}
	

	public void createShoppingSession(long userId, double total, LocalDate createdAt) {
		daoImpl.createShoppingSession(new ShoppingSession(userId, total, createdAt));
	}
	
	public ShoppingSession retrieveShoppingSession(long customerId) {
		return daoImpl.retrieveShoppingSession(customerId);
	}
	
	public ShoppingSession updateShoppingSession(long sessionId, double total, LocalDate createdAt) {
		return daoImpl.updateShoppingSession(sessionId, total, createdAt);
	}

	public void createCartItem(long shoppingSessionId, long productId, int quantity) {
		daoImpl.createCartItem(new CartItem(shoppingSessionId, productId, quantity));
	}
	
	public ArrayList<CartItem> retrieveCartItem(long shoppingSessionId) {
		return daoImpl.retrieveCartItem(shoppingSessionId);
	}
	
	public CartItem retrieveCartItemByProduct(long productId, long shoppingSessionId) {
		return daoImpl.retrieveCartItemByProduct(productId, shoppingSessionId);
	}
	
	public void updateCartItem(int quantity, long productId, long shoppingSessionId) { 
		daoImpl.updateCartItem(quantity, productId, shoppingSessionId);
	}
	
	public void updateCartItemQuantity(long productId, long shoppingSessionId) {
		daoImpl.updateCartItemQuantity(productId, shoppingSessionId);
	}
	
	
	public int  createOrderDetails(long customerId, long paymentId, double total, int quantity, LocalDate date) {
		return daoImpl.createOrderDetails(new OrderDetails(customerId, paymentId, total, quantity, date));
	}
	
	public int createOrderItems(long orderId, long productId, int quantity ) {
		return daoImpl.createOrderItems(new OrderItems(orderId, productId, quantity ));
	}
	
	public OrderDetails retrieveOrderDetails(long customerId, LocalDate localDate) {
		return daoImpl.retrieveOrderDetails(customerId, localDate);
	}
	
	public OrderItems retrieveOrderItems(long id) {
		return daoImpl.retrieveOrderItems(id);
	}
	
	public void addToCart(ArrayList<Product> products, long productId, long customerId) {
		Product product = findProduct(productId);
		products.add(product);
	}

	public Product findProduct(long id) {
		return daoImpl.findProductById(id);
	}

	public ArrayList<Product> addProductToOrder(Product product) {
		ArrayList<Product> products = new ArrayList<>();
		products.add(product);
		return products;
	}

}
