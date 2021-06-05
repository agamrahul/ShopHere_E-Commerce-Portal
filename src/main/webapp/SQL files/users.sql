CREATE TABLE users (
	
    username VARCHAR(25) ,
    userid VARCHAR(10) PRIMARY KEY ,
    passkey VARCHAR(15) NOT NULL ,
    role VARCHAR(20) ,
    
    FOREIGN KEY (role) REFERENCES accessroles(role)
);

INSERT INTO users VALUES ('Tyrion' , 'Cust1' , 'cust1' , 'CUSTOMER');
INSERT INTO users VALUES ('Shae' , 'Cust2' , 'cust2' , 'CUSTOMER');
INSERT INTO users VALUES ('Scofield' , 'Cust3' , 'cust3' , 'CUSTOMER');
INSERT INTO users VALUES ('John snow' , 'mgt1' , 'mgt1' , 'MANAGER');
INSERT INTO users VALUES ('Arya Stark' , 'mgt2' , 'mgt2' , 'MANAGER');
INSERT INTO users VALUES ('Harvey Specter' , 'admin' , 'admin' , 'ADMIN');