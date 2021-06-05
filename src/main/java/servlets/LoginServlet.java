package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaDB.ConnectionProvider;
import dao.AccessRolesDAO;
import dao.UserDAO;
import entities.AccessRoles;
import entities.User;
import exceptions.LoginException;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// login.jsp page is linked with this servlet.
		// when they submit their details in login.jsp page and click on submit 
		// we get those details here and perform some validations.
		
		// fetching details from login.jsp page
		String userid = request.getParameter("username");
		String password = request.getParameter("password");
		
		String message = "";
		String url = "/login.jsp";
		
		// UserDAO has a method to check whether userid and password are there in database or not.
		UserDAO userdao = new UserDAO(ConnectionProvider.getConnection());
		
		// verifying those userid and password
		// if valid details we get all the details of user
		// else it raises an error.
		User user = userdao.getUserByUserIdAndPassword(userid, password);
		
		if(user == null) {
			
			// throw new LoginException(errorMessage); 
			
			// Invalid details are entered.
			System.out.println("userid and password are wrong...");
			message = "Invalid Username and Password";
						
			try {
				throw new LoginException(message); 
			}catch(LoginException e) {
				
				HttpSession session = request.getSession();
				session.setAttribute("message", e.getMessage());
				
				url = "/login.jsp";
				getServletContext().getRequestDispatcher(url).forward(request, response); 
			}
			
		}
		else {
			
			// Login Successfull
			System.out.println("----- LOGIN SUCCESSFULL -----");
			
			HttpSession session = request.getSession();
			message = "Login Successful !!";
			session.setAttribute("message", message);
			session.setAttribute("currentuser", user);
			
			// finding user role.
			String userRole = user.getRole().trim().toUpperCase();
			
			// finding all the functionalities of user based on his role.
			AccessRolesDAO adao = new AccessRolesDAO(ConnectionProvider.getConnection());
			ArrayList<AccessRoles> listOfRoles = adao.getListOfRoles();
			AccessRoles userAccess = null;
			
			for(AccessRoles a : listOfRoles ) {
				if(a.getRoleName().equals(userRole)) {
					userAccess = a;
				}
			}
			
			session.setAttribute("currentaccess", userAccess);
			
			if(userRole.equals("ADMIN")) {
				
				url = "/adminView.jsp";
				getServletContext().getRequestDispatcher(url).forward(request, response); 
			}
			else if(userRole.equals("MANAGER")) {
				
				url = "/managerView.jsp";
				getServletContext().getRequestDispatcher(url).forward(request, response); 
			}
			else if(userRole.equals("CUSTOMER")) {
				
				url = "/customerView.jsp";
				getServletContext().getRequestDispatcher(url).forward(request, response); 
			}
			
		}
		
	}
	// END of doGet( ) method
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

} // END of LoginServlet class
