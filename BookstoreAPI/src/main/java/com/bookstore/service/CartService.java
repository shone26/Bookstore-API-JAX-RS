/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.service;

import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.exception.OutOfStockException;
import com.bookstore.model.Book;
import com.bookstore.model.Cart;
import com.bookstore.model.CartItem;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Shone
 */


public class CartService {
    private static final Map<Long, Cart> carts = new HashMap<>();
    private final CustomerService customerService;
    private final BookService bookService;
    
    public CartService(CustomerService customerService, BookService bookService) {
        this.customerService = customerService;
        this.bookService = bookService;
    }
    
    // Get or create cart for customer
    public Cart getCart(Long customerId) {
        // Validate customer exists
        if (!customerService.customerExists(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        
        // Create cart if it doesn't exist
        if (!carts.containsKey(customerId)) {
            Cart cart = new Cart(customerId);
            carts.put(customerId, cart);
        }
        
        return carts.get(customerId);
    }
    
    // Add item to cart
    public Cart addItem(Long customerId, CartItem item) {
        validateCartItem(item);
        
        // Check if book exists and has enough stock
        Book book = bookService.getBookById(item.getBookId());
        if (book.getStock() < item.getQuantity()) {
            throw new OutOfStockException(item.getBookId(), item.getQuantity(), book.getStock());
        }
        
        Cart cart = getCart(customerId);
        
        // Check if item already exists in cart
        CartItem existingItem = cart.findItem(item.getBookId());
        if (existingItem != null) {
            // Update quantity
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            // Add new item
            cart.addItem(item);
        }
        
        return cart;
    }
    
    // Update item quantity
    public Cart updateItemQuantity(Long customerId, Long bookId, Integer quantity) {
        validateQuantity(quantity);
        
        Cart cart = getCart(customerId);
        CartItem item = cart.findItem(bookId);
        
        if (item == null) {
            throw new InvalidInputException("Book is not in the cart.");
        }
        
        // Check if book has enough stock
        Book book = bookService.getBookById(bookId);
        if (book.getStock() < quantity) {
            throw new OutOfStockException(bookId, quantity, book.getStock());
        }
        
        item.setQuantity(quantity);
        return cart;
    }
    
    // Remove item from cart
    public Cart removeItem(Long customerId, Long bookId) {
        Cart cart = getCart(customerId);
        
        if (cart.findItem(bookId) == null) {
            throw new InvalidInputException("Book is not in the cart.");
        }
        
        cart.removeItem(bookId);
        return cart;
    }
    
    // Clear cart
    public void clearCart(Long customerId) {
        Cart cart = getCart(customerId);
        cart.getItems().clear();
    }
    
    // Helper method to validate cart item
    private void validateCartItem(CartItem item) {
        if (item.getBookId() == null) {
            throw new InvalidInputException("Book ID cannot be null.");
        }
        
        validateQuantity(item.getQuantity());
    }
    
    // Helper method to validate quantity
    private void validateQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new InvalidInputException("Quantity must be greater than zero.");
        }
    }
}