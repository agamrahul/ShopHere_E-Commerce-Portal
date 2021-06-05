<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>      
        <style>			
			h1
			{  
			    font-size: 140%; 
			    color: teal; 
			    margin-bottom: .5em;  
			}
        	.title {       		
        		font-family: Arial, Helvetica, sans-serif;   
			    font-size: 85%; 
			    margin-left: 2em;  
			    margin-right: 2em;
        		color: teal;
        	}        	
        	label {
        		font-weight: bold;
        	}
        </style>
    </head>
    
    <body>
    <center>
    <div class="title">
        <h1><b>Welcome to E - Mart Login page</b></h1>
    </div>
    
    <div>
    
    	<!-- message will print login exception-->
    	<h3 style = "color:red;"> <span>${message}</span> </h3> 
    	
    	<form action = "login" method = "POST">
		<table cellspacing = '5'>
			<tr>
				<td><label for="username"> User ID : </label></td>
				<td><input type="text" name = "username" placeholder = "Enter userid" required> <br></td>
			</tr>		
			<tr>
				<td><label for="password"> Password : </label></td>
				<td><input type="password" name = "password" placeholder = "Enter password" required> <br></td>
			</tr>
			<tr>
			</tr>
			<tr>
			</tr>
			<tr>
				<td> </td>
				<td><input type = "submit" value = "LOGIN">
			</tr>			
		</table>
		</form>
		
    </div>
    </center>
    </body>

</html>