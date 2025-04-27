/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.model;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Umesh 
 */



/**
 *
 * @author Umesh 
 */


/**
 *
 * @author Umesh 
 */
public class Order {
    private Long id;
    private Long customerId;
    private String orderDate;  // Changed back to String
    private List<OrderItem> items;
    private Double total;
    
    // Constructors
    public Order() {
        this.items = new ArrayList<>();
        this.orderDate = formatCurrentTimestamp();
    }
    
    public Order(Long customerId) {
        this.customerId = customerId;
        this.items = new ArrayList<>();
        this.orderDate = formatCurrentTimestamp();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    
    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
    
    // Add method to set date using Timestamp
    public void setOrderDate(Timestamp timestamp) {
        this.orderDate = formatTimestamp(timestamp);
    }
    
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { 
        this.items = items;
        calculateTotal();
    }
    
    public Double getTotal() { 
        if (total == null) {
            calculateTotal();
        }
        return total; 
    }
    public void setTotal(Double total) { this.total = total; }
    
    // Helper methods
    public void addItem(OrderItem item) {
        this.items.add(item);
        calculateTotal();
    }
    
    private void calculateTotal() {
        this.total = items.stream()
                .mapToDouble(OrderItem::getTotal)
                .sum();
    }
    
    // Method to format the current timestamp
    private String formatCurrentTimestamp() {
        return formatTimestamp(new Timestamp(System.currentTimeMillis()));
    }
    
    // Method to format a timestamp
    private String formatTimestamp(Timestamp timestamp) {
        if (timestamp == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(timestamp);
    }
}