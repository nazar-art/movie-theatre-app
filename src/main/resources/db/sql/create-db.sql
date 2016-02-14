--DROP TABLE users IF EXISTS;

----------------------
-- Create users table
----------------------
CREATE TABLE users (
  id        INTEGER PRIMARY KEY,
  name      VARCHAR(30),
  bithday   DATE(30),
  email     VARCHAR(30),
  role      VARCHAR(20),
);

-- todo finish filling all needed tables
