package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside Logout Servlet");
		
		HttpSession session = request.getSession();
		
		// removing the user from the session,
		// that means user has logged out !!
		session.removeAttribute("currentuser");
		session.invalidate();
		
		String logout_message = "Logged out Succesfully !!";
		
		System.out.println(logout_message);
		
		request.setAttribute("message", logout_message);
		
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
