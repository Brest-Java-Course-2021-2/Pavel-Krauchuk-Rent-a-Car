DROP TABLE IF EXISTS  car;

DROP TABLE IF EXISTS customer;

CREATE TABLE car (
    car_id INT NOT NULL AUTO_INCREMENT,
    model varchar(255) NOT NULL,
    price DECIMAL NOT NULL,
    CONSTRAINT car_pk PRIMARY KEY (car_id)
);

CREATE TABLE customer (
    customer_id INT NOT NULL AUTO_INCREMENT,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    address varchar(255) NOT NULL,
    CONSTRAINT customer_pk PRIMARY KEY (customer_id)
);