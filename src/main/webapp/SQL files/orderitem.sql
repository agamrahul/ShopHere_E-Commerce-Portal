CREATE TABLE orderitem(
	
	ordernumber INT,
	productid INT,
	productname VARCHAR(75),
	quantity INT ,
	price DECIMAL(19,2) ,
	
	FOREIGN KEY (ordernumber) REFERENCES orders(ordernumber),
	FOREIGN KEY (productid) REFERENCES inventory(productid)
);