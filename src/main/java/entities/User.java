package entities;

/*
 * This class represents the attributes of a user in this system.
 * 
 * parameters are :
 * username 
 * userid
 * password
 * role 
 * 
 */

public class User {
	
	private String userName;
	private String userId;
	private String passkey;
	private String role;
	
	//default constructor 
	public User() {
		
	}
	
	// parameterized constructor
	public User(String userName, String userId, String passkey, String role) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.passkey = passkey;
		this.role = role;
	}

	// getters and setters
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasskey() {
		return passkey;
	}

	public void setPasskey(String passkey) {
		this.passkey = passkey;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		
		return 	"\n----- USER -----\n" +
				"USER NAME = " + userName + "\t | \t" + "USER ID = " + userId + 
				"\nPASSWORD = " + passkey + "\t | \t" + "ROLE = " + role + 
				"\n--------------------------\n";
	}

} // END of Users class.
