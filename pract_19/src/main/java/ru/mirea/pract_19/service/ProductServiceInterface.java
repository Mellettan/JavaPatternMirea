package ru.mirea.pract_19.service;

import ru.mirea.pract_19.model.Product;

import java.util.List;

public interface ProductServiceInterface {
    void create(Product product);
    List<Product> readAll();
    Product read(int id);
    boolean update(Product product, int id);
    boolean delete(int id);
}
