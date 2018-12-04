CREATE SEQUENCE product_id_seq;
CREATE TABLE IF NOT EXISTS product
(
    id INTEGER NOT NULL DEFAULT nextval('product_id_seq'),
    name VARCHAR(255) NOT NULL,
    price INTEGER NOT NULL, -- in cents
    quantity INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT id_product_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE customer_order_id_seq;
CREATE TABLE IF NOT EXISTS customer_order
(
    id INTEGER NOT NULL DEFAULT nextval('customer_order_id_seq'),
    email VARCHAR(255) NOT NULL,
    total_order_value INTEGER NOT NULL, -- in cents
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT id_customer_order_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS customer_order_product
(
    product_id INTEGER NOT NULL,
    customer_order_id INTEGER NOT NULL,
    CONSTRAINT customer_order_product_fk_1 FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT customer_order_product_fk_2 FOREIGN KEY (customer_order_id) REFERENCES customer_order (id),
    CONSTRAINT id_customer_order_product_pkey PRIMARY KEY (product_id,customer_order_id)
);