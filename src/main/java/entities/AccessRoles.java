package entities;

/*
 * This class represents the total number of different roles in the system.
 * 
 * ADMIN - can do these activities --> modify all users , inventory , modify roles in this class and create orders.
 * MANAGER - can only update inventory and create orders
 * CUSTOMER - can update his own profile and create orders
 * 
 */

public class AccessRoles {
	
	private String roleName;
	
	private boolean updateAllUsers;
	private boolean updateOwnProfile;
	private boolean updateInventory;
	private boolean modifyRoles;
	private boolean createOrders;
	
	// default constructor 
	public AccessRoles() {
		
	}
	
	// constructor
	public AccessRoles(String roleName, boolean updateAllUsers, boolean updateOwnProfile, boolean updateInventory,
			boolean modifyRoles, boolean createOrders) {
		
		this.roleName = roleName;
		this.updateAllUsers = updateAllUsers;
		this.updateOwnProfile = updateOwnProfile;
		this.updateInventory = updateInventory;
		this.modifyRoles = modifyRoles;
		this.createOrders = createOrders;
	} // END of parameterized constructor

	// getters and setters
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isUpdateAllUsers() {
		return updateAllUsers;
	}

	public void setUpdateAllUsers(boolean updateAllUsers) {
		this.updateAllUsers = updateAllUsers;
	}

	public boolean isUpdateOwnProfile() {
		return updateOwnProfile;
	}

	public void setUpdateOwnProfile(boolean updateOwnProfile) {
		this.updateOwnProfile = updateOwnProfile;
	}

	public boolean isUpdateInventory() {
		return updateInventory;
	}

	public void setUpdateInventory(boolean updateInventory) {
		this.updateInventory = updateInventory;
	}

	public boolean isModifyRoles() {
		return modifyRoles;
	}

	public void setModifyRoles(boolean modifyRoles) {
		this.modifyRoles = modifyRoles;
	}

	public boolean isCreateOrders() {
		return createOrders;
	}

	public void setCreateOrders(boolean createOrders) {
		this.createOrders = createOrders;
	}

	@Override
	public String toString() {
		return 	"--------------------------------\n" + 
				"AccessRoles: \n" +  "roleName=" + roleName + 
				"\nupdateAllUsers = " + updateAllUsers + 
				"\nupdateOwnProfile = "+ updateOwnProfile + 
				"\nupdateInventory = " + updateInventory + 
				"\nmodifyRoles = " + modifyRoles +
				"\ncreateOrders = " + createOrders + 
				"\n-------------------------------\n";
	}
	
} // END of AccessRoles class
