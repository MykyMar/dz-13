
CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    age INTEGER NOT NULL,
    country VARCHAR(50) NOT NULL,
    company VARCHAR(50) NOT NULL,
    salary NUMERIC(10, 2) NOT NULL
);


INSERT INTO employees (first_name, last_name, age, country, company, salary)
VALUES
('John', 'Doe', 30, 'USA', 'Google', 120000.00),
('Jane', 'Smith', 25, 'Canada', 'Amazon', 95000.00),
('Emily', 'Jones', 35, 'UK', 'Microsoft', 130000.00),
('Michael', 'Brown', 28, 'Australia', 'Atlassian', 105000.00),
('Sarah', 'Wilson', 32, 'New Zealand', 'TechCorp', 115000.00);
