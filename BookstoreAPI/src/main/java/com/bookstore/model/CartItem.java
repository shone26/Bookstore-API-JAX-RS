/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.model;

/**
 *
 * @author Umesh 
 */

public class CartItem {
    private Long bookId;
    private Integer quantity;
    
    // Constructors
    public CartItem() {}
    
    public CartItem(Long bookId, Integer quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }
    
    // Getters and Setters
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}