/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.model;

/**
 *
 * @author Umesh 
 */
public class OrderItem {
    private Long bookId;
    private String bookTitle;
    private Double price;
    private Integer quantity;
    
    // Constructors
    public OrderItem() {}
    
    public OrderItem(Long bookId, String bookTitle, Double price, Integer quantity) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.price = price;
        this.quantity = quantity;
    }
    
    // Getters and Setters
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    // Helper method
    public Double getTotal() {
        return price * quantity;
    }
}