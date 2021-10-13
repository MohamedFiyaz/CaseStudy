package com.casestudy.restaurantservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jt on 2019-05-25.
 */
@RestControllerAdvice(annotations = RestController.class)
public class MvcExceptionHandler {

    @ExceptionHandler(RestaurantSearchServiceException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionHandling exceptionGetter(final RestaurantSearchServiceException exception, final HttpServletRequest request){
        ExceptionHandling exceptionHandling = new ExceptionHandling();
        exceptionHandling.setMessage(exception.getMessage());
        exceptionHandling.setUrl(request.getRequestURI());
        return exceptionHandling;
    }
}
