-- Create the uuid-ossp extension if it doesn't exist
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create `product` table in the correct schema
CREATE TABLE stacy_store_db.product
(
    id       UUID DEFAULT gen_random_uuid() PRIMARY KEY,  -- Use gen_random_uuid() to auto-generate UUIDs
    name     VARCHAR(255) NOT NULL,
    price    DECIMAL(10, 2) NOT NULL,
    category VARCHAR(255) NOT NULL
);

-- Create `invoice` table in the correct schema
CREATE TABLE stacy_store_db.invoice
(
    id           UUID DEFAULT gen_random_uuid() PRIMARY KEY,  -- Use gen_random_uuid() to auto-generate UUIDs
    date         DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL
);

-- Create `invoice_product` join table in the correct schema
CREATE TABLE stacy_store_db.invoice_product
(
    invoice_id UUID,
    product_id UUID,
    FOREIGN KEY (invoice_id) REFERENCES stacy_store_db.invoice (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES stacy_store_db.product (id) ON DELETE CASCADE,
    PRIMARY KEY (invoice_id, product_id)
);
