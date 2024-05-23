package ru.mirea.pract_18.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.pract_17.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
