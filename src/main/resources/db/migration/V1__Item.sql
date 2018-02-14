CREATE TABLE ITEMS (
  item_id BIGINT PRIMARY KEY,
  item_sku VARCHAR(25),
  item_name VARCHAR(100) NOT NULL,
  item_qty INT NOT NULL
);