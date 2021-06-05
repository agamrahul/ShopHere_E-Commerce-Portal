CREATE TABLE orders(
	ordernumber INT PRIMARY KEY AUTO_INCREMENT ,
	userid VARCHAR(10) NOT NULL,
	date DATE NOT NULL ,
	
	FOREIGN KEY (userid) REFERENCES users(userid)
);