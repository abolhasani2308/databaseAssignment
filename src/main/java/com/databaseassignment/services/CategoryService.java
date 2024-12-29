package com.databaseassignment.services;

import com.databaseassignment.entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CategoryService {
    private SessionFactory sessionFactory;

    public CategoryService() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public void createCategory(String name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Category category = new Category();
            category.setName(name);
            session.save(category);
            session.getTransaction().commit();
        }
    }

    public List<Category> getAllCategories() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Category", Category.class).list();
        }
    }

    public Category getCategoryById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Category.class, id);
        }
    }

    public void updateCategory(Long id, String name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Category category = session.get(Category.class, id);
            if (category != null) {
                category.setName(name);
                session.update(category);
                session.getTransaction().commit();
            } else {
                System.out.println("Category not found!");
            }
        }
    }

    public void deleteCategory(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Category category = session.get(Category.class, id);
            if (category != null) {
                session.delete(category);
                session.getTransaction().commit();
            } else {
                System.out.println("Category not found!");
            }
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
