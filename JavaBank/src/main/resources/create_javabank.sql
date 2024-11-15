DROP DATABASE IF EXISTS javabank;
CREATE DATABASE javabank;
USE javabank;
-- DROP TABLE IF EXISTS customers;
-- DROP TABLE IF EXISTS accounts;

-- Create customers table;
CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT,
    customer_name VARCHAR(45) NOT NULL,
    customer_password VARCHAR(45) NOT NULL,
    PRIMARY KEY (customer_id)
);

-- Create accounts table
CREATE TABLE accounts (
    account_id INT AUTO_INCREMENT,
    customer_id INT,
    account_type VARCHAR(15) NOT NULL,
    balance FLOAT(9,2) DEFAULT 0,
    PRIMARY KEY (account_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Insert random data into customers table
INSERT INTO customers (customer_name, customer_password) VALUES
('Rui', '1234'),
('Ana', '4321');

-- Insert random data into accounts table
INSERT INTO accounts (customer_id, account_type) VALUES
(1, 'CHECKING'),
(1, 'SAVINGS'),
(2, 'CHECKING'),
(2, 'SAVINGS');
