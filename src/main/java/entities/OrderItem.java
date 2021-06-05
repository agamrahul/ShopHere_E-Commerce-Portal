package entities;

public class OrderItem {
	
	private int orderNumber;
	private int productId;
	private String productName;
	private int quantity;
	private double price;
	
	// default constructor
	public OrderItem() {
		
	}
	// parameterized constructor
	public OrderItem(int orderNumber, int productId, String productName, int quantity, double price) {
		super();
		this.orderNumber = orderNumber;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}
	
	// getters and setters 
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		return "orderItem [orderNumber=" + orderNumber + ", productId=" + productId + ", productName=" + productName
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}
	
}
