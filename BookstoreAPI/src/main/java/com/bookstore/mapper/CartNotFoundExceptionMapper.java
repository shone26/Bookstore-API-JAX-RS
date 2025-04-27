/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.mapper;

import com.bookstore.exception.CartNotFoundException;
import com.bookstore.exception.ErrorResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Umesh 
 */


@Provider
public class CartNotFoundExceptionMapper implements ExceptionMapper<CartNotFoundException> {
    @Override
    public Response toResponse(CartNotFoundException e) {
        ErrorResponse error = new ErrorResponse("Cart Not Found", e.getMessage());
        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
