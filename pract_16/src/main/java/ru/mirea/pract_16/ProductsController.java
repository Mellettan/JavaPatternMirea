package ru.mirea.pract_16;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.pract_16.model.Product;
import ru.mirea.pract_16.service.ProductService;

@Controller
public class ProductsController {
    @Autowired
    private ProductService productService;

    @GetMapping(path = "/products")
    public @ResponseBody ResponseEntity getProducts() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.readAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(path = "/products")
    public @ResponseBody ResponseEntity createProduct(Product product) {
        try {
            productService.create(product);;
            return ResponseEntity.status(HttpStatus.OK).body("Product was created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/products")
    public @ResponseBody ResponseEntity deleteProduct(int id) {
        try {
            if (productService.delete(id)) {
                return ResponseEntity.status(HttpStatus.OK).body("Product was deleted");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during deleting product");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
