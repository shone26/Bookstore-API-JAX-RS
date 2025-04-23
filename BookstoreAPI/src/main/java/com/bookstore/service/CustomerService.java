/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.service;

import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Shone
 */


public class CustomerService {
    private static final Map<Long, Customer> customers = new HashMap<>();
    private static final AtomicLong idCounter = new AtomicLong(1);
    
    // Create a customer
    public Customer createCustomer(Customer customer) {
        validateCustomer(customer);
        
        customer.setId(idCounter.getAndIncrement());
                customers.put(customer.getId(), customer);
        return customer;
    }
    
    // Get all customers
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }
    
    // Get customer by ID
    public Customer getCustomerById(Long id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }
        return customer;
    }
    
    // Update customer
    public Customer updateCustomer(Long id, Customer customer) {
        if (!customers.containsKey(id)) {
            throw new CustomerNotFoundException(id);
        }
        
        validateCustomer(customer);
        
        customer.setId(id);
        customers.put(id, customer);
        return customer;
    }
    
    // Delete customer
    public void deleteCustomer(Long id) {
        if (!customers.containsKey(id)) {
            throw new CustomerNotFoundException(id);
        }
        customers.remove(id);
    }
    
    // Check if customer exists
    public boolean customerExists(Long id) {
        return customers.containsKey(id);
    }
    
    // Helper method to validate customer data
    private void validateCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new InvalidInputException("Customer name cannot be empty.");
        }
        
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("Email cannot be empty.");
        }
        
        if (customer.getPassword() == null || customer.getPassword().trim().isEmpty()) {
            throw new InvalidInputException("Password cannot be empty.");
        }
    }
}
