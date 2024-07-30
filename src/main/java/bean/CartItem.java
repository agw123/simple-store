package bean;

public class CartItem {
	
	private long id;
	private long shoppingSessionId;
	private long productId;
	private int quantity;
	
	public CartItem() {}
	
	public CartItem(long shoppingSessionId, long productId, int quantity) {
		this.shoppingSessionId = shoppingSessionId;
		this.productId = productId;
		this.quantity = quantity;
	}


	public CartItem(long id, long shoppingSessionId, long productId, int quantity) {
		this.id = id;
		this.shoppingSessionId = shoppingSessionId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getShoppingSessionId() {
		return shoppingSessionId;
	}

	public void setShoppingSessionId(long shoppingSessionId) {
		this.shoppingSessionId = shoppingSessionId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", ShoppingSessionId=" + shoppingSessionId + ", ProductId=" + productId
				+ ", quantity=" + quantity + "]";
	}
	
	
}
