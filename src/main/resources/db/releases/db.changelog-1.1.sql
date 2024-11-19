-- Insert initial Products with categories (UUIDs will be auto-generated)
INSERT INTO stacy_store_db.product (name, price, category)
VALUES ('Laptop', 999.99, 'ELECTRONICS');
INSERT INTO stacy_store_db.product (name, price, category)
VALUES ('Smartphone', 499.99, 'ELECTRONICS');
INSERT INTO stacy_store_db.product (name, price, category)
VALUES ('Headphones', 199.99, 'ELECTRONICS');
INSERT INTO stacy_store_db.product (name, price, category)
VALUES ('Keyboard', 89.99, 'ELECTRONICS');
INSERT INTO stacy_store_db.product (name, price, category)
VALUES ('Mouse', 29.99, 'ELECTRONICS');
INSERT INTO stacy_store_db.product (name, price, category)
VALUES ('T-shirt', 19.99, 'CLOTHING');
INSERT INTO stacy_store_db.product (name, price, category)
VALUES ('Jeans', 49.99, 'CLOTHING');
INSERT INTO stacy_store_db.product (name, price, category)
VALUES ('Pizza', 15.99, 'FOOD');
INSERT INTO stacy_store_db.product (name, price, category)
VALUES ('Fiction Book', 12.99, 'BOOKS');
INSERT INTO stacy_store_db.product (name, price, category)
VALUES ('Toy Car', 9.99, 'TOYS');

-- Insert initial Invoices (UUIDs will be auto-generated)
INSERT INTO stacy_store_db.invoice (date, total_amount)
VALUES ('2024-09-01', 1289.97);
INSERT INTO stacy_store_db.invoice (date, total_amount)
VALUES ('2024-09-02', 529.98);
INSERT INTO stacy_store_db.invoice (date, total_amount)
VALUES ('2024-09-03', 89.99);
INSERT INTO stacy_store_db.invoice (date, total_amount)
VALUES ('2024-09-04', 350.00);
INSERT INTO stacy_store_db.invoice (date, total_amount)
VALUES ('2024-09-05', 129.99);

-- Insert Product associations to Invoices (Many-to-Many)
-- Invoice 1 includes a Laptop and Headphones
INSERT INTO stacy_store_db.invoice_product (invoice_id, product_id)
VALUES ((SELECT id FROM stacy_store_db.invoice WHERE date = '2024-09-01' LIMIT 1),
       (SELECT id FROM stacy_store_db.product WHERE name = 'Laptop' LIMIT 1));
INSERT INTO stacy_store_db.invoice_product (invoice_id, product_id)
VALUES ((SELECT id FROM stacy_store_db.invoice WHERE date = '2024-09-01' LIMIT 1),
       (SELECT id FROM stacy_store_db.product WHERE name = 'Headphones' LIMIT 1));

-- Invoice 2 includes a Smartphone and Mouse
INSERT INTO stacy_store_db.invoice_product (invoice_id, product_id)
VALUES ((SELECT id FROM stacy_store_db.invoice WHERE date = '2024-09-02' LIMIT 1),
       (SELECT id FROM stacy_store_db.product WHERE name = 'Smartphone' LIMIT 1));
INSERT INTO stacy_store_db.invoice_product (invoice_id, product_id)
VALUES ((SELECT id FROM stacy_store_db.invoice WHERE date = '2024-09-02' LIMIT 1),
       (SELECT id FROM stacy_store_db.product WHERE name = 'Mouse' LIMIT 1));

-- Invoice 3 includes only a Keyboard
INSERT INTO stacy_store_db.invoice_product (invoice_id, product_id)
VALUES ((SELECT id FROM stacy_store_db.invoice WHERE date = '2024-09-03' LIMIT 1),
       (SELECT id FROM stacy_store_db.product WHERE name = 'Keyboard' LIMIT 1));

-- Invoice 4 includes T-shirt and Jeans
INSERT INTO stacy_store_db.invoice_product (invoice_id, product_id)
VALUES ((SELECT id FROM stacy_store_db.invoice WHERE date = '2024-09-04' LIMIT 1),
       (SELECT id FROM stacy_store_db.product WHERE name = 'T-shirt' LIMIT 1));
INSERT INTO stacy_store_db.invoice_product (invoice_id, product_id)
VALUES ((SELECT id FROM stacy_store_db.invoice WHERE date = '2024-09-04' LIMIT 1),
       (SELECT id FROM stacy_store_db.product WHERE name = 'Jeans' LIMIT 1));

-- Invoice 5 includes a Pizza and Fiction Book
INSERT INTO stacy_store_db.invoice_product (invoice_id, product_id)
VALUES ((SELECT id FROM stacy_store_db.invoice WHERE date = '2024-09-05' LIMIT 1),
       (SELECT id FROM stacy_store_db.product WHERE name = 'Pizza' LIMIT 1));
INSERT INTO stacy_store_db.invoice_product (invoice_id, product_id)
VALUES ((SELECT id FROM stacy_store_db.invoice WHERE date = '2024-09-05' LIMIT 1),
       (SELECT id FROM stacy_store_db.product WHERE name = 'Fiction Book' LIMIT 1));
