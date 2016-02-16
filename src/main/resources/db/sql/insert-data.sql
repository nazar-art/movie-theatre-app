------------------------
-- Populate Users table
------------------------
INSERT INTO Users VALUES (1, 'Garry Potter', '2001-05-01', 'potter@gmail.com', 'admin');
INSERT INTO Users VALUES (2, 'Ron Weasley',  '2000-05-01', 'ron@gmail.com', 'user');
INSERT INTO Users VALUES (3, 'Germiona Grendjer', '2000-05-01', 'germiona@gmail.com', 'user');
INSERT INTO Users VALUES (4, 'Rubeus Hagrid', '1972-05-01', 'hagrid@gmail.com', 'user');
INSERT INTO Users VALUES (5, 'Severus Snape', '1980-05-01', 'snape@gmail.com', 'user');

------------------------
-- Populate Events table
------------------------
INSERT INTO Events (event_id, event_name, event_price, event_rating, event_date)
VALUES (1, 'Green Mile', 60.0, 'EventRating.HIGH');
INSERT INTO Events (event_id, event_name, event_price, event_rating, event_date)
VALUES (2, 'Gone by the wind', 50.0, 'EventRating.MIDDLE');
INSERT INTO Events (event_id, event_name, event_price, event_rating, event_date)
VALUES (3, 'Mad Max: Furry road', 90.0, 'EventRating.LOW');

