CREATE TABLE accessroles (
	
    role VARCHAR(20) PRIMARY KEY ,
    
    users BOOLEAN ,
    ownprofile BOOLEAN ,
    inventory BOOLEAN ,
    modifyroles BOOLEAN ,
    orders BOOLEAN 
);

INSERT INTO accessroles VALUES( 'ADMIN' , 1, 1, 1, 1, 1);
INSERT INTO accessroles VALUES( 'CUSTOMER' , 0, 1, 0, 0, 1);
INSERT INTO accessroles VALUES( 'MANAGER' , 0, 0, 1,0, 1);