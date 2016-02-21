----------------------
-- Create Users table
----------------------
CREATE TABLE Users (
  user_id        INTEGER PRIMARY KEY NOT NULL,
  user_name      VARCHAR(30) NULL,
  user_birthday  DATETIME NULL,
  user_email     VARCHAR(30) NULL,
  user_role      VARCHAR(20) NULL,
);

----------------------
-- Create Events table
----------------------
CREATE TABLE Events (
  event_id       INTEGER PRIMARY KEY NOT NULL,
  event_name     VARCHAR(30) NULL,
  event_price    DECIMAL(8,2) NULL,
  event_rating   VARCHAR(60) NULL,
  event_date     VARCHAR(300) NULL,
);

---------------------------
-- Create Auditoriums table
---------------------------
CREATE TABLE Auditoriums (
  aud_id         INTEGER PRIMARY KEY NOT NULL,
  aud_name       VARCHAR(30),
  aud_seats      INTEGER,
  aud_vip        INTEGER,
);

-----------------------
-- Create Tickets table
-----------------------
CREATE TABLE Tickets (
  tick_id        INTEGER PRIMARY KEY NOT NULL,
  tick_price     DECIMAL(8,2),
  event_id       INTEGER,
  user_id        INTEGER,
);

--todo add tables + DAO layer for AOP info

----------------------
-- Define foreign keys
----------------------
ALTER TABLE Tickets
ADD CONSTRAINT FK_Tickets_Users FOREIGN KEY (user_id) REFERENCES Users (user_id);

ALTER TABLE Tickets
ADD CONSTRAINT FK_Tickets_Events FOREIGN KEY (event_id) REFERENCES Events (event_id);
