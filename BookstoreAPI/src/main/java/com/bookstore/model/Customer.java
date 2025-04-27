    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.bookstore.model;

    import java.util.List;

    /**
     *
     * @author Umesh 
     */
    public class Customer {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;

        // Constructors
        public Customer() {}

        public Customer(String firstName, String email, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
        }



        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getEmail() { return email; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }    
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }