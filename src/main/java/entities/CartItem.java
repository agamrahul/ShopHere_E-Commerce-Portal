package entities;
import java.io.Serializable;
import java.text.NumberFormat;

/*
 * This class represents an item in user cart.
 * It has the product and the quantity of the product.
 */
public class CartItem implements Serializable {

    private Inventory product;
    private int quantity;

    // default constructor 
    public CartItem() {
    	
    }
    // parameterized constructor 
    public CartItem(Inventory item, int quantity) {
    	this.product = item;
    	this.quantity = quantity;
    }
    
    // setters and getters
    public void setProduct(Inventory p) {
        product = p;
    }
    public Inventory getProduct() {
        return product;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    /*
     * This function calculates the total amount 
     * TOTAL AMOUNT = ( price of single item )*(total quantity)
     */
    public double getTotal() {
        double total = product.getPrice() * quantity;
        return total;
    }

    public String getTotalCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(this.getTotal());
    }

	@Override
	public String toString() {
		return "\n********* CART ITEM *********" + 
				"\nproduct = " + product + 
				"\nQuantity = " + quantity +
				"\n*******************************";
	}
    
}
