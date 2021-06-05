<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
        
        <style>
        	h1
			{  
			    font-size: 140%; 
			    color: teal; 
			    margin-bottom: .5em;  
			}
        	.title {       		
        		font-family: Arial, Helvetica, sans-serif;   
        	}        	
        	small {
        		font-size: 85%;
        	}
        	body {
        		margin: 2em;
        	}
        </style>
    </head>
    <body>
    	
    	<div class = "title">
    		<h1><b>Thank you for shopping with us!!</b></h1>
    		<small><b>We have many more offers for you...shop again !!</b></small>
    	</div>
        <br>
        <form action="cart" method="post">
                <input type="hidden" name="option" value="init">
                <input type="submit" value="SHOP AGAIN">
        </form>
        
        <br>
        <form action="logout" method="post">
        	<input type="submit" value="LOGOUT">
        </form>
        
    </body>
</html>
