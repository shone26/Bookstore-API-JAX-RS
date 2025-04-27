/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.service;

import com.bookstore.exception.BookNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.exception.OutOfStockException;
import com.bookstore.model.Book;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;



/**
 *
 * @author Shone
 */
public class BookService {
    private static final Map<Long, Book> books = new HashMap<>();
    private static final AtomicLong idCounter = new AtomicLong(1);
    
    //create a book
    public Book createBook(Book book) {
        validateBook(book);
        
        //set id and add to map
        book.setId(idCounter.getAndIncrement());
        books.put(book.getId(), book);
        return book;
        
    }
    
    // get all books
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }
    
    //get book by id
    public Book getBookById(Long id) {
        Book book = books.get(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        return book;
    }
    
    // update book
    public Book updateBook(Long id, Book book) {
        if (!books.containsKey(id)) {
            throw new BookNotFoundException(id);
        }
        
        validateBook(book);
        
        book.setId(id);
        books.put(id, book);    
        return book;
    }
    
    //delete book
    public void deleteBook(Long id) {
        if (!books.containsKey(id)) {
            throw new BookNotFoundException(id);
        }
        books.remove(id);
    }
    
    //get books by author
    public List<Book> getBooksByAuthor(Long authorId) {
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthorId().equals(authorId)) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }
    
    //helper methods to validate books data
    private void validateBook(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new InvalidInputException("Book ID cannot be null.");
        }
        
        if (book.getAuthorId() == null) {
            throw new InvalidInputException("Author ID cannot be null.");
        }
        
        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            throw new InvalidInputException("ISBM cannot be empty.");
        }
        
        if (book.getPublicationYear() == null) {
            throw new InvalidInputException("Publication year cannot be null.");
        }
        
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (book.getPublicationYear() > currentYear) {
            throw new InvalidInputException("Publication year cannot be in the future.");
        }
        
        if (book.getPrice() == null || book.getPrice() <= 0) {
            throw new InvalidInputException("Price must be greater than zero");
        }
        
        if (book.getStock() == null || book.getStock() <= 0) {
            throw new InvalidInputException("Stock cannot be negative");
        }
            // Check if book with same ISBN already exists
    if (bookWithIsbnExists(book.getIsbn())) {
        throw new InvalidInputException("Book with ISBN '" + book.getIsbn() + "' already exists.");
    }
    
        
    }
    
    //reduce stock when placing and order
    public void reduceStock(Long bookId, int quantity) {
        Book book = getBookById(bookId);
            if (book.getStock() < quantity) {
                throw new OutOfStockException(bookId, quantity, book.getStock());
            }   
        book.setStock(book.getStock() - quantity);
    }
    // Add this method to check if a book with the same ISBN already exists
    private boolean bookWithIsbnExists(String isbn) {
        return books.values().stream()
                .anyMatch(book -> book.getIsbn().equals(isbn));
}
}
