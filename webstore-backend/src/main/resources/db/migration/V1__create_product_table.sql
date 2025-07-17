CREATE TABLE IF NOT EXISTS product
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR(100)     NOT NULL,
    description    TEXT,
    price          NUMERIC(10, 2)   NOT NULL,
    in_stock       BOOLEAN                   DEFAULT true,
    average_rating DOUBLE PRECISION NOT NULL DEFAULT 0.0

);
