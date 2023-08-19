-- Version: 1
-- Description: Create order table

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    date_issue TIMESTAMP NOT NULL,
    description VARCHAR(255),
    client_id BIGINT REFERENCES client(id),
    amount NUMERIC(12, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS order_item (
    id SERIAL PRIMARY KEY,
    order_id BIGINT REFERENCES orders(id),
    product_id BIGINT REFERENCES product(id),
    quantity INT NOT NULL,
    amount NUMERIC(12, 2) NOT NULL,
    constraint fk_product_order_id_product foreign key (product_id) references product (id),
    constraint fk_product_order_id_order foreign key (order_id) references orders (id)
);
)