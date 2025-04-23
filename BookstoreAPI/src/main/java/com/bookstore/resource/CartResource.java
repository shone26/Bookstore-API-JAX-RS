/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.resource;

import com.bookstore.model.Cart;
import com.bookstore.model.CartItem;
import com.bookstore.service.BookService;
import com.bookstore.service.CartService;
import com.bookstore.service.CustomerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Shone
 */


@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private final CustomerService customerService = new CustomerService();
    private final BookService bookService = new BookService();
    private final CartService cartService = new CartService(customerService, bookService);
    
    @POST
    @Path("/items")
    public Response addItemToCart(@PathParam("customerId") Long customerId, CartItem item) {
        Cart updated = cartService.addItem(customerId, item);
        return Response.status(Response.Status.CREATED).entity(updated).build();
    }
    
    @GET
    public Response getCart(@PathParam("customerId") Long customerId) {
        Cart cart = cartService.getCart(customerId);
        return Response.ok(cart).build();
    }
    
    @PUT
    @Path("/items/{bookId}")
    public Response updateItemQuantity(
            @PathParam("customerId") Long customerId,
            @PathParam("bookId") Long bookId,
            @QueryParam("quantity") Integer quantity) {
        
        Cart updated = cartService.updateItemQuantity(customerId, bookId, quantity);
        return Response.ok(updated).build();
    }
    
    @DELETE
    @Path("/items/{bookId}")
    public Response removeItemFromCart(
            @PathParam("customerId") Long customerId,
            @PathParam("bookId") Long bookId) {
        
        Cart updated = cartService.removeItem(customerId, bookId);
        return Response.ok(updated).build();
    }
}