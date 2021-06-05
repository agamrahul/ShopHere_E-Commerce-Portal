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
			    font-size: 140%; 
			    color: teal; 
			    margin-bottom: .5em;  
			    font-family: Arial, Helvetica, sans-serif;   
        		color: teal;
			}
        </style>
        <link rel="stylesheet" href="style.css" type="text/css"/>
	</head>
	
	<body>
		<h1> Welcome ${currentuser.getRole()}, <span>${currentuser.getUserName()}</span></h1>
		<h2>INVENTORY ITEMS LIST</h2>
		
		<table>
            <tr>
                <th> PRODUCT NAME</th>
                <th> PRICE </th>
                <th> QUANTITY AVAILABLE </th>
            </tr>
            
            <c:forEach var="item" items="${products}">
                <tr>
                    <td>${item.getProductName()}</td>
                    <td class = "right">${item.price}</td>
                    <td>${item.quantity }</td>
                    <td>
                        <form action="editInventory" method="post">
                            <input type="hidden" name="option" value="modify" >
                            <input type="hidden" name="productid" value="<c:out value='${item.productId}'/>">
                            <input type="submit" value="UPDATE">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            
        </table>
	</body>
</html>