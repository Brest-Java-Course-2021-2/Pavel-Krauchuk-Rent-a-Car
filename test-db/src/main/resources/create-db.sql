
DROP TABLE IF EXISTS car;

DROP TABLE IF EXISTS customer;

DROP TABLE IF EXISTS car_order;

CREATE TABLE car (
    car_id int NOT NULL auto_increment,
    model varchar(255) NOT NULL,
    color varchar(255) NOT NULL,
    price decimal (10, 2)NOT NULL,
    CONSTRAINT car_pk PRIMARY KEY (car_id)
);

CREATE TABLE customer (
    customer_id int NOT NULL auto_increment,
    firstname varchar(255) NOT NULL,
    lastname varchar(255) NOT NULL,
    address char NOT NULL,
    CONSTRAINT customer_pk PRIMARY KEY (customer_id)
);

CREATE TABLE car_order(
    order_car_id int NOT NULL auto_increment,
    date_order date NOT NULL,
    customer_id int NOT NULL,
    car_id int NOT NULL,
    order_status ENUM ('CREATED', 'APPROVED', 'DENIED', 'FINISHED') DEFAULT 'CREATED'

)
