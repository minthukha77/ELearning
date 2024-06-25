package com.blissstock.mappingSite.exceptions;

public class UserNotFoundException extends Exception{

    public static String errorMessage = "User Not Found";

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public UserNotFoundException() {
        super(errorMessage);
    }
}
