<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>Shopping Cart</title>
       	<style>
       		h1 , h2
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
			<h1> YOUR CART ITEMS </h1>
		</div>
		<br>
        <table border = '2' cellpadding = '5'>
            <tr>
                <th>Quantity</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Amount</th>
                <th></th>
            </tr>

            <c:forEach var="item" items="${cart.items}">
                <tr>
                    <td>
                        <form action="cart" method="post">
                            <input type="hidden" name="option" value="update">
                            <input type="hidden" name="productCode" value="<c:out value='${item.product.productId}'/>">
                            <input type=text name="quantity" value="<c:out value='${item.quantity}'/>" id="quantity">
                            <input type="submit" value="Update">
                        </form>
                    </td>
                    <td><c:out value='${item.product.productName}'/></td>
                    <td>${item.product.price}</td>
                    <td>${item.totalCurrencyFormat}</td>
                    <td>
                        <form action="cart" method="post">
                            <input type="hidden" name="option" value="remove">
                            <input type="hidden" name="productCode" value="<c:out value='${item.product.productId}'/>">
                            <input type="submit" value="Remove Item">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            
            <c:set var="totalAmount" value="${0}" />
            <c:forEach var="item" items="${cart.items}">
            	<c:set var="totalAmount" value="${totalAmount + item.product.price*item.quantity}" />
            </c:forEach>
            
        </table>
		
		<h2>The Total Amount of cart is: ${totalAmount }</h2>
        <p><b>To change the quantity</b>, enter the new quantity and click on the Update button.</p>
  
        <form action="cart" method="post">
            <input type="hidden" name="option" value="shop">
            <input type="submit" value="Continue Shopping">
        </form>
		<br>
        <form action="cart" method="post">
            <input type="hidden" name="option" value="checkout">
            <input type="submit" value="Checkout">
        </form>

    </body>
</html>