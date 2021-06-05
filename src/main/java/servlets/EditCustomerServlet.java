package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaDB.ConnectionProvider;
import dao.UserDAO;
import entities.User;

/**
 * Servlet implementation class EditCustomerServlet
 */
public class EditCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("********* EDIT CUSTOMER SERVLET **********");
		
		String option = request.getParameter("option");
		
		if(option == null) {
			option = "init";
		}
		
		if(option.equals("init")) {
			
			UserDAO udao = new UserDAO(ConnectionProvider.getConnection());
			ArrayList<User> customerList = udao.getAllCustomers();
			
			HttpSession session = request.getSession();
			session.setAttribute("customers", customerList);
			
			String url = "/viewCustomers.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		else if(option.equals("update")) {
			
			String userid = request.getParameter("userid").trim();
			System.out.println("the user id : " + userid);
			
			UserDAO udao = new UserDAO(ConnectionProvider.getConnection());
			ArrayList<User> customerList = udao.getAllCustomers();
			
			User customerToBeModified = null;
			
			/*
			 * Finding the Customer , using the userid.
			 */
			for(User customer : customerList) {
				if(customer.getUserId().equals(userid)) {
					customerToBeModified = customer;
				}
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("modifyuser", customerToBeModified);
			
			String url = "/updateCustomer.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		else if(option.equals("modify")) {
			
			// details of that user already existing in database are :
			HttpSession s = request.getSession();
			User oldUser = (User) s.getAttribute("existingUser");
			
			String oldId = oldUser.getUserId();
			
			// fetch all data from updateProfile.jsp, when user want to edit his details
			String updatedUserName = request.getParameter("updatedName");
			String updatedUserId = request.getParameter("updatedId");
			String updatedPassword = request.getParameter("updatedPassword");
			String updatedRole = request.getParameter("updatedRole");
			
			// now update the existing details with the new details 
			oldUser.setUserName(updatedUserName);
			oldUser.setUserId(updatedUserId);
			oldUser.setPasskey(updatedPassword);
			oldUser.setRole(updatedRole);
			
			System.out.println(oldUser);
			// update these in database 
			UserDAO userdao = new UserDAO(ConnectionProvider.getConnection());
					
			boolean res = userdao.updateUserDetails(oldUser, oldId);
			
			if(res) {
				System.out.println("----- UPDATED SUCCESSFULLY -----");
			}
			else {
				System.out.println("----- NOT UPDATED DETAILS-----");
			}
			
			String url = "/adminView.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
