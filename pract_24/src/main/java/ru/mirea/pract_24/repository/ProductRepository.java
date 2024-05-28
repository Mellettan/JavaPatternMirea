package ru.mirea.pract_24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.pract_24.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
