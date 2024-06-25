package com.blissstock.mappingSite.exceptions;

public class InsuffientPermssionException extends RuntimeException{
    public static String errorMessage = "Permission Insuccfi";

    public InsuffientPermssionException(String errorMessage) {
        super(errorMessage);
    }

    public InsuffientPermssionException() {
        super(errorMessage);
    }
    
}
