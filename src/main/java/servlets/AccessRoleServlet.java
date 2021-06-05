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
import entities.AccessRoles;

/**
 * Servlet implementation class AccessRoleServlet
 */
public class AccessRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String option = request.getParameter("option");
		
		if(option == null) {
			option = "init";
		}
		
		AccessRolesDAO adao = new AccessRolesDAO(ConnectionProvider.getConnection());
		
		if(option.equals("init")) {
			
			ArrayList<AccessRoles> listOfRoles = adao.getListOfRoles();
			
			HttpSession session = request.getSession();
			session.setAttribute("rolesList", listOfRoles);
			
			String url = "/viewAccessRoles.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
			
		}
		else if(option.equals("update")) {
			
			String role = request.getParameter("role");
			System.out.println("The role is : " + role);
			
			AccessRoles roleToBeModified = null;
			ArrayList<AccessRoles> listOfRoles = adao.getListOfRoles();
			System.out.println(" THE NUMBER OF ROLES :" + listOfRoles.size());
			
			for(AccessRoles a : listOfRoles) {
				//System.out.println(a);
				if(a.getRoleName().equals(role)) {
					roleToBeModified = a;
				}
			}
			
			System.out.println(roleToBeModified);
			
			HttpSession session = request.getSession();
			session.setAttribute("modRole", roleToBeModified);
			session.setAttribute("roleName", role);
			
			String url = "/updateAccessRoles.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
			
		}
		else if(option.equals("modify")) {
			String oldRole = request.getParameter("oldRole");
			
			String u = request.getParameter("allUsers");
			boolean allUsers = this.findTrueorFalse(u);
			
			String p = request.getParameter("ownProfile");
			boolean profile = this.findTrueorFalse(p);
			
			String inv = request.getParameter("inventory");
			boolean changeInventory = this.findTrueorFalse(inv);
			
			String r = request.getParameter("accessRoles");
			boolean roles = this.findTrueorFalse(r);
			
			String ord = request.getParameter("createorders");
			boolean orders = this.findTrueorFalse(ord);
			
			AccessRoles modifiedRole = new AccessRoles();
			modifiedRole.setRoleName(oldRole);
			modifiedRole.setUpdateAllUsers(allUsers);
			modifiedRole.setUpdateOwnProfile(profile);
			modifiedRole.setUpdateInventory(changeInventory);
			modifiedRole.setModifyRoles(roles);
			modifiedRole.setCreateOrders(orders);
			
			// pushing updates into database using AccessRolesDAO class.
			adao.updateAccessRoles(modifiedRole, oldRole);
			
			String url = "/adminView.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		else if(option.equals("cancel")) {
			
			String url = "/adminView.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		
	}
	
	public boolean findTrueorFalse(String text) {
		
		if(text.equals("true")) {
			return true;
		}else if(text.equals("false")) {
			return false;
		}
		
		return false;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
