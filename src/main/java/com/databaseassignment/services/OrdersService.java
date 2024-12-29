package com.databaseassignment.services;

import com.databaseassignment.entities.Orders;
import com.databaseassignment.entities.Customer;
import com.databaseassignment.repositories.OrdersRepository;
import com.databaseassignment.repositories.CustomerRepository;

import java.util.List;

public class OrdersService {
    private OrdersRepository ordersRepository;
    private CustomerRepository customerRepository;

    public OrdersService() {
        this.ordersRepository = new OrdersRepository();
        this.customerRepository = new CustomerRepository();
    }

    public void createOrder(String ordersDate, Long customerId, double totalAmount) {
        Customer customer = customerRepository.findById(Customer.class, customerId);
        if (customer != null) {
            Orders order = new Orders();
            order.setOrdersDate(ordersDate);
            order.setCustomer(customer);
            order.setTotalAmount(totalAmount);
            ordersRepository.save(order);
        } else {
            System.out.println("Customer not found!");
        }
    }

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll("from Orders", Orders.class);
    }

    public Orders getOrderById(Long id) {
        return ordersRepository.findById(Orders.class, id);
    }

    public void updateOrder(Long id, String ordersDate, double totalAmount) {
        Orders order = ordersRepository.findById(Orders.class, id);
        if (order != null) {
            order.setOrdersDate(ordersDate);
            order.setTotalAmount(totalAmount);
            ordersRepository.update(order);
        } else {
            System.out.println("Order not found!");
        }
    }

    public void deleteOrder(Long id) {
        Orders order = ordersRepository.findById(Orders.class, id);
        if (order != null) {
            ordersRepository.delete(order);
        } else {
            System.out.println("Order not found!");
        }
    }

    public void close() {
        ordersRepository.close();
    }
}
