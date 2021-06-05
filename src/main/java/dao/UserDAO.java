package dao;

import java.sql.*;
import java.util.ArrayList;

import entities.User;
import exceptions.LoginException;

/**
 * This is in Data Access Layer
 * This class directly communicates with the Database.
 * We can validate the User, using the entered userId and Password.
 * 
 * @author SriLakshmi
 *
 */
public class UserDAO {
	
	// takes connection object as parameter while creating UserDao object.
	private  Connection con;
	
	// parameterized constructor
	public UserDAO(Connection con) {
		this.con = con;
	}
	
	/*
	 *  In our ordersystem , we have ADMIN , MANAGER , CUSTOMER
	 *  But this array list contains all customers only.
	 */
	public ArrayList<User> customerList = new ArrayList<User>();
	
	public ArrayList<User> getAllCustomers() {
		this.getAllCustomersFromDatabase();
		return customerList;
	}
	
	public User getUserByUserIdAndPassword(String userID , String password) {
		
		User user = null;
		
		try {
			
			String queryToFetchUser = "SELECT * FROM users WHERE userid = ? AND passkey = ?";
			
			// creating a Prepared statement
			System.out.println("Creating statement in UserDAO to get user by using userid and password");
			System.out.println("Query is : " + queryToFetchUser);
			
			PreparedStatement pstmt = con.prepareStatement(queryToFetchUser);
			pstmt.setString(1, userID);
			pstmt.setString(2, password);
			
			// executing the query
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				user = new User();
				
				user.setUserName(res.getString("username"));
				user.setUserId(res.getString("userid"));
				user.setPasskey(res.getString("passkey"));
				user.setRole(res.getString("role"));
				
			} // END of while loop
			
		} // END of try block
		catch (SQLException e) {		
			e.printStackTrace();
		}
		
		return user;
		
	} // END OF getUserByUserIdAndPassword method.
	
	public boolean updateUserDetails(User user, String old_userId) {
		
		boolean f = false;
		try {
			String query = "UPDATE users SET username = ? , userid = ? , passkey = ? , role = ? WHERE userid = ? ";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			System.out.println("In UserDAO/ update user details , query = " + query);
			
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserId());
			pstmt.setString(3,user.getPasskey());
			pstmt.setString(4, user.getRole());
			pstmt.setString(5, old_userId);
			
			int rows = pstmt.executeUpdate();
			
			if(rows > 0) {
				f = true;
			}
			
			return f;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	} // END of Edit Profile Servlet method
	
	public void getAllCustomersFromDatabase() {
		
		customerList.clear();
		User customer = null;
		
		try {
			
			String getAllCustomersQuery = "SELECT username , userid , passkey, role FROM users WHERE role = ?";
			
			PreparedStatement pstmt = con.prepareStatement(getAllCustomersQuery);
			pstmt.setString(1, "CUSTOMER");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				customer = new User();
				
				customer.setUserName(rs.getString("username"));
				customer.setUserId(rs.getString("userid"));
				customer.setPasskey(rs.getString("passkey"));
				customer.setRole(rs.getString("role"));
				
				// adding the customer to the customerList.
				customerList.add(customer);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean addUser(User user) {
		
		boolean f = false;
		try {
			String query = "INSERT INTO users(username, userid, passkey, role) VALUES (?, ?, ?, ?)";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			System.out.println("In UserDAO/ update user details , query = " + query);
			
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserId());
			pstmt.setString(3,user.getPasskey());
			pstmt.setString(4, user.getRole());
			
			int rows = pstmt.executeUpdate();
			
			if(rows > 0) {
				f = true;
			}
			
			return f;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
}
