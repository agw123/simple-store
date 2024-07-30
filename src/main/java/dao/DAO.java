package dao;

import java.time.LocalDate;
import java.util.ArrayList;

import bean.CartItem;
import bean.OrderDetails;
import bean.OrderItems;
import bean.Product;
import bean.ShoppingSession;
import bean.User;

public interface DAO {
	
	// User

	User login(String username, String password);

	User register(String username, String password);
	
	// Products

	ArrayList<Product> getAllProducts();

	Product findProductById(long productId);
	
	int addProduct(Product product);
	
	Product updateProductName(long productId, String newName);

	int deleteProduct(long productId);

	// Cart and session

	void createShoppingSession(ShoppingSession shoppingSession);

	void createCartItem(CartItem cartItem);

	ShoppingSession retrieveShoppingSession(long customerId);

	ArrayList<CartItem> retrieveCartItem(long shoppingSessionId);
	
	CartItem retrieveCartItemByProduct(long productId, long shoppingSessionId);
	
	public void updateCartItemQuantity(long productId, long shoppingSessionId);
	
	ShoppingSession updateShoppingSession(long id, double total, LocalDate createdAt);

	void updateCartItem(int quantity, long productId, long shoppingSessionId);
	
	// Order
	
	int createOrderDetails(OrderDetails order);

	int createOrderItems(OrderItems orderByProduct);
	
	OrderDetails retrieveOrderDetails(long customerId, LocalDate localDate);
	
	OrderItems retrieveOrderItems(long id);
	
}
