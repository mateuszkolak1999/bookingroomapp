INSERT INTO staff_details(firstname,lastname,pesel)
VALUES ("Mateusz","Kolak","12345678901");

INSERT INTO customer_details(date,firstname,lastname,pesel)
VALUES ('2015-11-05 14:29:36',"Adam","Kowalski","12345678901");

INSERT INTO customer(email,password,username,customer_details_id)
VALUES ("aa@aa.pl","test123","test123",1);

INSERT INTO role(name,salary)
VALUES ("sprzataczka",1250);

INSERT INTO staff(email,password,username,role_id,staff_details_id)
VALUES ("aa@aa.pl","test123","test123",1,1);

INSERT INTO equipment(name, count)
VALUES ("Krzesła", 2);

INSERT INTO room(available,cost_per_day,left_day, people)
VALUES (1,100,0,2);

INSERT INTO room_equipment(room_id,equipment_id)
VALUES (1,1);

INSERT INTO booking(booking_from, booking_to, cost, customer_id)
VALUES ('2020-08-01','2020-08-10',100,1);

INSERT INTO booking(booking_from, booking_to, cost, customer_id)
VALUES ('2020-08-20','2020-08-25',500,1);

INSERT INTO booking_room(booking_id,room_id)
VALUES (1,1);

INSERT INTO booking_room(booking_id,room_id)
VALUES (2,1);