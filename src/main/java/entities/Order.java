package entities;

import java.util.Date;

public class Order {
	
	private int orderNumber;
	private String userId;
	private Date date;
	
	// default constructor 
	public Order() {
		
	}
	
	// parameterized constructor
	public Order(int orderNumber, String userId, Date date) {
		super();
		this.orderNumber = orderNumber;
		this.userId = userId;
		this.date = date;
	}

	// getters and setters
	
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", userId=" + userId + ", date=" + date + "]";
	}
	
}
