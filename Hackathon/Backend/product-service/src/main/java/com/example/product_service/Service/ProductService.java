package com.example.product_service.Service;


import com.example.product_service.Entity.Product;
import com.example.product_service.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    // ADD PRODUCT
    public Product addProduct(Product product) {
        return repository.save(product);
    }

    // GET ALL PRODUCTS
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // GET PRODUCT BY ID
    public Product getProduct(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // UPDATE PRODUCT
    public Product updateProduct(Long id, Product product) {

        Product existing = getProduct(id);

        existing.setName(product.getName());
        existing.setCategory(product.getCategory());
        existing.setBrand(product.getBrand());
        existing.setPackaging(product.getPackaging());
        existing.setPrice(product.getPrice());

        return repository.save(existing);
    }

    // DELETE PRODUCT
    public String deleteProduct(Long id) {
        repository.deleteById(id);
        return "Product deleted successfully";
    }
}
