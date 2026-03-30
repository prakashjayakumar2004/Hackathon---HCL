package com.example.product_service.Controller;


import com.example.product_service.Entity.Product;
import com.example.product_service.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // ADD PRODUCT
    @PostMapping
    public Product add(@RequestBody Product product) {
        return service.addProduct(product);
    }

    // GET ALL PRODUCTS
    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return service.getProduct(id);
    }

    // UPDATE PRODUCT
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return service.updateProduct(id, product);
    }

    // DELETE PRODUCT
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.deleteProduct(id);
    }
}