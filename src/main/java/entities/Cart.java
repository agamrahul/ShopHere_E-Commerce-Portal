package entities;
 
import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
	
	/*
	 * This list represents the all the items and their quantity in the user cart.
	 */
    private ArrayList<CartItem> items;

    public Cart() {
        items = new ArrayList<CartItem>();
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    /*
     * This method adds the item in the cart.
     * It checks whether item is already present in the cart or not.
     * 
     * if item already present in the cart , it increases it's quantity by 1
     * else it adds the item to the cart and quantity will be 1.
     */
    public void addItem(CartItem item) {
    	
        CartItem cartItem = findItem(item.getProduct().getProductId());
        
        if (cartItem == null) {
            items.add(item);
        }
        else {
        	cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
        }
    }
    
    /*
     * This methods finds for an item which is already existing in the user cart.
     * if it finds it returns that item , or else it returns null.
     */
    public CartItem findItem(int productid){
    	
        for (int i = 0; i < items.size(); i++) {
        	
            if (items.get(i).getProduct().getProductId() == productid) {
                return items.get(i);
            }
        }
        return null;
    }

    /*
     * This will help us in removing any item from the cart.
     */
    public void removeItem(int productid) {
    	
        CartItem item = findItem(productid);
        
        if (item != null) {
            items.remove(item);
        }
    }
    
    /*
     * This method helps in updating the quantity of an item 
     * which is existing in the cart.
     * 
     * It returns the updated price.
     */
    public double updateItem(int productid, int quantity){
    	
        CartItem item = findItem(productid);
        
        if (item != null) {
        	item.setQuantity(quantity);
        }
        
        double modifiedPrice = item.getTotal();
        return modifiedPrice;
    }
    
    public void displayCart() {
    	
    	for(CartItem i : items) {
    		System.out.println(i);
    	}
    }
}
