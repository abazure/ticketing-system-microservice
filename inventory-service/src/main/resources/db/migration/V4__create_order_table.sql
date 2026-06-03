CREATE TABLE orders
(
    id          BIGSERIAL PRIMARY KEY,
    total_price BIGINT NOT NULL,
    quantity    BIGINT NOT NULL,
    placed_at   TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    customer_id BIGINT NOT NULL,
    event_id    BIGINT NOT NULL,

    CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES customers (id),
    CONSTRAINT fk_order_event FOREIGN KEY (event_id) REFERENCES events (id)
)