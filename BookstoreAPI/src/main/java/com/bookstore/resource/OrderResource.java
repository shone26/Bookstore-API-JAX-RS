/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.resource;

import com.bookstore.model.Order;
import com.bookstore.service.BookService;
import com.bookstore.service.CartService;
import com.bookstore.service.CustomerService;
import com.bookstore.service.OrderService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Shone
 */


@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    private final CustomerService customerService = new CustomerService();
    private final BookService bookService = new BookService();
    private final CartService cartService = new CartService(customerService, bookService);
    private final OrderService orderService = new OrderService(customerService, bookService, cartService);
    
    @POST
    public Response createOrder(@PathParam("customerId") Long customerId) {
        Order order = orderService.createOrder(customerId);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }
    
    @GET
    public Response getCustomerOrders(@PathParam("customerId") Long customerId) {
        List<Order> orders = orderService.getCustomerOrders(customerId);
        return Response.ok(orders).build();
    }
    
    @GET
    @Path("/{orderId}")
    public Response getOrder(
            @PathParam("customerId") Long customerId,
            @PathParam("orderId") Long orderId) {
        
        Order order = orderService.getOrder(customerId, orderId);
        return Response.ok(order).build();
    }
}