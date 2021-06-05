<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Acess Roles </title>
        <style>
        	h1
			{  
			    font-size: 120%; 
			    color: teal; 
			    margin-bottom: .5em;  
			}
        	.title {       		
        		font-family: Arial, Helvetica, sans-serif;   
        	}        	
        	body {
        		margin-left: 2em;  
			    margin-right: 2em;
        	}
        	table {
        		border-collapse: collapse;
        		text-align: center;
        	}
        </style>
    </head>
    
    <body>
    	<div class = "title">
    		<h1><b> The List Of All Access Roles </b></h1>
    	</div>
    	
        <table border = '2' cellpadding = '7'>
            <tr>
                <th> ROLE </th>
                <th> Access to all Users </th>
                <th> Edit own Profile </th>
                <th> Edit Inventory </th>
                <th> Modify Access Roles </th>
                <th> Create Orders </th>
                <th>&nbsp;</th>
            </tr>
            
            <c:forEach var="role" items="${rolesList}">
                <tr>
                    <td><b style="color: red;">${role.roleName}</b></td>
                    <td>${role.updateAllUsers}</td>
                    <td>${role.updateOwnProfile}</td>
                    <td>${role.updateInventory }</td>
                    <td>${role.modifyRoles }</td>
                    <td>${role.createOrders }</td>
                    <td>
                        <form action="accessRole" method="post">
                            <input type="hidden" name="option" value="update" >
                            <input type="hidden" name="role" value="<c:out value='${role.roleName}'/>">
                            <input type="submit" value="UPDATE">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            
        </table>
    </body>
</html>