//package ru.mirea.pract_24;
//
//import jakarta.persistence.EntityManager;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import ru.mirea.pract_24.model.Product;
//import ru.mirea.pract_24.repository.MarketRepository;
//import ru.mirea.pract_24.repository.ProductRepository;
//import ru.mirea.pract_24.service.ProductService;
//import ru.mirea.pract_24.service.ProductServiceInterface;
//
//import java.util.List;
//
//@ExtendWith(MockitoExtension.class)
//public class ProductServiceTest {
//	@Mock
//	private ProductRepository productRepository;
//	private final EntityManager entityManager;
//
//	public ProductServiceTest(EntityManager entityManager) {
//		this.entityManager = entityManager;
//	}
//	@Captor
//	ArgumentCaptor<Product> captor;
//
//	@Test
//	void getProducts() {
//		Product product1 = new Product();
//		product1.setName("Water");
//		product1.setPrice(100);
//
//		Product product2 = new Product();
//		product1.setName("Apple");
//		product1.setPrice(200);
//
//		Mockito.when(productRepository.findAll()).thenReturn(List.of(product1, product2));
//		ProductServiceInterface productService = new ProductService(productRepository, entityManager);
//		Assertions.assertEquals(2, productService.readAll().size());
//		Assertions.assertEquals("Apple", productService.readAll().get(1).getName());
//	}
//
//	@Test
//	void saveProduct() {
//		Product product = new Product();
//		product.setName("Chocolate");
//		product.setPrice(300);
//		ProductService productService = new ProductService(productRepository, entityManager);
//		productService.create(product);
//		Mockito.verify(productRepository).save(captor.capture());
//		Product captured = captor.getValue();
//		Assertions.assertEquals("Chocolate", captured.getName());
//	}
//}
//
package ru.mirea.pract_24;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.mirea.pract_24.model.Product;
import ru.mirea.pract_24.repository.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import ru.mirea.pract_24.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@Mock
	private EntityManager entityManager;

	@InjectMocks
	private ProductService productService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateProduct() {
		Product product = new Product();
		product.setName("Test Product");

		productService.create(product);

		verify(productRepository, times(1)).save(product);
	}

	@Test
	public void testReadAllProducts() {
		List<Product> products = new ArrayList<>();
		products.add(new Product());
		when(productRepository.findAll()).thenReturn(products);

		List<Product> result = productService.readAll();

		assertEquals(1, result.size());
		verify(productRepository, times(1)).findAll();
	}

	@Test
	public void testReadProductById() {
		Product product = new Product();
		when(productRepository.getReferenceById(anyInt())).thenReturn(product);

		Product result = productService.read(1);

		assertNotNull(result);
		verify(productRepository, times(1)).getReferenceById(1);
	}

	@Test
	public void testUpdateProduct() {
		Product product = new Product();
		when(productRepository.existsById(anyInt())).thenReturn(true);

		boolean result = productService.update(product, 1);

		assertTrue(result);
		verify(productRepository, times(1)).save(product);
	}

	@Test
	public void testUpdateProductNotFound() {
		Product product = new Product();
		when(productRepository.existsById(anyInt())).thenReturn(false);

		boolean result = productService.update(product, 1);

		assertFalse(result);
		verify(productRepository, times(0)).save(product);
	}

	@Test
	public void testDeleteProduct() {
		when(productRepository.existsById(anyInt())).thenReturn(true);

		boolean result = productService.delete(1);

		assertTrue(result);
		verify(productRepository, times(1)).deleteById(1);
	}

	@Test
	public void testDeleteProductNotFound() {
		when(productRepository.existsById(anyInt())).thenReturn(false);

		boolean result = productService.delete(1);

		assertFalse(result);
		verify(productRepository, times(0)).deleteById(1);
	}

	@Test
	public void testFilterProducts() {
		CriteriaBuilder cb = mock(CriteriaBuilder.class);
		CriteriaQuery<Product> query = mock(CriteriaQuery.class);
		Root<Product> root = mock(Root.class);
		TypedQuery<Product> typedQuery = mock(TypedQuery.class);

		when(entityManager.getCriteriaBuilder()).thenReturn(cb);
		when(cb.createQuery(Product.class)).thenReturn(query);
		when(query.from(Product.class)).thenReturn(root);
		when(entityManager.createQuery(query)).thenReturn(typedQuery);
		when(typedQuery.getResultList()).thenReturn(new ArrayList<>());

		List<Product> result = productService.filterProducts("Test", 10.0, 100.0);

		assertNotNull(result);
		verify(entityManager, times(1)).createQuery(query);
		verify(typedQuery, times(1)).getResultList();
	}
}
