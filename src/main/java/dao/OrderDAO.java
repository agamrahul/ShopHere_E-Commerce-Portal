package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import entities.Order;

public class OrderDAO {
	
	private static Connection con;
	
	public OrderDAO(Connection con) {
		this.con = con;
	}
	
	public static ArrayList<Order> orderList = new ArrayList<Order>();

	// We pass customerId as the argument , the method will generate the order number 
    // and sets the order date.
    public  int createOrder(String userId) {
    	
    	int id = -1;
    	/*
    	 * Converting java.util.Date to java.sql.Date
    	 */
    	Date date = new Date();
    	java.sql.Date sDate = new java.sql.Date(date.getTime());
    	
    	try {
    		/*
    		 * We are not generating the Ordernumber , because it is automatically generated in the database itself.
    		 */
    		String createNewOrder = "INSERT INTO orders( userid , date ) VALUES( ? , ?)";
    		
    		PreparedStatement pstmt = con.prepareStatement(createNewOrder);
    		pstmt.setString(1, userId);
    		pstmt.setDate(2, sDate);
    		
    		System.out.println("In OrderDao : executing query with CustID: " + userId);
    		int row = pstmt.executeUpdate();
    		
    		// rows effected
    		System.out.println("After insert operation in Orders table, rows effected: " + row);
    		
    		// To get order number of the above inserted row
    		System.out.println("Getting order Number: ");
    		String getOrderNumber = "SELECT MAX(ordernumber) AS ordernumber FROM orders WHERE userid = ? AND date = ?";
    		PreparedStatement st = con.prepareStatement(getOrderNumber);
    		st.setString(1, userId);
    		st.setDate(2, sDate);
    		
    		ResultSet rs = st.executeQuery();
    		
    		while(rs.next()) {
    			id = rs.getInt(1);
    		}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	} // END of try-catch blocks
    	
    	return id;
    } // END of creating order method.
    
    public static ArrayList<Order> getAllOrders() {
    	
    	Order ord = null;	
    	orderList.clear();
    	
        try{
        	Statement st = con.createStatement();
        	ResultSet rs;
        	
        	String sqlQuery = "SELECT  ordernumber , userid , date " + "from orders";
        	rs = st.executeQuery(sqlQuery);
			    
			while (rs.next()) {				
				 // creating a new Order object
			     ord = new Order();
			     
			     // initializing the order object with the data from orders table.
			     ord.setOrderNumber(rs.getInt(1));
			     ord.setUserId(rs.getString(2));
			     ord.setDate(rs.getDate(3));
			     
			     // Adding that order object to the arraylist.
			     orderList.add(ord);
			     
			} // END of while loop
        }// END of try block
        
		catch(Exception e){
		    System.out.println(e.getMessage());
		}
        
		return orderList;
    }
}
