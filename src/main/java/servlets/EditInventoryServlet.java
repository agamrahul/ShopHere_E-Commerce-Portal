package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaDB.ConnectionProvider;
import dao.InventoryDAO;
import entities.Inventory;
import entities.User;

/**
 * Servlet implementation class EditInventoryServlet
 */
public class EditInventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("------ IN Edit inventory servlet ------");
		
		// creating inventoryDAO object and getting the inventory list
		InventoryDAO daoInv = new InventoryDAO(ConnectionProvider.getConnection());
		
		String option = request.getParameter("option");
		
		if(option == null) {
			option = "";
		}		
		
		if(option.equals("modify")) {
			
			String pid = request.getParameter("productid");
			System.out.println("product to be modified is: " + pid);
			
			int productid = Integer.parseInt(pid.trim());
			
			
			// Fetching the inventory item using productid
			Inventory itemToBeModified = daoInv.getItemUsingId(productid);
			System.out.println("Item to be modified is: " + itemToBeModified);
			
			HttpSession session = request.getSession();
			session.setAttribute("item", itemToBeModified);
			session.setAttribute("prodname", itemToBeModified.getProductName());
			
			String url = "/updateInventoryItem.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response); 
		}
		else if(option.equals("update")) {
			
			String updatedId = request.getParameter("id");
			int id = Integer.parseInt(updatedId);
			
			String updatedName = request.getParameter("updatedName");
			
			String price = request.getParameter("updatedPrice");
			double updatedPrice = Double.parseDouble(price);
			
			String quantity = request.getParameter("updatedQuantity");
			int updatedQuantity = Integer.parseInt(quantity);
			
			Inventory updatedItem = new Inventory();
			updatedItem.setProductId(id);
			updatedItem.setProductName(updatedName);
			updatedItem.setPrice(updatedPrice);
			updatedItem.setQuantity(updatedQuantity);
			
			// pushing the changes into database using DAO
			InventoryDAO updateDao = new InventoryDAO(ConnectionProvider.getConnection());
			updateDao.updateInventoryItem(updatedItem);
			
			System.out.println("============== UPDATE SUCCESSFULLY ==============");
			
			// creating session 
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("currentuser");
			
			String username = user.getRole();
			
			if(username.equals("ADMIN")) {
				
				String url = "/adminView.jsp";
				getServletContext().getRequestDispatcher(url).forward(request, response); 
			}
			else if(username.equals("MANAGER")) {
				
				String url = "/managerView.jsp";
				getServletContext().getRequestDispatcher(url).forward(request, response); 
			}
		}
		else {
			// getting inventory list.
			ArrayList<Inventory> products = daoInv.getInventoryList();
			// 
			
	//		System.out.println("IN EDIT INVENTORY SERVLET : PRINTING PRODUCTS");
	//		for(Inventory i : products) {
	//			System.out.println(i);
	//		}
			HttpSession session = request.getSession();
			session.setAttribute("products", products);
			
			String url = "/updateInventory.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response); 
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
