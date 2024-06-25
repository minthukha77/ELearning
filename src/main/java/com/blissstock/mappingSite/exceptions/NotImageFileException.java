package com.blissstock.mappingSite.exceptions;

public class NotImageFileException extends RuntimeException{
    public static String errorMessage = "Email has been already registered";

    public NotImageFileException(String errorMessage) {
        super(errorMessage);
    }

    public NotImageFileException() {
        super(errorMessage);
    }
    
}
