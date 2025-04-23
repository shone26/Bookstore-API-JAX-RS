/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.service;

import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.exception.OutOfStockException;
import com.bookstore.exception.OrderNotFoundException;
import com.bookstore.model.Book;
import com.bookstore.model.Cart;
import com.bookstore.model.CartItem;
import com.bookstore.model.Order;
import com.bookstore.model.OrderItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Shone
 */

public class OrderService {
    private static final Map<Long, List<Order>> customerOrders = new HashMap<>();
    private static final AtomicLong idCounter = new AtomicLong(1);
    
    private final CustomerService customerService;
    private final BookService bookService;
    private final CartService cartService;
    
    public OrderService(CustomerService customerService, BookService bookService, CartService cartService) {
        this.customerService = customerService;
        this.bookService = bookService;
        this.cartService = cartService;
    }
    
    // Create order from cart
    public Order createOrder(Long customerId) {
        // Validate customer exists
        if (!customerService.customerExists(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        
        // Get customer's cart
        Cart cart = cartService.getCart(customerId);
        
        if (cart.getItems().isEmpty()) {
            throw new InvalidInputException("Cannot create order with empty cart.");
        }
        
        // Create new order
        Order order = new Order(customerId);
        order.setId(idCounter.getAndIncrement());
        
        // Convert cart items to order items
        for (CartItem cartItem : cart.getItems()) {
            Book book = bookService.getBookById(cartItem.getBookId());
            
            // Check if book has enough stock
            if (book.getStock() < cartItem.getQuantity()) {
                throw new OutOfStockException(
                    cartItem.getBookId(), 
                    cartItem.getQuantity(), 
                    book.getStock()
                );
            }
            
            // Create order item
            OrderItem orderItem = new OrderItem(
                book.getId(),
                book.getTitle(),
                book.getPrice(),
                cartItem.getQuantity()
            );
            
            order.addItem(orderItem);
            
            // Reduce book stock
            bookService.reduceStock(book.getId(), cartItem.getQuantity());
        }
        
        // Store order
        if (!customerOrders.containsKey(customerId)) {
            customerOrders.put(customerId, new ArrayList<>());
        }
        customerOrders.get(customerId).add(order);
        
        // Clear cart after order is created
        cartService.clearCart(customerId);
        
        return order;
    }
    
    // Get all orders for a customer
    public List<Order> getCustomerOrders(Long customerId) {
        // Validate customer exists
        if (!customerService.customerExists(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        
        return customerOrders.getOrDefault(customerId, new ArrayList<>());
    }
    
    // Get specific order for a customer
    public Order getOrder(Long customerId, Long orderId) {
        // Validate customer exists
        if (!customerService.customerExists(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        
        List<Order> orders = customerOrders.getOrDefault(customerId, new ArrayList<>());
        
        return orders.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}