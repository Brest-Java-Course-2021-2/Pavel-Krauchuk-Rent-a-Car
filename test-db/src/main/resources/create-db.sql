DROP TABLE IF EXISTS car;
CREATE TABLE car (
    car_id INT NOT NULL auto_increment,
    model VARCHAR(255) NOT NULL,
    color VARCHAR(255),
    year_of_issue INT,
    car_number VARCHAR(7) UNIQUE,
    CONSTRAINT car_pk PRIMARY KEY (car_id)
);