------------------------------
-- Populate t_auditorium table
------------------------------
INSERT INTO t_auditorium (name, seats, vip)
VALUES ('La Scala', 350, '12,13,14,15,16,17,18,19,20');
INSERT INTO t_auditorium (name, seats, vip)
VALUES ('Grand Opera', 450, '2,3,4,5,6,7');
INSERT INTO t_auditorium (name, seats, vip)
VALUES ('Carnegie Hall', 500, '12,13,14,15,16,17,18,19,20');
INSERT INTO t_auditorium (name, seats, vip)
VALUES ('Disco Radio Hall', 150, '1,7,13,27');

------------------------
-- Populate t_user table
------------------------
INSERT INTO t_user (name, birthday, email, role)
VALUES ('Garry Potter', '2001-05-01', 'potter@gmail.com', 'admin');
INSERT INTO t_user (name, birthday, email, role)
VALUES ('Ron Weasley',  '2000-05-01', 'ron@gmail.com', 'admin');
INSERT INTO t_user (name, birthday, email, role)
VALUES ('Germiona Grendjer', '2000-05-01', 'germiona@gmail.com', 'admin');
INSERT INTO t_user (name, birthday, email, role)
VALUES ('Rubeus Hagrid', '1972-05-01', 'hagrid@gmail.com', 'user');
INSERT INTO t_user (name, birthday, email, role)
VALUES ('Severus Snape', '1980-05-01', 'snape@gmail.com', 'user');

------------------------
-- Populate t_event table
------------------------
INSERT INTO t_event (name, airDate, ticketPrice, rating, auditorium_id)
VALUES ('Green Mile', '2016-02-28', 60.0, 'HIGH', 1);
INSERT INTO t_event (name, airDate, ticketPrice, rating, auditorium_id)
VALUES ('Gone by the wind', '2016-02-28', 50.0, 'MEDIUM', 2);
INSERT INTO t_event (name, airDate, ticketPrice, rating, auditorium_id)
VALUES ('Mad Max: Furry road', '2016-02-28', 90.0, 'LOW', 3);
INSERT INTO t_event (name, airDate, ticketPrice, rating, auditorium_id)
VALUES ('Tow Waits tribute concert', '2016-02-28', 110.0, 'HIGH', 4);
--INSERT INTO t_event (name, airDate, ticketPrice, rating, auditorium_id)
--VALUES ('Fredie Mercurry tribute concert', '2016-02-28', 200.0, 'HIGH', 1);


--------------------------
-- Populate t_ticket table
--------------------------
INSERT INTO t_ticket (name, price, onDate, event_id, user_id)
VALUES ('23', 60.0, '2016-04-28', 1, 1);
INSERT INTO t_ticket (name, price, onDate, event_id, user_id)
VALUES ('32', 70.0, '2016-04-18', 2, 1);
INSERT INTO t_ticket (name, price, onDate, event_id, user_id)
VALUES ('17', 80.0, '2016-04-09', 3, 1);
INSERT INTO t_ticket (name, price, onDate, event_id, user_id)
VALUES ('47', 90.0, '2016-04-08', 4, 1);
INSERT INTO t_ticket (name, price, onDate, event_id, user_id)
VALUES ('72', 70.0, '2016-04-08', 1, 1);
INSERT INTO t_ticket (name, price, onDate, event_id, user_id)
VALUES ('49', 200.0, '2016-04-08', 3, 1);
INSERT INTO t_ticket (name, price, onDate, event_id, user_id)
VALUES ('57', 250.0, '2016-04-08', 4, 1);





