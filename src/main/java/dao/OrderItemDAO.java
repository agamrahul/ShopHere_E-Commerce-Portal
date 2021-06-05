package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entities.OrderItem;

public class OrderItemDAO {
	
	private static Connection con;
	
	public OrderItemDAO(Connection con) {
		this.con = con;
	}
	
	public static ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
	
	/*
	 *  This adds the orderitem to the database
	 */
	public void addItemToDatabase(int orderNumber, int productId, String description, 
			int quantity, double price) {
		
		try {
			String createOrderItem = "INSERT INTO orderItem(ordernumber , productid , productname ,quantity, price) VALUES(?,?,?,?,?)";
			
			PreparedStatement pstmt = con.prepareStatement(createOrderItem);
			pstmt.setInt(1, orderNumber);
			pstmt.setInt(2, productId);
			pstmt.setString(3, description);
			pstmt.setInt(4, quantity);
			pstmt.setDouble(5, price);
			
			int row = pstmt.executeUpdate(); 

    		// rows effected
    		System.out.println("After insert operation in OrderItem table, rows effected: "+row);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	} // END of adding item to list method.
	
	public ArrayList<OrderItem> getAllOrderItems() {
		
		OrderItem ord = null;	
        try{
        	Statement st = con.createStatement();
        	ResultSet rs;
        	
        	String sqlQuery = "SELECT  ordernumber , productid , productname , quantity , price " + "from orderitem";
        	rs = st.executeQuery(sqlQuery);
			    
			while (rs.next()) {				
				 // creating a new Order object
			     ord = new OrderItem();
			     
			     // initializing the order object with the data from orders table.
			     ord.setOrderNumber(rs.getInt(1));
			     ord.setProductId(rs.getInt(2));
			     ord.setProductName(rs.getString(3));
			     ord.setQuantity(rs.getInt(4));
			     ord.setPrice(rs.getDouble(5));
			     
			     // Adding that order object to the arraylist.
			     orderItemList.add(ord);
			} // END of while loop
        }// END of try block
        
		catch(Exception e){
		    System.out.println(e.getMessage());
		}
		return orderItemList;
	}
}
