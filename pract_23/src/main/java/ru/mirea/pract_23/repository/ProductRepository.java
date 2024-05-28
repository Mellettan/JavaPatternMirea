package ru.mirea.pract_23.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.pract_23.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
