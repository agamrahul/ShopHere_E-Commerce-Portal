package JavaDB;
import java.sql.*;

/*It helps in connecting to database
 * It performs all the important steps of JDBC 
 * Loads and registers  the jdbc drivers
 * we give Mysql username and password  here
 * It gives Connection object
*/

public class ConnectionProvider {
	
	private static Connection con;
	
	public static Connection getConnection() {	
		try {
			if(con == null) {
				// load the driver class
				Class.forName("com.mysql.jdbc.Driver");
				
				// connection establishing 
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ordersystem","root","root");
				System.out.println("In JavaDB/connectionProvider : "+con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	} // END of getConnection( ) method
} // END of Connection Provider class.
