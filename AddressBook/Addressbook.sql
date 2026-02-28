
-- =================================================
-- UC1 : Create Address Book Service Database
-- =================================================

CREATE DATABASE address_book_service; 
SHOW DATABASES;
USE address_book_service;
SELECT DATABASE();


-- =================================================
-- UC2 : Create Address Book Table
-- =================================================

USE address_book_service;

CREATE TABLE address_book (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    address VARCHAR(200),
    city VARCHAR(50),
    state VARCHAR(50),
    zip VARCHAR(10),
    phone_number VARCHAR(15),
    email VARCHAR(100),
    PRIMARY KEY (id)
);

-- =================================================
-- UC3 : Insert New Contacts
-- =================================================

INSERT INTO address_book
(first_name, last_name, address, city, state, zip, phone_number, email)
VALUES
('Bill', 'Gates', '108 Microsoft Road', 'Seattle', 'Washington', '98052', '9999999999', 'bill@microsoft.com'),
('Elon', 'Musk', '370 Tesla Street', 'Austin', 'Texas', '73301', '8888888888', 'elon@tesla.com');


-- =================================================
-- UC4 : Update Existing Contact by Name
-- =================================================

USE address_book_service;

UPDATE address_book set phone_number = '7777777777'
where first_name = 'Bill' 
AND last_name = 'Gates';


-- =================================================
-- UC5 : Delete Contact by Name
-- =================================================

USE address_book_service;

DELETE from address_book 
where first_name = 'Elon' 
AND last_name = 'Musk';


-- =================================================
-- UC6 : Retrieve Contacts by City or State
-- =================================================

USE address_book_service;

SELECT *
FROM address_book
WHERE city = 'Seattle';

SELECT *
FROM address_book
WHERE state = 'Texas';



-- =================================================
-- UC7 : Count Contacts by City and State
-- =================================================

SELECT city, COUNT(*) AS total_contacts
FROM address_book
GROUP BY city;

SELECT state, COUNT(*) AS total_contacts
FROM address_book
GROUP BY state;


-- =================================================
-- UC8 : Sort Contacts Alphabetically by Name
-- =================================================

SELECT *
FROM address_book
WHERE city = 'Seattle'
ORDER BY first_name ASC, last_name ASC;


-- =================================================
-- UC9 : Add Address Book Name and Type
-- =================================================

USE address_book_service;

alter table address_book add address_book_name varchar(50);

alter table address_book add address_book_type varchar(50);

update address_book set address_book_name = 'Personal' , address_book_type = 'Friends' where first_name = 'Bill';

update address_book set address_book_name = 'Office' , address_book_type = 'Colleague' where first_name = 'Elon';

select * from address_book;


-- =================================================
-- UC10 : Count Contacts by Type
-- =================================================

USE address_book_service;

SELECT address_book_type , COUNT(*) AS total_contacts
FROM address_book
GROUP BY address_book_type;


-- =================================================
-- UC11 : Add Person to Both Friend and Family
-- =================================================

USE address_book_service;

INSERT INTO address_book
(first_name, last_name, address, city, state, zip, phone_number, email, address_book_name, address_book_type)
SELECT first_name, last_name, address, city, state, zip, phone_number, email, address_book_name, 'Family'
FROM address_book
WHERE first_name = 'Bill' AND last_name = 'Gates';



