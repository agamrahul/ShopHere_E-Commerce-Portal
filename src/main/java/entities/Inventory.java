package entities;

public class Inventory {

	private int productId;
	private String productName;
	private double price;
	private int quantity;
	
	// default constructor 
	public Inventory() {
		
	}
	// parameterized constructor
	public Inventory(int productId, String productName, double price, int quantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}
	
	// getters and setters
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	@Override
	public String toString() {
		
		return  "\nPRODUCT ID = " + productId + "\t | \t" + "PRODUCT NAME = " + productName + 
				"\nPRICE = " + price + "\t | \t" + "QUANTITY = " + quantity;
	}
	
	
	
	
}
