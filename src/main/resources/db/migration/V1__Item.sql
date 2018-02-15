CREATE TABLE ITEMS (
  item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  item_sku VARCHAR(25) NOT NULL,
  item_name VARCHAR(100) NOT NULL,
  item_qty INT NOT NULL
);

CREATE TABLE ITEMSIN (
  item_in_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  item_in_time DATETIME NOT NULL,
  item_in_sku VARCHAR(25) NOT NULL,
  item_in_name VARCHAR(100) NOT NULL,
  item_in_qty_pesan INT NOT NULL,
  item_in_qty_terima INT NOT NULL,
  item_in_harga_beli INT NOT NULL,
  item_in_total INT NOT NULL,
  item_in_kwitansi INT NOT NULL,
  item_in_notes VARCHAR(100) NOT NULL
);

CREATE TABLE ITEMSOUT (
  item_out_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  item_out_time DATETIME NOT NULL,
  item_out_sku VARCHAR(25) NOT NULL,
  item_out_name VARCHAR(100) NOT NULL,
  item_out_qty_out INT NOT NULL,
  item_out_harga_jual INT NOT NULL,
  item_out_total INT NOT NULL,
  item_out_notes VARCHAR(100) NOT NULL
);