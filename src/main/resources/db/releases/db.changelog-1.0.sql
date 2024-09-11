-- ChangeSet 1: Create `product` table
CREATE TABLE product (
     id UUID  PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     price DECIMAL(10,2) NOT NULL
);

-- ChangeSet 2: Create `invoice` table
CREATE TABLE invoice (
     id UUID  PRIMARY KEY,
     date DATE NOT NULL,
     total_amount DECIMAL(10,2) NOT NULL
);

-- ChangeSet 3: Create join table for `invoice` and `product` many-to-many relationship
CREATE TABLE invoice_product (
     invoice_id UUID,
     product_id UUID,
     FOREIGN KEY (invoice_id) REFERENCES invoice(id),
     FOREIGN KEY (product_id) REFERENCES product(id),
     PRIMARY KEY (invoice_id, product_id)
);