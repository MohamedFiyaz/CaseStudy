package com.casestudy.restaurantservice.exception;

public class ItemNotFoundException extends RestaurantSearchServiceException {

    public ItemNotFoundException(){
        super();
    }

    public ItemNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ItemNotFoundException(String message, Throwable cause){
        super(message,cause);
    }

    public ItemNotFoundException(String message){
        super(message);
    }

    public ItemNotFoundException(Throwable throwable){
        super(throwable);
    }
}
