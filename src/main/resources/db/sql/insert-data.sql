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
INSERT INTO t_event (name, price, rating, airDate)
VALUES ('Green Mile', 60.0, 'HIGH', '2016-02-28');
INSERT INTO t_event (name, price, rating, airDate)
VALUES ('Gone by the wind', 50.0, 'MEDIUM', '2016-02-28');
INSERT INTO t_event (name, price, rating, airDate)
VALUES ('Mad Max: Furry road', 90.0, 'LOW', '2016-02-28');
INSERT INTO t_event (name, price, rating, airDate)
VALUES ('Tow Waits tribute concert', 110.0, 'HIGH', '2016-03-13');
INSERT INTO t_event (name, price, rating, airDate)
VALUES ('Fredie Mercurry tribute concert', 200.0, 'HIGH', '2016-03-19');


-------------------------
-- Populate Tickets table
-------------------------
--INSERT INTO Tickets (tick_id, tick_price, event_id, user_id)
--VALUES (1, 60.0, 1, 1);
--INSERT INTO Tickets (tick_id, tick_price, event_id, user_id)
--VALUES (2, 120.0, 2, 2);
--INSERT INTO Tickets (tick_id, tick_price, event_id, user_id)
--VALUES (3, 200.0, 5, 3);




