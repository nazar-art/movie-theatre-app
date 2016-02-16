
----------------------
-- Create Users table
----------------------
CREATE TABLE Users (
  user_id        INTEGER PRIMARY KEY,
  user_name      VARCHAR(30),
  user_birthday  DATETIME,
  user_email     VARCHAR(30),
  user_role      VARCHAR(20),
);

----------------------
-- Create Events table
----------------------
CREATE TABLE Events (
  event_id       INTEGER PRIMARY KEY,
  event_name     VARCHAR(30),
  event_price    DECIMAL(8,2),
  event_rating   VARCHAR(30),
  event_date     DATETIME,
);

---------------------------
-- Create Auditoriums table
---------------------------
CREATE TABLE Auditoriums (
  aud_id         INTEGER PRIMARY KEY,
  aud_name       VARCHAR(30),
  aud_seats      INTEGER,
  aud_vip        INTEGER,
);

-----------------------
-- Create Tickets table
-----------------------
CREATE TABLE Tickets (
  tick_id        INTEGER PRIMARY KEY,
  event_id       VARCHAR(30),
  tick_price     DECIMAL(8,2),
  user_id        INTEGER,
);


----------------------
-- Define foreign keys
----------------------
ALTER TABLE Tickets ADD CONSTRAINT FK_Tickets_Events FOREIGN KEY (event_id) REFERENCES Events (event_id);
ALTER TABLE Tickets ADD CONSTRAINT FK_Tickets_Users FOREIGN KEY (user_id) REFERENCES Events (user_id);
