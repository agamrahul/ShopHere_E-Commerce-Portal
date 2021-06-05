package servlets;
import entities.*;

import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaDB.ConnectionProvider;
import dao.InventoryDAO;
import dao.OrderDAO;
import dao.OrderItemDAO;

public class CartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        ServletContext sc = getServletContext();
        
        System.out.println("****Inside Servlet*****");
        
        HttpSession session = request.getSession();
        
        String option = request.getParameter("option");
        System.out.println("***option = " + option);
        
        String cid = (String) session.getAttribute("cid");
        
        User user = (User) request.getAttribute("currentuser");
        System.out.println("USER inside cart servlet is: " + user);
        
        System.out.println("user id: " + cid);
        
        String url = "/customerView.jsp";
        
        if (option == null) {
            option = "shop";  // default action
        }
        
        if (option.equals("add")) {
        	
        	// using the already existing cart.
            Cart cart = (Cart) session.getAttribute("cart");
            
            String productCode = request.getParameter("productCode");
            int productid = Integer.parseInt(productCode.trim());
            
            InventoryDAO idao = new InventoryDAO(ConnectionProvider.getConnection());
            Inventory product = idao.getItemUsingId(productid);
            
            CartItem item = new CartItem();
            item.setProduct(product);
            item.setQuantity(1);
            
            // adding the item to the cart.
            cart.addItem(item);
            
            session.setAttribute("cart", cart);
            
            System.out.println("\n----------- ADD to CART ---------------");
            cart.displayCart();
            
            url = "/cart.jsp";
        }
        
        else if (option.equals("checkout")) {
        	Cart cart = (Cart) session.getAttribute("cart");
        	
        	ArrayList<CartItem> items = cart.getItems();
        	System.out.println("Size of cart is: " + items.size());
        	//creating an order
        	OrderDAO odao = new OrderDAO(ConnectionProvider.getConnection());
        	int orderNum = odao.createOrder(cid);
        	
        	OrderItemDAO oidao = new OrderItemDAO(ConnectionProvider.getConnection());
        	
        	for(CartItem x : items) {
        		
        		int itemCode = x.getProduct().getProductId();
        		int itemQuantity = x.getQuantity();
        		String itemDesc = x.getProduct().getProductName();
        		double cost = x.getProduct().getPrice();
        		
        		// When user clicks on checkout button , we are changing the quantity on hand in inventory.
        		InventoryDAO idao = new InventoryDAO(ConnectionProvider.getConnection());
        		int res = idao.updateQuantity(itemCode, itemQuantity);
        		        		
        		 // updating the orderItem
        		oidao.addItemToDatabase(orderNum, itemCode, itemDesc, itemQuantity, cost);
        	}
        	
        	System.out.println("printing all order items: ");
        	oidao.getAllOrderItems();
        	
        	
        	System.out.println("\n----------- CHECKOUT ---------------");
            cart.displayCart();
             
            url = "/checkout.jsp";
        }
        
        else if (option.equals("init")) {
        	
        	//creating inventory of products and initializing them with some quantity.
        	InventoryDAO idao = new InventoryDAO(ConnectionProvider.getConnection());
            ArrayList<Inventory> products = idao.getInventoryList();
            
            System.out.println("Total number of products: " + products.size());
//            for(Inventory i : products ) {
//            	System.out.println("PROD inside cart servlet : " + i);
//            }
            
            // created a bag for the customer ( to add the products )
            Cart cart = new Cart();
            
            session.setAttribute("availableproducts", products);
            session.setAttribute("cart", cart);
            
            System.out.println("\n----------- INIT ---------------");
            cart.displayCart();
            
            url = "/shop.jsp";
        }
        else if (option.equals("shop")) {
        	 url = "/shop.jsp";
        }
        
        else if (option.equals("remove")){
        	
            Cart cart = (Cart) session.getAttribute("cart");
            
            String productCode = request.getParameter("productCode");
            int productid = Integer.parseInt(productCode);
            
            // removing the item from the cart
            cart.removeItem(productid);
            
            session.setAttribute("cart", cart);
            url = "/cart.jsp";
        }
        
        else if (option.equals("update")){
            int quantity , productid;
            
            Cart cart = (Cart) session.getAttribute("cart");
            
            String productCode = request.getParameter("productCode");
            productid = Integer.parseInt(productCode);
            String quantityString = request.getParameter("quantity");
            
            try {
                quantity = Integer.parseInt(quantityString);
            }
            
            catch (NumberFormatException e) {
                quantity = 1;
            }
            
            cart.updateItem(productid, quantity);
            
            session.setAttribute("cart", cart);
            
            System.out.println("\n----------- UPDATE ---------------");
            cart.displayCart();
            
            url = "/cart.jsp";
        }

        sc.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}

