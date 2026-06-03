CREATE TABLE customers
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(50)  NOT NULL,
    email   VARCHAR(50) UNIQUE,
    address VARCHAR(100) NOT NULL
)