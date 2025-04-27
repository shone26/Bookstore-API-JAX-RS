/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.service;

import java.util.regex.Pattern;
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
 * @author Umesh
 */


public class CustomerService {
    private static final Map<Long, Customer> customers = new HashMap<>();
    private static final AtomicLong idCounter = new AtomicLong(1);
    
    // Add this regex pattern as a constant in your CustomerService class
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    
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
        if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty()) {
            throw new InvalidInputException("Customer first name cannot be empty.");
        }
        
        if (customer.getLastName() == null || customer.getLastName().trim().isEmpty()) {
            throw new InvalidInputException("Customer last name cannot be empty.");
        }
        
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("Email cannot be empty.");
        }
        
        if (customer.getPassword() == null || customer.getPassword().trim().isEmpty()) {
            throw new InvalidInputException("Password cannot be empty.");
        }
        
        // Check if customer with the same email already exists
        if (customerWithEmailExists(customer.getEmail())) {
            throw new InvalidInputException("Customer with email '" + customer.getEmail() + "' already exists.");
        }
        
        // Validate email format
        if (!isValidEmail(customer.getEmail())) {
            throw new InvalidInputException("Invalid email format. Please provide a valid email address.");
        }
    }
    
    // Method to check if a customer with the same email already exists
    private boolean customerWithEmailExists(String email) {
        return customers.values().stream()
                .anyMatch(customer -> customer.getEmail().equalsIgnoreCase(email));
    }
    
    // Method to validate email format
    private boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
}