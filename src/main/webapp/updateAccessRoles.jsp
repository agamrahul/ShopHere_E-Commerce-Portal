<%@page import="entities.AccessRoles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@page import="entities.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Update Profile</title>
		<style>	
		
			h1
			{  
			    font-size: 140%; 
			    color: teal; 
			    margin-bottom: .5em;  
			}
        	.title {       		
        		font-family: Arial, Helvetica, sans-serif;   
			    margin-left: 2em;  
			    margin-right: 2em;
        	}        	
        	label {
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
	
	<center>
	<!-- getting the user object from session -->
	<%
		AccessRoles newAccessRole = (AccessRoles) session.getAttribute("modRole");
		System.out.println("UPDATE ACCESS JSP : " + newAccessRole);
		String roleName = (String) session.getAttribute("roleName");
		System.out.println("UPDATE ACCESS JSP : " + roleName);
	%>
	<div class = "title">
		<h1> UPDATE ACCESS ROLE </h1>
	</div>	
	<form  action="accessRole" method="POST">
        <fieldset>           
            <table cellspacing="5">           
               <tr>
					<th> ROLE </th>
					<td><input type = "text" disabled name = "newRole" value ="<%= newAccessRole.getRoleName()%>"></td>
				</tr>
                <tr>
					<th> Access to All Users </th>
					<td><input type = "radio" name = "allUsers" value = "true" > YES</td>
					<td><input type = "radio" name = "allUsers" value = "false" > NO</td>				
				</tr>
				<tr>
					<th> Edit own Profile </th>
					<td><input type = "radio" name = "ownProfile" value = "true" > YES</td>
					<td><input type = "radio" name = "ownProfile" value = "false" > NO</td>	
				</tr>
				<tr>
					<th> Edit Inventory </th>
					<td><input type = "radio" name = "inventory" value = "true" > YES</td>
					<td><input type = "radio" name = "inventory" value = "false" > NO</td>	
				</tr>
				<tr>
					<th> Edit Access Roles </th>
					<td><input type = "radio" name = "accessRoles" value = "true" > YES</td>
					<td><input type = "radio" name = "accessRoles" value = "false" > NO</td>	
				</tr>
				<tr>
					<th> Create Orders </th>
					<td><input type = "radio" name = "createorders" value = "true" > YES</td>
					<td><input type = "radio" name = "createorders" value = "false" > NO</td>	
				</tr>
            </table>
        </fieldset>
        <br><br>
        <input type="hidden" name="option" value="modify" >
        <input type = "hidden" name = "oldRole" value = "<c:out value='${roleName}'/>">
		<input type = "submit" value = "MODIFY">
		
    </form>	
    	
    <form action="accessRole" method="POST">
    	<input type="hidden" name="option" value="cancel" >
    	<input type = "submit" value = "CANCEL">
    </form>
    
    </center>
	</body>
</html>