<%@page import="entities.Inventory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>   
        <style>
        	h1
			{  
			    font-size: 120%; 
			    color: teal; 
			    margin-bottom: .5em;  
			    font-family: Arial, Helvetica, sans-serif;   
        		color: teal;
			}
			table {
				border-collapse: collapse;
			}
			td {
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
	
		<%
			Inventory item = (Inventory) session.getAttribute("item");
			
			if(item == null) {
				String url = "/updateInventory.jsp";
				response.sendRedirect(url);
			}
			
			String prodname = (String) session.getAttribute("prodname");
			System.out.println("PRD NAME in update item page : " + prodname);
		%>
		<center>
		<h1><b>UPDATE INVENTORY ITEM</b></h1>
		
		<form action = "editInventory" method = "post">
			<fieldset>
			<table cellspacing = "5">
				<tr>
					<td>PRODUCT ID</td>
					<td><input type="text" disabled name = "pid" value = <%= item.getProductId() %>></td>
				</tr>
	            <tr>
	            	<td>PRODUCT NAME</td>
	            	<td><input type = "text" name = "updatedName" value = "<%= prodname%>"></td>
	         	</tr>     
	         	
	         	<tr>
	         		<td>PRICE</td>
	         		<td><input type="text" name = "updatedPrice" value = <%= item.getPrice() %>></td>
	         	</tr>  
	         	<tr>
	         		<td>QUANTITY</td>
	         		<td><input type="text" name = "updatedQuantity" value = <%= item.getQuantity() %>></td>
	         	</tr> 
	        </table>
	        </fieldset>
	        
	        <br>
	        <br>
	        <input type="hidden" name="option" value="update" >
	        <input type="hidden" name="id" value="<c:out value='${item.productId}'/>">
	        <input type="submit" value = "UPDATE">
        </form>
        </center>
	</body>
</html>