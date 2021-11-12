CREATE TABLE IF NOT EXISTS CUISINES (
  id                     INTEGER NOT NULL PRIMARY KEY,
  name                   VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS RESTAURANTS (
  id                     IDENTITY NOT NULL PRIMARY KEY,
  name                   VARCHAR(50) NOT NULL,
  customer_rating        INTEGER NOT NULL,
  distance               INTEGER NOT NULL,
  price                  INTEGER NOT NULL,
  cuisine_id             INTEGER NOT NULL
);