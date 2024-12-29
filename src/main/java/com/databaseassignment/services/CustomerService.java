package com.databaseassignment.services;

import com.databaseassignment.entities.Customer;
import com.databaseassignment.repositories.CustomerRepository;

import java.util.List;

public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }

    public void createCustomer(String name, String email) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll("from Customer", Customer.class);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(Customer.class, id);
    }

    public void updateCustomer(Long id, String name, String email) {
        Customer customer = customerRepository.findById(Customer.class, id);
        if (customer != null) {
            customer.setName(name);
            customer.setEmail(email);
            customerRepository.update(customer);
        } else {
            System.out.println("Customer not found!");
        }
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(Customer.class, id);
        if (customer != null) {
            customerRepository.delete(customer);
        } else {
            System.out.println("Customer not found!");
        }
    }

    public void close() {
        customerRepository.close();
    }
}
