INSERT INTO CAR (car_id, model, color, price) VALUES (1, 'MERCEDES', 'RED', 18.25);
INSERT INTO CAR (car_id, model, color, price) VALUES (2, 'PORSCHE', 'WHITE', 19.55);
INSERT INTO CAR (car_id, model, color, price) VALUES (3, 'AUDI', 'BLACK', 18.90);
INSERT INTO CAR (car_id, model, color, price) VALUES (4, 'LEXUS', 'GRAY', 17.50);
INSERT INTO CAR (car_id, model, color, price) VALUES (5, 'SUBARU', 'YELLOW', 19.90);

INSERT INTO CUSTOMER (customer_id, first_name, last_name, address) VALUES (1, 'FUSER11', 'LUSER11', 'STREET11-11');
INSERT INTO CUSTOMER (customer_id, first_name, last_name, address) VALUES (2, 'FUSER12', 'LUSER12', 'STREET12-12');
INSERT INTO CUSTOMER (customer_id, first_name, last_name, address) VALUES (3, 'FUSER13', 'LUSER13', 'STREET13-13');
INSERT INTO CUSTOMER (customer_id, first_name, last_name, address) VALUES (4, 'FUSER14', 'LUSER14', 'STREET14-14');
INSERT INTO CUSTOMER (customer_id, first_name, last_name, address) VALUES (5, 'FUSER15', 'LUSER15', 'STREET15-15');

INSERT INTO CAR_ORDER ( order_car_id, date_order, customer_id, car_id, order_status) VALUES(1, 2021.10.10, 5, 1, 'CREATED');
INSERT INTO CAR_ORDER ( order_car_id, date_order, customer_id, car_id, order_status) VALUES(2, 2021.11.11, 4, 2, 'CREATED');
INSERT INTO CAR_ORDER ( order_car_id, date_order, customer_id, car_id, order_status) VALUES(3, 2021.12.12, 3, 3, 'CREATED');
INSERT INTO CAR_ORDER ( order_car_id, date_order, customer_id, car_id, order_status) VALUES(4, 2021.12.15, 2, 4, 'CREATED');
INSERT INTO CAR_ORDER ( order_car_id, date_order, customer_id, car_id, order_status) VALUES(5, 2021.12.17, 1, 5, 'CREATED');