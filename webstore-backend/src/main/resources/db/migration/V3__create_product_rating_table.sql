CREATE TABLE product_review
(
    id         BIGSERIAL PRIMARY KEY,
    rating     INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment    TEXT,
    product_id BIGINT  NOT NULL,
    CONSTRAINT fk_product_review_product
        FOREIGN KEY (product_id) REFERENCES product (id)
            ON DELETE CASCADE
);
