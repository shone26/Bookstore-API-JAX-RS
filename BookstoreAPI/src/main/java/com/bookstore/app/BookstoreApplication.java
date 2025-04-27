    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.app;

import com.bookstore.resource.CustomerResource;
import com.bookstore.mapper.*;
import com.bookstore.resource.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author Umesh 
 */

@ApplicationPath("/api")
public class BookstoreApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        
        // Add resource classes
        classes.add(BookResource.class);
        classes.add(AuthorResource.class);
        classes.add(CustomerResource.class);
        classes.add(CartResource.class);
        classes.add(OrderResource.class);
        
        // Add exception mappers
        classes.add(BookNotFoundExceptionMapper.class);
        classes.add(AuthorNotFoundExceptionMapper.class);
        classes.add(CustomerNotFoundExceptionMapper.class);
        classes.add(InvalidInputExceptionMapper.class);
        classes.add(OutOfStockExceptionMapper.class);
        classes.add(CartNotFoundExceptionMapper.class);
        classes.add(OrderNotFoundExceptionMapper.class);
        
        return classes;
    }
}