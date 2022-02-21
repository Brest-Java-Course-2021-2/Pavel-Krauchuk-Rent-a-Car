DROP TABLE IF EXISTS  CAR;

DROP TABLE IF EXISTS car_order;

CREATE TABLE CAR (
    car_id INT NOT NULL AUTO_INCREMENT,
    model varchar(255) NOT NULL,
    price DECIMAL NOT NULL,
    CONSTRAINT car_pk PRIMARY KEY (car_id)
);

CREATE TABLE ORDER_CAR (
    order_id INT NOT NULL AUTO_INCREMENT,
    date_order TIMESTAMP NOT NULL,
    customer varchar(255) NOT NULL,
    car_id INT NOT NULL,
    rental_period INT NOT NULL,
    CONSTRAINT car_order_pk PRIMARY KEY (order_id)
);