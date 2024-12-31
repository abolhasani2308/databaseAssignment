package com.databaseassignment;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.databaseassignment.entities.Category;
import com.databaseassignment.entities.Product;
import com.databaseassignment.entities.Customer;
import com.databaseassignment.entities.Orders;
import com.databaseassignment.services.CustomerService;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

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
            phone.setName("iphone 16");
            phone.setPrice(90000000.0);
            phone.setCategory(electronics);
            session.save(phone);

            Customer customer = new Customer();
            customer.setName("amir");
            customer.setEmail("amir@gmail.com");
            session.save(customer);

            Orders orders = new Orders();
            orders.setCustomer(customer);
            orders.setTotalAmount(699.99);
            session.save(orders);

            session.getTransaction().commit();
            logger.info(GREEN + "Data saved successfully!" + RESET);

            session = factory.getCurrentSession();
            session.beginTransaction();

            String hql = "FROM Product";
            List<Product> products = session.createQuery(hql, Product.class).getResultList();
            logger.info(GREEN + "products size" + products.size() + RESET);

            for (Product product : products) {
                logger.info(GREEN + product.getName() + ": " + product.getPrice() + RESET);
            }

            session.getTransaction().commit();

            CustomerService customerService = new CustomerService();

            customerService.createCustomer("Amir Mohammad", "amirmohammad@gmail.com");
    
            customerService.getAllCustomers().forEach(customerItem -> 
                logger.info(GREEN + customerItem.getName() + " " + customerItem.getEmail() + RESET)
            );
    
            customerService.updateCustomer(1L, "Amir Mohammad Abolhasani", "amirmohammad@gmail.com");
    
            customerService.deleteCustomer(1L);
    
            customerService.close();

        } finally {
            factory.close();
        }
    }
}