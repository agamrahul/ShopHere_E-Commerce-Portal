package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaDB.ConnectionProvider;
import dao.UserDAO;
import entities.User;

/*
 * This is a Sign up servlet 
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("In Sign up Servlet");
		
		// creating a user DAO object for database operations.
		UserDAO userdao = new UserDAO(ConnectionProvider.getConnection());
		
		// creating a session
		HttpSession session = request.getSession();
		
		String userName = request.getParameter("username");
		String userId = request.getParameter("userid");
		String firstPassword = request.getParameter("firstPassword");
		String secondPassword = request.getParameter("secondPassword");
		
		User newUser = null;
		
		if(firstPassword.equals(secondPassword)) {
			
			// So the password and Re-entered password are same 
			// We are not checking whether that userid is already present or not. 
			
			newUser = new User();
			
			newUser.setUserName(userName);
			newUser.setPasskey(firstPassword);
			newUser.setUserId(userId);
			newUser.setRole("CUSTOMER");
			
			System.out.println("NEW USER : \n" + newUser);
			
			// Adding the details to database 
			boolean addedUser = userdao.addUser(newUser);
			
			if(addedUser) {
				System.out.println("User registered successfully");
				session.setAttribute("message", "Registered Successfully, Now Sign In to shop !!");
				
				String url = "/login.jsp";
				getServletContext().getRequestDispatcher(url).forward(request, response); 
			}
		}
		
		if(newUser == null) {
			System.out.println("Something went wrong, registration not successfull !");
			session.setAttribute("message", "Something went wrong, please try again !!");
			
			String url = "/signup.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response); 
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
