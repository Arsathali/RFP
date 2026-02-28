/* =================================================
   UC1 : Create Payroll Service Database
   ================================================= */

CREATE DATABASE payroll_service;
SHOW DATABASES;
USE payroll_service;
SELECT DATABASE();

/* =================================================
   UC2 : Create Employee Payroll Table
   ================================================= */

USE payroll_service;

CREATE TABLE employee_payroll (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    salary decimal(10,2) NOT NULL,
    start_date DATE NOT NULL
);

/* =================================================
   UC3 : Insert Employee Payroll Data
   ================================================= */

USE payroll_service;

INSERT INTO employee_payroll (name, salary, start_date)
VALUES 
('Bill', 1000000.00, '2018-01-03'),
('Terisa', 2000000.00, '2019-11-13'),
('Charlie', 3000000.00, '2020-05-21');

/* =================================================
   UC4 : Retrieve All Employee Payroll Data
   ================================================= */

USE payroll_service;

SELECT * FROM employee_payroll;

/* =================================================
   UC5 : Retrieve Salary and Employees by Condition
   ================================================= */

USE payroll_service;

/* Retrieve Salary of a Particular Employee */

SELECT salary 
FROM employee_payroll 
WHERE name = 'Bill';


/* Retrieve Employees Joined Between Date Range */
SELECT * 
FROM employee_payroll
WHERE start_date 
BETWEEN CAST('2018-01-01' AS DATE) 
AND DATE(NOW());


/* =================================================
   UC6 : Add Gender Column and Update Records
   ================================================= */

USE payroll_service;

alter table employee_payroll add gender CHAR(1) after name;

update employee_payroll set gender = 'M' where name = 'Bill' or name = 'Charlie';

update employee_payroll set gender = 'F' where name = 'Terisa';



/* =================================================
   UC7 : Aggregate Functions with GROUP BY
   ================================================= */

USE payroll_service;

SELECT gender, SUM(salary) AS total_salary
FROM employee_payroll
GROUP BY gender;

SELECT gender, AVG(salary) AS average_salary
FROM employee_payroll
GROUP BY gender;

SELECT gender, MIN(salary) AS minimum_salary
FROM employee_payroll
GROUP BY gender;

SELECT gender, MAX(salary) AS maximum_salary
FROM employee_payroll
GROUP BY gender;

SELECT gender, COUNT(*) AS total_employees
FROM employee_payroll
GROUP BY gender;


