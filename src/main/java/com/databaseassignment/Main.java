package com.databaseassignment;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import com.databaseassignment.entities.Category;
import com.databaseassignment.entities.Product;
import com.databaseassignment.entities.Customer;
import com.databaseassignment.entities.Orders;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Orders.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Category electronics = new Category();
            electronics.setName("Electronics");
            session.save(electronics);

            Product phone = new Product();
            phone.setName("Smartphone");
            phone.setPrice(699.99);
            phone.setCategory(electronics);
            session.save(phone);

            Customer customer = new Customer();
            customer.setName("John Doe");
            customer.setEmail("john.doe@example.com");
            session.save(customer);

            Orders orders = new Orders();
            orders.setCustomer(customer);
            orders.setTotalAmount(699.99);
            session.save(orders);

            session.getTransaction().commit();
            System.out.println("Data saved successfully!");

            session = factory.getCurrentSession();
            session.beginTransaction();

            String hql = "FROM Product";
            List<Product> products = session.createQuery(hql, Product.class).getResultList();
            System.out.println("products size" + products.size());

            for (Product product : products) {
                System.out.println(product.getName() + ": " + product.getPrice());
            }

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
