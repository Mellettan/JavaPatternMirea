package ru.mirea.pract_17;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.pract_17.model.Product;
import ru.mirea.pract_17.service.ProductService;

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

    @GetMapping("/products/filter")
    public @ResponseBody ResponseEntity filterProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.filterProducts(name, minPrice, maxPrice));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
