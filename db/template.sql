CREATE TABLE alexeyku_users(
  user_id INT PRIMARY KEY,
  name VARCHAR(30),
  surname VARCHAR(30),
  login VARCHAR(40) UNIQUE,
  password VARCHAR (40)
);

CREATE TABLE alexeyku_commodities(
  commodity_id INT PRIMARY KEY,
  name VARCHAR (40),
  price INT
);

INSERT INTO alexeyku_commodities() VALUES (1,'iPhone 7', 449);
INSERT INTO alexeyku_commodities() VALUES (2,'iPhone 7 Plus', 569);
INSERT INTO alexeyku_commodities() VALUES (3,'iPhone 8', 599);
INSERT INTO alexeyku_commodities() VALUES (4,'iPhone 8 Plus', 699);
INSERT INTO alexeyku_commodities() VALUES (5,'iPhone Xr', 749);
INSERT INTO alexeyku_commodities() VALUES (6,'iPhone Xs', 999);
INSERT INTO alexeyku_commodities() VALUES (7,'iPhone Xs Max', 1099);
INSERT INTO alexeyku_commodities() VALUES (8,'13-inch MacBook Pro 128GB Storage', 1299);
INSERT INTO alexeyku_commodities() VALUES (9,'13-inch MacBook Pro 256GB Storage', 1499);
INSERT INTO alexeyku_commodities() VALUES (10,'13-inch MacBook Pro 512GB Storage', 1999);
INSERT INTO alexeyku_commodities() VALUES (11,'15-inch MacBook Pro 256GB Storage', 2399);
INSERT INTO alexeyku_commodities() VALUES (12,'13-inch MacBook Air 128GB Storage', 1199);
INSERT INTO alexeyku_commodities() VALUES (13,'13-inch MacBook Air 256GB Storage', 1399);


CREATE TABLE alexeyku_carts(
  commodity_id INT,
  user_id INT,
  quantity INT,
  FOREIGN KEY (commodity_id) REFERENCES alexeyku_commodities(commodity_id) ON DELETE CASCADE ,
  FOREIGN KEY (user_id) REFERENCES alexeyku_users(user_id) ON DELETE CASCADE,
  PRIMARY KEY (user_id,commodity_id)
);