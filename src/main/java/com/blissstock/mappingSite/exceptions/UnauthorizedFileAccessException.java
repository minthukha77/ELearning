package com.blissstock.mappingSite.exceptions;

public class UnauthorizedFileAccessException extends Exception {

  public static String errorMessage = "Unauthorized file access";

  public UnauthorizedFileAccessException(String errorMessage) {
    super(errorMessage);
  }

  public UnauthorizedFileAccessException() {
    super(errorMessage);
  }

  public String formatErrorMessage(Long uid, String requestedResource) {
    return "User Id " + uid + " is trying to access " + requestedResource;
  }
}
