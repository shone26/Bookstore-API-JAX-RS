/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.exception;

/**
 *
 * @author Shone
 */
public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String message) {
        super(message);
    }
    
    public OutOfStockException(Long bookId, Integer requested, Integer available) {
        super("Book with ID " + bookId + " has insufficient stock. Requested: " + 
              requested + ", Available: " + available);
    }
}