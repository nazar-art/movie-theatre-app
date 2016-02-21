
------------------------
-- Populate Users table
------------------------
INSERT INTO Users VALUES (1, 'Garry Potter', '2001-05-01', 'potter@gmail.com', 'admin', NULL);
INSERT INTO Users VALUES (2, 'Ron Weasley',  '2000-05-01', 'ron@gmail.com', 'user', NULL);
INSERT INTO Users VALUES (3, 'Germiona Grendjer', '2000-05-01', 'germiona@gmail.com', 'user', NULL);
INSERT INTO Users VALUES (4, 'Rubeus Hagrid', '1972-05-01', 'hagrid@gmail.com', 'user', NULL);
INSERT INTO Users VALUES (5, 'Severus Snape', '1980-05-01', 'snape@gmail.com', 'user', NULL);

------------------------
-- Populate Events table
------------------------
INSERT INTO Events (event_id, event_name, event_price, event_rating, event_date)
VALUES (1, 'Green Mile', 60.0, 'high', '2016-02-28');
INSERT INTO Events (event_id, event_name, event_price, event_rating, event_date)
VALUES (2, 'Gone by the wind', 50.0, 'middle', '2016-02-28');
INSERT INTO Events (event_id, event_name, event_price, event_rating, event_date)
VALUES (3, 'Mad Max: Furry road', 90.0, 'low', '2016-02-28')





