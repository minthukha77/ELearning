package com.blissstock.mappingSite.exceptions;

public class UserAlreadyExistException extends Exception{

    public static String errorMessage = "Email has been already registered";

    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }

    public UserAlreadyExistException() {
        super(errorMessage);
    }
}
