CREATE TABLE inventory (
	
    productid INT PRIMARY KEY,
    productname VARCHAR(70) NOT NULL,
    price DECIMAL(19,2) NOT NULL ,
    quantity INT NOT NULL
);

INSERT INTO inventory VALUES(1401,'86 (the band) - True Life songs and Pictures', 26.75,1000);
INSERT INTO inventory VALUES(1402,'Joe Rut - Genuine Wood', 138.96 ,1000);
INSERT INTO inventory VALUES(1403,'Paddlefoot - The first CD', 20.38,1000);
INSERT INTO inventory VALUES(1404,'Paddlefoot - The Second CD', 100.38 ,1000);