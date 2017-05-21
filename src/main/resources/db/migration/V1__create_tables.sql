CREATE TABLE users (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

insert into users (id, name) values ("001", "admin");


CREATE TABLE products (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  price DOUBLE
);

CREATE TABLE orders (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  user_id VARCHAR(255) NOT NULL
);

CREATE TABLE order_items (
  product_id VARCHAR(255) NOT NULL,
  amount DOUBLE NOT NULL,
  quantity INT NOT NULL,
  order_id VARCHAR(255) NOT NULL
);