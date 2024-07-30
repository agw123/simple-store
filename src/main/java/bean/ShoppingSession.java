package bean;

import java.time.LocalDate;

public class ShoppingSession {

	private long id;
	private long userId;
	private double total;
	private LocalDate createdAt;
	
	public ShoppingSession() {}
	
	public ShoppingSession(long userId, double total, LocalDate createdAt) {
		this.userId = userId;
		this.total = total;
		this.createdAt = createdAt;
	}

	public ShoppingSession(long id, long userId, double total, LocalDate createdAt) {
		this.id = id;
		this.userId = userId;
		this.total = total;
		this.createdAt = createdAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "ShoppingSession [id=" + id + ", UserId=" + userId + ", total=" + total + ", createdAt=" + createdAt
				+ "]";
	}
	

}
