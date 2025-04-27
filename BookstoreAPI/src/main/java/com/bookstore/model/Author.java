/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.model;

/**
 *
 * @author Umesh 
 */
public class Author {
    private Long id;
    private String firstName;
    private String lastName;
    private String biography;
    
    // Constructors
    public Author() {}
    
    public Author(String firstName, String lastName, String biography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
    }
    

    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
//    public String getName() { return firstName + lastName;}
        public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }
}