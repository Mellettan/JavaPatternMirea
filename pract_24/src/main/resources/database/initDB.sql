-- Создание последовательностей для генерации идентификаторов
CREATE SEQUENCE products_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE markets_id_seq START WITH 1 INCREMENT BY 1;

-- Создание таблицы продуктов
CREATE TABLE products (
    id INT PRIMARY KEY DEFAULT NEXTVAL('products_id_seq'),
    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL
);

-- Создание таблицы рынков
CREATE TABLE markets (
    id INT PRIMARY KEY DEFAULT NEXTVAL('markets_id_seq'),
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    product_id INT NOT NULL,
    CONSTRAINT fk_product
        FOREIGN KEY (product_id)
        REFERENCES products (id)
);

-- Вставка данных в таблицу продуктов
INSERT INTO products (name, price) VALUES ('Product A', 10.99);
INSERT INTO products (name, price) VALUES ('Product B', 15.49);

-- Вставка данных в таблицу рынков
INSERT INTO markets (name, address, product_id) VALUES ('Market 1', '123 Market St', 1);
INSERT INTO markets (name, address, product_id) VALUES ('Market 2', '456 Market Ave', 1);
INSERT INTO markets (name, address, product_id) VALUES ('Market 3', '789 Market Blvd', 2);
