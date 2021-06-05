<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> All Customers </title>
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
    		<h1><b> The List Of All Customers </b></h1>
    	</div>
    	
        <table border = '2' cellpadding = '7'>
            <tr>
                <th> CUSTOMER NAME </th>
                <th> CUSTOMER ID </th>
                <th> PASSWORD </th>
                <th>&nbsp;</th>
            </tr>
            
            <c:forEach var="cust" items="${customers}">
                <tr>
                    <td>${cust.userName}</td>
                    <td>${cust.userId}</td>
                    <td>${cust.passkey}</td>
                    <td>${cust.role }</td>
                    <td>
                        <form action="editCustomer" method="post">
                            <input type="hidden" name="option" value="update" >
                            <input type="hidden" name="userid" value="<c:out value='${cust.userId}'/>">
                            <input type="submit" value="UPDATE">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            
        </table>
    </body>
</html>