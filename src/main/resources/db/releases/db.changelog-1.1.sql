-- Insert initial Products
INSERT INTO product (id, name, price) VALUES ('123e4567-e89b-12d3-a456-426614174000', 'Laptop', 999.99);
INSERT INTO product (id, name, price) VALUES ('123e4567-e89b-12d3-a456-426614174001', 'Smartphone', 499.99);
INSERT INTO product (id, name, price) VALUES ('123e4567-e89b-12d3-a456-426614174002', 'Headphones', 199.99);
INSERT INTO product (id, name, price) VALUES ('123e4567-e89b-12d3-a456-426614174003', 'Keyboard', 89.99);
INSERT INTO product (id, name, price) VALUES ('123e4567-e89b-12d3-a456-426614174004', 'Mouse', 29.99);

-- Insert initial Invoices
INSERT INTO invoice (id, date, total_amount) VALUES ('123e4567-e89b-12d3-a456-426614174010', '2024-09-01', 1289.97);
INSERT INTO invoice (id, date, total_amount) VALUES ('123e4567-e89b-12d3-a456-426614174011', '2024-09-02', 529.98);
INSERT INTO invoice (id, date, total_amount) VALUES ('123e4567-e89b-12d3-a456-426614174012', '2024-09-03', 89.99);

-- Insert Product associations to Invoices (Many-to-Many)
-- Invoice 1 includes a Laptop and Headphones
INSERT INTO invoice_product (invoice_id, product_id) VALUES ('123e4567-e89b-12d3-a456-426614174010', '123e4567-e89b-12d3-a456-426614174000');
INSERT INTO invoice_product (invoice_id, product_id) VALUES ('123e4567-e89b-12d3-a456-426614174010', '123e4567-e89b-12d3-a456-426614174002');

-- Invoice 2 includes a Smartphone and Mouse
INSERT INTO invoice_product (invoice_id, product_id) VALUES ('123e4567-e89b-12d3-a456-426614174011', '123e4567-e89b-12d3-a456-426614174001');
INSERT INTO invoice_product (invoice_id, product_id) VALUES ('123e4567-e89b-12d3-a456-426614174011', '123e4567-e89b-12d3-a456-426614174004');

-- Invoice 3 includes only a Keyboard
INSERT INTO invoice_product (invoice_id, product_id) VALUES ('123e4567-e89b-12d3-a456-426614174012', '123e4567-e89b-12d3-a456-426614174003');
