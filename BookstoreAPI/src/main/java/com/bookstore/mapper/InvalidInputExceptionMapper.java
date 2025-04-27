/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.mapper;

import com.bookstore.exception.ErrorResponse;
import com.bookstore.exception.InvalidInputException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Umesh 
 */


@Provider
public class InvalidInputExceptionMapper implements ExceptionMapper<InvalidInputException> {
    @Override
    public Response toResponse(InvalidInputException e) {
        ErrorResponse error = new ErrorResponse("Invalid Input", e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}