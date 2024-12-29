package com.databaseassignment.services;

import com.databaseassignment.entities.Product;
import com.databaseassignment.entities.Category;
import com.databaseassignment.repositories.ProductRepository;
import com.databaseassignment.repositories.CategoryRepository;

import java.util.List;

public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
        this.categoryRepository = new CategoryRepository();
    }

    public void createProduct(String name, double price, Long categoryId) {
        Category category = categoryRepository.findById(Category.class, categoryId);
        if (category != null) {
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setCategory(category);
            productRepository.save(product);
        } else {
            System.out.println("Category not found!");
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll("from Product", Product.class);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(Product.class, id);
    }

    public void updateProduct(Long id, String name, double price) {
        Product product = productRepository.findById(Product.class, id);
        if (product != null) {
            product.setName(name);
            product.setPrice(price);
            productRepository.update(product);
        } else {
            System.out.println("Product not found!");
        }
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(Product.class, id);
        if (product != null) {
            productRepository.delete(product);
        } else {
            System.out.println("Product not found!");
        }
    }

    public void close() {
        productRepository.close();
    }
}
