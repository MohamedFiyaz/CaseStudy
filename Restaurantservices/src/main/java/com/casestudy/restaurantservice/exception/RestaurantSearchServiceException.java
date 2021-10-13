package com.casestudy.restaurantservice.exception;

public class RestaurantSearchServiceException extends Exception{

    public RestaurantSearchServiceException(){
        super();
    }

    public RestaurantSearchServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RestaurantSearchServiceException(String message, Throwable cause){
        super(message,cause);
    }

    public RestaurantSearchServiceException(String message){
        super(message);
    }

    public RestaurantSearchServiceException(Throwable throwable){
        super(throwable);
    }
}
