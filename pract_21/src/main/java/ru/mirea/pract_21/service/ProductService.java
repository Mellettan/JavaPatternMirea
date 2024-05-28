package ru.mirea.pract_21.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.pract_21.model.Product;
import ru.mirea.pract_21.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductService implements ProductServiceInterface {
    private final ProductRepository productRepository;
    private final EntityManager entityManager;

    public ProductService(ProductRepository productRepository, EntityManager entityManager) {
        this.productRepository = productRepository;
        this.entityManager = entityManager;
    }

    public List<Product> filterProducts(String name, Double minPrice, Double maxPrice) {
        log.info("Filtering products: name={}, minPrice={}, maxPrice={}", name, minPrice, maxPrice);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            predicates.add(cb.like(product.get("name"), "%" + name + "%"));
        }
        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("price"), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(product.get("price"), maxPrice));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }


    @Override
    @Transactional
    public void create(Product product) {
        log.info("Creating product: {}", product);
        productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> readAll() {
        log.info("Reading all products");
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product read(int id) {
        log.info("Reading product: id={}", id);
        return productRepository.getReferenceById(id);
    }

    @Override
    @Transactional
    public boolean update(Product product, int id) {
        if (productRepository.existsById(id)) {
            log.info("Updating product: id={}, product={}", id, product);
            product.setId(id); // noqa
            productRepository.save(product);
            return  true;
        }
        log.info("Product with id={} not found", id);
        return false;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        if (productRepository.existsById(id)) {
            log.info("Deleting product: id={}", id);
            productRepository.deleteById(id);
            return true;
        }
        log.info("Product with id={} not found", id);
        return false;
    }
}
