<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entities.User" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Customer Page </title>
        <style>			
			h1
			{  
			    font-size: 120%; 
			    color: teal; 
			    font-family: Arial, Helvetica, sans-serif;   
			}      	    
			body {
				margin-left: 2em;  
			    margin-right: 2em;
			}   	
        	label {
        		font-weight: bold;
        	} 
        	li {
        		margin: 1em;
        	}
        </style>
    </head>
    
    <body>
        <h1><b>Welcome CUSTOMER ,${currentuser.userName}</b></h1>
        <h3><small><i>${message}</i></small></h3>
		
		<!-- Finding the functionalities of USER based on Access roles -->

		<!-- passing the customer id to cart servlet -->
		<ul>
			<c:if test="${currentaccess.isUpdateInventory() == true }">
				<li>
					<form action = "editInventory" method = "POST">
						<input type = "submit" value = "UPDATE INVENTORY">
					</form>
				</li>
			</c:if>
			<c:if test="${currentaccess.isUpdateAllUsers() == true }">
				<li>
					<form action = "editCustomer" method = "POST">
						<input type="hidden" name="option" value="init">
						<input type= "submit" value = "VIEW ALL CUSTOMERS">
					</form>
				</li>
			</c:if>
			<c:if test="${currentaccess.isUpdateOwnProfile() == true }">
				<li>
					<form action = "updateProfile.jsp" method = "post">
						<input type = "submit" value = "UPDATE PROFILE">
					</form>
				</li>
			</c:if>
			<c:if test="${currentaccess.isModifyRoles() == true }">
				<li>
					<form action = "accessRole" method = "POST">
						<input type="hidden" name="option" value="init">
						<input type = "submit" value = "MODIFY ACCESS ROLES">
					</form>
				</li>	
			</c:if>
			<c:if test="${currentaccess.isCreateOrders() == true }">
				<li>
					<form action="cart" method="post">
				        <input type="hidden" name="option" value="init">
				       	<c:set var="cid" scope="session" value="${currentuser.getUserId()}" />
				        <input type="submit" value="SHOP">
		   			</form>
				</li>
			</c:if>
			
		</ul>
	    
	    <br>
	    <form action="logout" method = "POST">
	    	<input type = submit value = "LOGOUT">
	    </form>
    </body>
</html>
