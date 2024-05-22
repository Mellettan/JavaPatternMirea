package ru.mirea.pract_16.service;

import org.springframework.stereotype.Service;
import ru.mirea.pract_16.model.Product;
import ru.mirea.pract_16.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService implements ProductServiceInterface {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void create(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> readAll() {
        return productRepository.findAll();
    }

    @Override
    public Product read(int id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public boolean update(Product product, int id) {
        if (productRepository.existsById(id)) {
            product.setId(id); // noqa
            productRepository.save(product);
            return  true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
