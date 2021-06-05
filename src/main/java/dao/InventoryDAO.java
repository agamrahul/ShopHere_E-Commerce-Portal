package dao;

import java.sql.*;
import java.util.ArrayList;

import entities.Inventory;

public class InventoryDAO {
	
	// takes connection object as parameter while creating UserDao object.
	private  Connection con;
	
	public InventoryDAO(Connection con) {
		this.con = con;
	}
	
	// List of all inventory objects 
	private static ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();
	
	// to get all the list of inventory
	public ArrayList<Inventory> getInventoryList() {
		this.getInventoryFromDatabase();
		return inventoryList;
	}
	
	/* 
	 * This method helps in getting whole data from database 
	 * and it populates the inventoryList with that data
	 * so we need not hit database again and again to get inventory items.
	 * 
	 */
	public void getInventoryFromDatabase() {
		
		System.out.println("Getting inventory data from database...inventoryDAO");
		inventoryList.clear();
		
		Inventory item = null;
		
		String queryToFetchItems = "SELECT * FROM inventory";
		
		try {				
			PreparedStatement pstmt = con.prepareStatement(queryToFetchItems);				
			ResultSet result = pstmt.executeQuery();
			
			while(result.next()) {
				
				item = new Inventory();
				
				item.setProductId(result.getInt("productid"));
				item.setProductName(result.getString("productname"));
				item.setPrice(result.getDouble("price"));
				item.setQuantity(result.getInt("quantity"));
				
				// adding the item to inventoryList
				inventoryList.add(item);
				
			} // END of while loop		
//			System.out.println("========== LIST ITEMS ==========");
//			for(Inventory i : inventoryList ) {
//				System.out.println(i);
//			}
		}catch (SQLException e) {		
			System.out.println("***** Some issue in fetching inventory from database *****");
			e.printStackTrace();
		} // END of try - catch block
	} // END of method getInventoryFromDatabase()
	
	
	/*
	 * either ADMIN or MANAGER can update the inventory items
	 * So this methods helps us in pushing those modifications to database.
	 */
	public boolean updateInventoryItem(Inventory modifiedItem) {
		
		boolean updated = false;
		
		// modified data 
		int id = modifiedItem.getProductId();
		String updatedName = modifiedItem.getProductName();
		double updatedPrice = modifiedItem.getPrice();
		int updatedQuantity = modifiedItem.getQuantity();
				
		try {
			
			String updateQuery = "UPDATE inventory SET productname = ? , price = ? , quantity = ? WHERE productid = ?";
			PreparedStatement pstmt = con.prepareStatement(updateQuery); 
			pstmt.setString(1, updatedName);
			pstmt.setDouble(2, updatedPrice);
			pstmt.setInt(3, updatedQuantity);
			pstmt.setInt(4, id);
			
			System.out.println("In InventoryDAO/ executing the update query");
			int res = pstmt.executeUpdate();
			
			if(res > 0) {
				System.out.println("----- Updated the product successfully -----");
				updated = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return updated;
	}
	
	/*
	 * This method uses productId to get the Inventory item and returns that item.
	 */
	public Inventory getItemUsingId(int pid) {
		
		Inventory item = null;
		
		for( Inventory i : inventoryList ) {
			if( i.getProductId() == pid ) {
				item = i;
			}
		}
		
		return item;
	}
	
	 // reducing the quantity of product ( identified based on code ) by quantity.
	public int updateQuantity(int code, int quantity) {
		
		int rs = 0;
		
		for(Inventory item : inventoryList ) {	
			
			if(item.getProductId() == code) {
			
				int currentQuantity = item.getQuantity();
				System.out.println("In inventoryDao, curent Quantity is: "+ currentQuantity);
				
				int newQuantity = currentQuantity - quantity;
				System.out.println("In inventoryDao, new Quantity is: "+ newQuantity);		
				
				item.setQuantity(newQuantity);
				
				// pushing the modifications into database ( Inventory table )
				try {			
					String updateQuantityQuery = "UPDATE inventory " + 
							"SET quantity = ? WHERE productid = ?";
					
					PreparedStatement ps = con.prepareStatement(updateQuantityQuery);
					ps.setInt(1, newQuantity);
					ps.setInt(2, code);
					
					rs = ps.executeUpdate();
					
					// rows effected
					System.out.println("In inventory table , after updating quantity, rows effected: " + rs);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			} // END of IF block
		} // END of for loop.
		return rs;
		
	} // END of updateQuantity method.
		
} // END of InventoryDao class.
