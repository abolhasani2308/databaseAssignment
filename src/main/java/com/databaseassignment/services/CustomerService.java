package com.databaseassignment.services;

import com.databaseassignment.entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CustomerService {
    private final SessionFactory sessionFactory;

    public CustomerService() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    // Create a new Customer
    public void createCustomer(String name, String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Customer customer = new Customer();
            customer.setName(name);
            customer.setEmail(email);
            session.save(customer);

            transaction.commit();
            System.out.println("Customer created successfully!");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Read all Customers
    public List<Customer> getAllCustomers() {
        Session session = sessionFactory.openSession();

        try {
            return session.createQuery("from Customer", Customer.class).list();
        } finally {
            session.close();
        }
    }

    // Update a Customer
    public void updateCustomer(Long id, String name, String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Customer customer = session.get(Customer.class, id);
            if (customer != null) {
                customer.setName(name);
                customer.setEmail(email);
                session.update(customer);
                transaction.commit();
                System.out.println("Customer updated successfully!");
            } else {
                System.out.println("Customer not found!");
                transaction.rollback();
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Delete a Customer
    public void deleteCustomer(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Customer customer = session.get(Customer.class, id);
            if (customer != null) {
                session.delete(customer);
                transaction.commit();
                System.out.println("Customer deleted successfully!");
            } else {
                System.out.println("Customer not found!");
                transaction.rollback();
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
