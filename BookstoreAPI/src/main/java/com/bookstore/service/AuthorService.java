/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.service;

import com.bookstore.exception.AuthorNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.model.Author;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Shone
 */


public class AuthorService {
    private static final Map<Long, Author> authors = new HashMap<>();
    private static final AtomicLong idCounter = new AtomicLong(1);
    
    // Create an author
    public Author createAuthor(Author author) {
        validateAuthor(author);
        
        author.setId(idCounter.getAndIncrement());
        authors.put(author.getId(), author);
        return author;
    }
    
    // Get all authors
    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors.values());
    }
    
    // Get author by ID
    public Author getAuthorById(Long id) {
        Author author = authors.get(id);
        if (author == null) {
            throw new AuthorNotFoundException(id);
        }
        return author;
    }
    
    // Update author
    public Author updateAuthor(Long id, Author author) {
        if (!authors.containsKey(id)) {
            throw new AuthorNotFoundException(id);
        }
        
        validateAuthor(author);
        
        author.setId(id);
        authors.put(id, author);
        return author;
    }
    
    // Delete author
    public void deleteAuthor(Long id) {
        if (!authors.containsKey(id)) {
            throw new AuthorNotFoundException(id);
        }
        authors.remove(id);
    }
    
    // Check if author exists
    public boolean authorExists(Long id) {
        return authors.containsKey(id);
    }
    
    // Helper method to validate author data
    private void validateAuthor(Author author) {
        if (author.getName() == null || author.getName().trim().isEmpty()) {
            throw new InvalidInputException("Author name cannot be empty.");
        }
    }
}