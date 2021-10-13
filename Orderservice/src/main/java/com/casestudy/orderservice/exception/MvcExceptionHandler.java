package com.casestudy.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jt on 2019-05-25.
 */
@RestControllerAdvice(annotations = RestController.class)
public class MvcExceptionHandler {

    @ExceptionHandler(OrderServiceException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionHandling exceptionGetter(final OrderServiceException exception, final HttpServletRequest request){
        ExceptionHandling exceptionHandling = new ExceptionHandling();
        exceptionHandling.setMessage(exception.getMessage());
        exceptionHandling.setUrl(request.getRequestURI());
        return exceptionHandling;
    }
}
