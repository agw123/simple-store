package bean;

public class OrderItems {
	
	private long id;
	private long productId;
	private long orderId;
	private int quantity;
	
	public OrderItems() {
	}

	public OrderItems(long id, long productId, long orderId, int quantity) {
		this.id = id;
		this.productId = productId;
		this.orderId = orderId;
		this.quantity = quantity;
	}

	public OrderItems(long productId, long orderId, int quantity) {
		this.productId = productId;
		this.orderId = orderId;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderByProduct [id=" + id + ", productId=" + productId + ", orderId=" + orderId + ", quantity="
				+ quantity + "]";
	}


}
