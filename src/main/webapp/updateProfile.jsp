<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@page import="entities.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Update Profile</title>
		<style>	
		
			h1
			{  
			    font-size: 140%; 
			    color: teal; 
			    margin-bottom: .5em;  
			}
        	.title {       		
        		font-family: Arial, Helvetica, sans-serif;   
			    margin-left: 2em;  
			    margin-right: 2em;
        		color: teal;
        	}        	
        	label {
        		font-weight: bold;
        	}
		    fieldset {
		        display: inline-block;
		    }
		    input {
		    	margin: 5px;
		    }
		  
		</style>		
	</head>
	
	<body>
	
	<center>
	<!-- getting the user object from session -->
	<%
		User user = (User) session.getAttribute("currentuser");
		
		if( user == null) {
			response.sendRedirect("login.jsp");
		}
	%>
	<h1>Profile Page</h1>
	
	<form  action="editProfile" method="POST">
        <fieldset>           
            <table cellspacing="5">           
               <tr>
					<th>USERNAME</th>
					<td><input type = "text" name = "updatedName" value ="<%= user.getUserName()%>" ></td>
				</tr>
                <tr>
					<th>USER ID</th>
					<td><input type = "text" name = "updatedId" value = "<%= user.getUserId() %>" ></td>
				</tr>
				<tr>
					<th>PASSWORD</th>
					<td><input type = "text" name = "updatedPassword" value = "<%= user.getPasskey() %>" ></td>
				</tr>
				
				<!-- java code , to know the user role -->
				<%
					String userRole = user.getRole();
					System.out.println("In scriplet tag: " + userRole);
					
					if(userRole.equals("ADMIN")) {						
					
				%>				
					<tr>
						<th>ROLE</th>
						<td>
							<select name = "updatedRole">
								<option value = 'ADMIN'>ADMIN</option>
								<option value = 'MANAGER'>MANAGER</option>
								<option value = 'CUSTOMER'>CUSTOMER</option>
							</select>		
						</td>
					</tr>
				<%
					}
					else if(userRole.equals("CUSTOMER")){
				%>
					<tr>
						<th>ROLE</th>
						<td>
							<select name = "updatedRole">
								<option value = 'CUSTOMER' > <%= user.getRole() %></option>
							</select>		
						</td>
					</tr>
					<tr>
						<td></td>
						<td><small><i>* cannot change the role</i></small></td>
					</tr>
				<%
					}
					else if(userRole.equals("MANAGER")) {
				%>
					<tr>
						<th>ROLE</th>
						<td>
							<select name = "updatedRole">
								<option value = 'MANAGER' > <%= user.getRole() %></option>
							</select>		
						</td>
					</tr>
					<tr>
						<td></td>
						<td><small><i>* cannot change the role</i></small></td>
					</tr>
				<%
					}
				%>
				
            </table>
        </fieldset>
        <br><br>
        <%
        	HttpSession s = request.getSession();
        	s.setAttribute("existingUser", user);
        %>
        	<input type="hidden" name="option" value="update" >
			<input type = "submit" value = "UPDATE">
		
    </form>	
    	
    <form action="editProfile" method="POST">
    	<input type="hidden" name="option" value="cancel" >
    	<input type = "submit" value = "CANCEL">
    </form>
    </center>
	</body>
</html>