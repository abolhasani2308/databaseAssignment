package com.databaseassignment.services;

import com.databaseassignment.entities.Product;
import com.databaseassignment.entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductService {
    private SessionFactory sessionFactory;

    public ProductService() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public void createProduct(String name, double price, Long categoryId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Category category = session.get(Category.class, categoryId);
            if (category != null) {
                Product product = new Product();
                product.setName(name);
                product.setPrice(price);
                product.setCategory(category);
                session.save(product);
                session.getTransaction().commit();
            } else {
                System.out.println("Category not found!");
            }
        }
    }

    public List<Product> getAllProducts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Product", Product.class).list();
        }
    }

    public Product getProductById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, id);
        }
    }

    public void updateProduct(Long id, String name, double price) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                product.setName(name);
                product.setPrice(price);
                session.update(product);
                session.getTransaction().commit();
            } else {
                System.out.println("Product not found!");
            }
        }
    }

    public void deleteProduct(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
                session.getTransaction().commit();
            } else {
                System.out.println("Product not found!");
            }
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
