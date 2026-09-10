-- Inventory schema. The data dictionary in docs/data/ describes these columns.
CREATE TABLE IF NOT EXISTS categories (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    code        VARCHAR(40)  NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS items (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    sku              VARCHAR(120)  NOT NULL UNIQUE,
    name             VARCHAR(255)  NOT NULL,
    unit_price       DECIMAL(12,2) NOT NULL,
    quantity_on_hand INT           NOT NULL DEFAULT 0,
    category_id      BIGINT,
    CONSTRAINT fk_items_category FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE INDEX IF NOT EXISTS idx_items_category ON items (category_id);
CREATE INDEX IF NOT EXISTS idx_items_quantity ON items (quantity_on_hand);
