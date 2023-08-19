-- Version: 1
-- Description: Create product table

CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    unit VARCHAR(50) NOT NULL,
    price NUMERIC(12, 2) NOT NULL
);