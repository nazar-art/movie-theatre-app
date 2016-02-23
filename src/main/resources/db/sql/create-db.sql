----------------------
-- Create Users table
----------------------
CREATE TABLE Users (
  user_id        INT PRIMARY KEY NOT NULL,
  user_name      VARCHAR(60) NOT NULL,
  user_birthday  DATE,
  user_email     VARCHAR(60),
  user_role      VARCHAR(20),
);

----------------------
-- Create Events table
----------------------
CREATE TABLE Events (
  event_id       INT PRIMARY KEY NOT NULL,
  event_name     VARCHAR(100) NOT NULL,
  event_price    DECIMAL(8,2),
  event_rating   VARCHAR(60),
  event_date     DATE,
);

---------------------------
-- Create Auditoriums table
---------------------------
CREATE TABLE Auditoriums (
  aud_id         INT PRIMARY KEY NOT NULL,
  aud_name       VARCHAR(60),
  aud_seats      INT,
  aud_vip        INT,
);

-----------------------
-- Create Tickets table
-----------------------
CREATE TABLE Tickets (
  tick_id        INT PRIMARY KEY NOT NULL,
  tick_price     DECIMAL(8,2),
  event_id       INT CONSTRAINT FK_Tickets_Users
                     REFERENCES Events ON UPDATE RESTRICT ON DELETE RESTRICT,
  user_id        INT CONSTRAINT FK_Tickets_Events
                     REFERENCES Users ON UPDATE RESTRICT ON DELETE CASCADE,
);


-----------------------------
-- Create AspectCounter table
-----------------------------
CREATE TABLE AspectCounter (
  aspect_id      INT PRIMARY KEY NOT NULL,
  aspect_name    VARCHAR(80),
  aspect_source  VARCHAR(150),
  aspect_counter INT
);


----------------------
-- Define foreign keys
----------------------
--ALTER TABLE Tickets
--ADD CONSTRAINT FK_Tickets_Users FOREIGN KEY (user_id) REFERENCES Users (user_id);
--ALTER TABLE Tickets
--ADD CONSTRAINT FK_Tickets_Events FOREIGN KEY (event_id) REFERENCES Events (event_id);
