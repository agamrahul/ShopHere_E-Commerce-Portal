package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.AccessRoles;

public class AccessRolesDAO {
	
	private Connection con;
	
	public AccessRolesDAO(Connection con) {
		this.con = con;
	}
	
	public ArrayList<AccessRoles> listOfRoles = new ArrayList<AccessRoles>();
	
	public ArrayList<AccessRoles> getListOfRoles() {
		this.getAccessRoles();
		return listOfRoles;
	}
	
	/*
	 * This function gets all the roles in the ordersystem.
	 * ADMIN
	 * MANAGER
	 * CUSTOMER
	 */
	public void getAccessRoles() {
	
		listOfRoles.clear();
		AccessRoles role = null;
		
		try {			
			String accessRoles = "SELECT * FROM accessroles";
			
			PreparedStatement pstmt = con.prepareStatement(accessRoles);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				role = new AccessRoles();
				
				role.setRoleName(rs.getString("role"));
				role.setUpdateAllUsers(rs.getBoolean("users"));
				role.setUpdateOwnProfile(rs.getBoolean("ownprofile"));
				role.setUpdateInventory(rs.getBoolean("inventory"));
				role.setModifyRoles(rs.getBoolean("modifyroles"));
				role.setCreateOrders(rs.getBoolean("orders"));
				
				listOfRoles.add(role);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	} // END of getAccessRoles() method.
	
	public void updateAccessRoles(AccessRoles modifiedRole , String role) {
		
		try {			
			String updateRoleQuery = "UPDATE accessroles SET users = ? , ownprofile = ?, inventory = ?, modifyroles = ?, orders = ?"
					+ " WHERE role = ? ";
			
			PreparedStatement pstmt = con.prepareStatement(updateRoleQuery);
			pstmt.setBoolean(1, modifiedRole.isUpdateAllUsers());
			pstmt.setBoolean(2, modifiedRole.isUpdateOwnProfile());
			pstmt.setBoolean(3, modifiedRole.isUpdateInventory());
			pstmt.setBoolean(4, modifiedRole.isModifyRoles());
			pstmt.setBoolean(5, modifiedRole.isCreateOrders());
			pstmt.setString(6, role);
			
			int res = pstmt.executeUpdate();
			
			if(res > 0) {
				System.out.println("------- UPDATED THE ACCESS ROLE SUCCESSFULLY -------");
			}
			else {
				System.out.println("------- NOT UPDATED SUCCESSFULLY -------");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
} // END of AccessRolesDAO class.
