DROP TABLE IF EXISTS  car;

CREATE TABLE car (
    car_id INT NOT NULL AUTO_INCREMENT,
    model varchar(255) NOT NULL,
    CONSTRAINT car_pk PRIMARY KEY (car_id)
);
