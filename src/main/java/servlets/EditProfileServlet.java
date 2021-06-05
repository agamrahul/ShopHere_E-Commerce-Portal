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

/**
 * Servlet implementation class EditProfileServlet
 */
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("In Edit Profile Servlet class");
		
		HttpSession s = request.getSession();
		// get the user from session ( already existing details )			
		User existing_user = (User) s.getAttribute("existingUser");
					
		String option = request.getParameter("option");
		
		if(option == null) {
			option = "cancel";
		}
		
		if(option.equals("cancel")) {
			
			System.out.println("Cancelling the process and moving back to the home page");
			String role = existing_user.getRole();
			
			// if role is ADMIN , we need to direct them to /adminView.jsp page
			if(role.equals("ADMIN")) {
							
				String url = "/adminView.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			}
			else if(role.equals("CUSTOMER")) {
							
				String url = "/customerView.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			}
			else if(role.equals("MANAGER")) {
				
				String url = "/managerView.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			}
		}
		else if(option.equals("update")) {
			// fetch all data from updateProfile.jsp, when user want to edit his details
			String updatedUserName = request.getParameter("updatedName");
			String updatedUserId = request.getParameter("updatedId");
			String updatedPassword = request.getParameter("updatedPassword");
			String updatedRole = request.getParameter("updatedRole");
			
			System.out.println("User has requested to update his details \nBelow are the details: ");
			System.out.println("NEW ROLE: " + updatedRole);
			
			String old_userId = existing_user.getUserId();
			
			// now update the existing details with the new details 
			existing_user.setUserName(updatedUserName);
			existing_user.setUserId(updatedUserId);
			existing_user.setPasskey(updatedPassword);
			existing_user.setRole(updatedRole);
			
			System.out.println(existing_user);
			// update these in database 
			UserDAO userdao = new UserDAO(ConnectionProvider.getConnection());
					
			boolean res = userdao.updateUserDetails(existing_user, old_userId);
			
			if(res) {
				System.out.println("----- UPDATED SUCCESSFULLY -----");
			}
			else {
				System.out.println("----- NOT UPDATED DETAILS-----");
			}
					
			s.setAttribute("currentuser", existing_user);
			
			// if role is ADMIN , we need to direct them to /adminView.jsp page
			if(updatedRole.equals("ADMIN")) {
				
				String url = "/adminView.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			}
			else if(updatedRole.equals("CUSTOMER")) {
				
				String url = "/customerView.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			}
			else if(updatedRole.equals("MANAGER")) {
				
				String url = "/managerView.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			}
		}
		
	} // END of doGet() method

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
