package bean;

import java.time.LocalDate;

public class OrderDetails {
	private long id;
	private long customerId;
	private long paymentId;
	private double total;
	private int quantity;
	private LocalDate date;
	
	public OrderDetails() {
	}

	public OrderDetails(long customerId, long paymentId, double total, int quantity, LocalDate date) {
		this.customerId = customerId;
		this.paymentId = paymentId;
		this.total = total;
		this.quantity = quantity;
		this.date = date;
	}

	public OrderDetails(long id, long customerId, long paymentId, double total, int quantity, LocalDate date) {
		this.id = id;
		this.customerId = customerId;
		this.paymentId = paymentId;
		this.total = total;
		this.quantity = quantity;
		this.date = date;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}

	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", total=" + total + ", quantity=" + quantity
				+ "]";
	}
}
