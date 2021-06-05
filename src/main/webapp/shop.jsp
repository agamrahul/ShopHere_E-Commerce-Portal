<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>shop</title>
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
        	}
        </style>
    </head>
    
    <body>
    	<div class = "title">
    		<h1><b>Below are the products available in our E - MART</b></h1>
    	</div>
    	
        <table border = '2' cellpadding = '7'>
            <tr>
                <th> PRODUCT NAME </th>
                <th> PRICE </th>
                <th>&nbsp;</th>
            </tr>
            
            <c:forEach var="item" items="${availableproducts}">
                <tr>
                    <td>${item.productName}</td>
                    <td>${item.price}</td>
                    <td>
                        <form action="cart" method="post">
                            <input type="hidden" name="option" value="add" >
                            <input type="hidden" name="productCode" value="<c:out value='${item.productId}'/>">
                            <input type="submit" value="Add to Cart">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            
        </table>
    </body>
</html>