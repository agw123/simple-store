package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import bean.CartItem;
import bean.OrderDetails;
import bean.OrderItems;
import bean.Product;
import bean.ShoppingSession;
import bean.User;
import database.ConnectDB;

public class DAOimpl implements DAO {

	// Login and registration queries - move to LoginDAO

	private final String LOGIN_SQL = "SELECT id, username, userType FROM User WHERE username = ? AND password = ?;";

	private static final String REGISTER_SQL = "INSERT INTO User (username, password, userType) VALUES (?, ?, ?);";

	// Product - static data queries: SELECT - the products are changed rarely; ONLY

	private static final String SELECT_PRODUCT_BY_ID = "SELECT id, name, price, category FROM Product WHERE id = ?;";

	private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM Product;";

	private static final String INSERT_PRODUCT = "INSERT INTO Product (name, price, category) VALUES (?, ?, ?);";

	private static final String UPDATE_PRODUCT_NAME = "UPDATE Product SET name = ? WHERE id = ?;";

	private static final String DELETE_PRODUCT = "DELETE FROM Product WHERE id = ?;";

	// Cart - session data queries - very dynamic, lots of changes

	private static final String INSERT_SHOPPING_SESSION = "INSERT INTO shopping_session (userId, total, createdAt) VALUES (?, ?, ?);";

	private static final String SELECT_SHOPPING_SESSION = "SELECT id, userId, total, createdAt from shopping_session WHERE userId = ?;";

	private static final String UPDATE_SHOPPING_SESSION = "UPDATE shopping_session SET total = ?, createdAt = ? WHERE id = ?;";

	private static final String INSERT_CART_ITEM = "INSERT INTO cart_item (shoppingSessionId, productId, quantity) VALUES (?, ?, ?);";

	private static final String SELECT_CART_ITEM = "SELECT id, shoppingSessionId, productId, quantity from cart_item WHERE shoppingSessionId = ?;";

	private static final String SELECT_CART_ITEM_BY_PRODUCT = "SELECT id, shoppingSessionId, productId, quantity FROM cart_item WHERE productId = ? AND shoppingSessionId = ?";

	private static final String UPDATE_CART_ITEM = "UPDATE cart_item SET quantity = ? WHERE productId = ? AND shoppingSessionId = ?;";

	// Order - static data no way to update

	private static final String INSERT_ORDER_DETAILS = "INSERT INTO order_details (customerId, paymentId, total, date, quantity) VALUES (?, ?, ?, ?, ?);";

	private static final String INSERT_ORDER_ITEMS = "INSERT INTO order_items (orderId, productId, quantity) VALUES (?, ?, ?);";

	private static final String SELECT_ORDER_DETAILS = "SELECT id, customerId, total, paymentId, date, quantity FROM order_details WHERE customerId = ? AND date = ?";

	private static final String SELECT_ORDER_ITEMS = "SELECT productId, orderId, quantity FROM order_items WHERE id = ?;";

	protected ConnectDB connectDB = new ConnectDB();
	protected Connection connection = null;
	protected PreparedStatement pr = null;
	protected ResultSet rs = null;

	// User

	@Override
	public User login(String username, String password) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(LOGIN_SQL)) {
			pr.setString(1, username);
			pr.setString(2, password);
			try (ResultSet rs = pr.executeQuery()) {
				if (rs.next()) {
					return new User(Long.parseLong(rs.getString("id")), rs.getString("username"),
							rs.getInt("userType"));
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User register(String username, String password) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(REGISTER_SQL)) {
			pr.setString(1, username);
			pr.setString(2, password);
			pr.setInt(3, 0);
			int rowsAffected = pr.executeUpdate();
			return login(username, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Product

	@Override
	public ArrayList<Product> getAllProducts() {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
			try (ResultSet rs = pr.executeQuery()) {
				ArrayList<Product> productsList = new ArrayList<>();
				while (rs.next()) {
					Product product = new Product(Long.parseLong(rs.getString("id")), rs.getString("name"),
							Double.parseDouble(rs.getString("price")), rs.getString("category"));
					productsList.add(product);
				}
				return productsList;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Product findProductById(long productId) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
			pr.setLong(1, productId);
			try (ResultSet rs = pr.executeQuery()) {
				if (rs.next()) {
					return new Product(Long.parseLong(rs.getString("id")), rs.getString("name"),
							Double.parseDouble(rs.getString("price")), rs.getString("category"));
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int addProduct(Product product) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(INSERT_PRODUCT)) {
			pr.setString(1, product.getName());
			pr.setDouble(2, product.getPrice());
			pr.setString(3, product.getCategory());
			int rowsAffected = pr.executeUpdate();
			return rowsAffected;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Product updateProductName(long productId, String newName) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(UPDATE_PRODUCT_NAME)) {
			pr.setString(1, newName);
			pr.setLong(2, productId);
			pr.executeUpdate();
			return findProductById(productId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteProduct(long productId) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(DELETE_PRODUCT)) {
			pr.setLong(1, productId);
			int rowsAffected = pr.executeUpdate();
			return rowsAffected;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Cart and session

	@Override
	public void createShoppingSession(ShoppingSession shoppingSession) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(INSERT_SHOPPING_SESSION)) {

			pr.setLong(1, shoppingSession.getUserId());
			pr.setDouble(2, shoppingSession.getTotal());
			pr.setDate(3, java.sql.Date.valueOf(shoppingSession.getCreatedAt()));

			pr.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void createCartItem(CartItem cartItem) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(INSERT_CART_ITEM)) {
			pr.setLong(1, cartItem.getShoppingSessionId());
			pr.setLong(2, cartItem.getProductId());
			pr.setInt(3, cartItem.getQuantity());
			pr.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ShoppingSession retrieveShoppingSession(long customerId) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(SELECT_SHOPPING_SESSION)) {
			pr.setLong(1, customerId);
			try (ResultSet rs = pr.executeQuery()) {
				if (rs.next()) {
					long id = rs.getLong("id");
					long userId = rs.getLong("userId");
					double total = rs.getDouble("total");
					LocalDate createdAt = rs.getDate("createdAt").toLocalDate();
					return new ShoppingSession(id, userId, total, createdAt);
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<CartItem> retrieveCartItem(long shoppingSessionId) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(SELECT_CART_ITEM)) {
			pr.setLong(1, shoppingSessionId);
			try (ResultSet rs = pr.executeQuery()) {
				ArrayList<CartItem> cartItems = new ArrayList<>();
				while (rs.next()) {
					cartItems.add(new CartItem(Long.parseLong(rs.getString("id")), Long.parseLong(rs.getString("shoppingSessionId")),
							Long.parseLong(rs.getString("productId")), rs.getInt("quantity")));
				}
				return cartItems;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public CartItem retrieveCartItemByProduct(long productId, long shoppingSessionId) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(SELECT_CART_ITEM_BY_PRODUCT)) {
			pr.setLong(1, productId);
			pr.setLong(2, shoppingSessionId);
			try (ResultSet rs = pr.executeQuery()) {
				if (rs.next()) {
				       return new CartItem(
			                    rs.getLong("id"),
			                    rs.getLong("shoppingSessionId"),
			                    rs.getLong("productId"),
			                    rs.getInt("quantity")
			                );
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void updateCartItemQuantity(long productId, long shoppingSessionId) {
	    String selectSql = "SELECT quantity FROM cart_item WHERE productId = ? AND shoppingSessionId = ?";
	    String updateSql = "UPDATE cart_item SET quantity = ? WHERE productId = ? AND shoppingSessionId = ?";
	    String deleteSql = "DELETE FROM cart_item WHERE productId = ? AND shoppingSessionId = ?";

	    try (Connection connection = connectDB.getConnection();
	         PreparedStatement selectPstmt = connection.prepareStatement(selectSql);
	         PreparedStatement updatePstmt = connection.prepareStatement(updateSql);
	         PreparedStatement deletePstmt = connection.prepareStatement(deleteSql)) {

	        // Check the current quantity
	        selectPstmt.setLong(1, productId);
	        selectPstmt.setLong(2, shoppingSessionId);

	        try (ResultSet rs = selectPstmt.executeQuery()) {
	            if (rs.next()) {
	                int currentQuantity = rs.getInt("quantity");
	                int newQuantity = currentQuantity - 1;
	                if (newQuantity > 0) {
	                    updatePstmt.setInt(1, newQuantity);
	                    updatePstmt.setLong(2, productId);
	                    updatePstmt.setLong(3, shoppingSessionId);
	                    updatePstmt.executeUpdate();
	                } else {
	                    deletePstmt.setLong(1, productId);
	                    deletePstmt.setLong(2, shoppingSessionId);
	                    deletePstmt.executeUpdate();
	                }
	            } else {
	                throw new RuntimeException("Cart item not found");
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Error updating cart item quantity", e);
	    }
	}

	@Override
	public ShoppingSession updateShoppingSession(long sessionId, double total, LocalDate createdAt) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(UPDATE_SHOPPING_SESSION)) {
			pr.setDouble(1, total);
			pr.setDate(2, java.sql.Date.valueOf(createdAt));
			pr.setLong(3, sessionId);
			pr.executeUpdate();
			ShoppingSession shoppingSession = retrieveShoppingSession(sessionId);
			return shoppingSession;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateCartItem(int quantity, long productId, long shoppingSessionId) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(UPDATE_CART_ITEM)) {
			pr.setInt(1, quantity);
			pr.setLong(2, productId);
			pr.setLong(3, shoppingSessionId);
			pr.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Order

	@Override
	public int createOrderDetails(OrderDetails order) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(INSERT_ORDER_DETAILS)) {
			pr.setLong(1, order.getCustomerId());
			pr.setLong(2, order.getPaymentId());
			pr.setDouble(3, order.getTotal());
			pr.setDate(4, java.sql.Date.valueOf(order.getDate()));
			pr.setInt(5, order.getQuantity());
			
			int rowsAffected = pr.executeUpdate();
			return rowsAffected;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int createOrderItems(OrderItems orderByProduct) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(INSERT_ORDER_ITEMS)) {
			pr.setDouble(1, orderByProduct.getOrderId());
			pr.setLong(2, orderByProduct.getProductId());
			pr.setLong(3, orderByProduct.getQuantity());
			int rowsAffected = pr.executeUpdate();
			return rowsAffected;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public OrderDetails retrieveOrderDetails(long customerId, LocalDate localDate) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(SELECT_ORDER_DETAILS)) {
			pr.setLong(1, customerId);
			pr.setDate(2, java.sql.Date.valueOf(localDate));
			try (ResultSet rs = pr.executeQuery()) {
				if (rs.next()) {
					return new OrderDetails(Long.parseLong(rs.getString("id")), Long.parseLong(rs.getString("customerId")),
							Long.parseLong(rs.getString("paymentId")), Double.parseDouble(rs.getString("total")),
							rs.getInt("quantity"), rs.getDate("date").toLocalDate());
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public OrderItems retrieveOrderItems(long id) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(SELECT_ORDER_ITEMS)) {
			pr.setLong(1, id);
			try (ResultSet rs = pr.executeQuery()) {
				if (rs.next()) {
					return new OrderItems(Long.parseLong(rs.getString("orderId")),
							Long.parseLong(rs.getString("productId")), rs.getInt("quantity"));
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
