DROP TABLE IF EXISTS Check;  
CREATE TABLE Check (  
checkId INT AUTO_INCREMENT  PRIMARY KEY,  
uri VARCHAR(50) NOT NULL,  
interval INT(8) NOT NULL  
);  