package com.databaseassignment.services;

import com.databaseassignment.entities.Orders;
import com.databaseassignment.entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class OrdersService {
    private SessionFactory sessionFactory;

    public OrdersService() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public void createOrder(String ordersDate, Long customerId, double totalAmount) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, customerId);
            if (customer != null) {
                Orders order = new Orders();
                order.setOrdersDate(ordersDate);
                order.setCustomer(customer);
                order.setTotalAmount(totalAmount);
                session.save(order);
                session.getTransaction().commit();
            } else {
                System.out.println("Customer not found!");
            }
        }
    }

    public List<Orders> getAllOrders() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Orders", Orders.class).list();
        }
    }

    public Orders getOrderById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Orders.class, id);
        }
    }

    public void updateOrder(Long id, String ordersDate, double totalAmount) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Orders order = session.get(Orders.class, id);
            if (order != null) {
                order.setOrdersDate(ordersDate);
                order.setTotalAmount(totalAmount);
                session.update(order);
                session.getTransaction().commit();
            } else {
                System.out.println("Order not found!");
            }
        }
    }

    public void deleteOrder(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Orders order = session.get(Orders.class, id);
            if (order != null) {
                session.delete(order);
                session.getTransaction().commit();
            } else {
                System.out.println("Order not found!");
            }
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
