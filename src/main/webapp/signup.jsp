<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>      
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
        <h1><b>Welcome to E - Mart Registration page</b></h1>
    </div>
    
    <div>
    
    	<!-- message will print registration exception-->
    	<h3 style = "color:red;"> <span>${message}</span> </h3> 
    	
    	<form action = "register" method = "POST">
		<table cellspacing = '5'>
			<tr>
				<td><label for="username"> UserName : </label></td>
				<td><input type="text" name = "username" placeholder = "Enter your name here" required> <br></td>
			</tr>		
			<tr>
				<td><label for="userid"> User ID : </label></td>
				<td><input type="text" name = "userid" placeholder = "Enter userid" required> <br></td>
			</tr>		
			<tr>
				<td><label for="firstPassword"> Password : </label></td>
				<td><input type="password" name = "firstPassword" placeholder = "Enter password" required> <br></td>
			</tr>
			<tr>
				<td><label for="secondPassword"> Re-Enter the Password : </label></td>
				<td><input type="password" name = "secondPassword" placeholder = "Enter password again" required> <br></td>
			</tr>
			<tr>
			</tr>
			<tr>
			</tr>
			<tr>
				<td> </td>
				<td><input type = "submit" value = "SIGN UP">
			</tr>			
		</table>
		</form>
		
    </div>
    </center>
    </body>

</html>