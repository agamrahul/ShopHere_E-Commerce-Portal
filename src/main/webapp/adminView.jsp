<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>   
        <style>
        	h1
			{  
			    font-size: 140%; 
			    color: teal; 
			    margin-bottom: .5em;  
			    font-family: Arial, Helvetica, sans-serif;   
			}
			li {
				margin: 1em;
			}
        </style>
	</head>
	
	<body>
		<h1> Welcome ADMIN, <span>${currentuser.getUserName()}</span></h1>
		
		<!-- List of ADMIN duties -->
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