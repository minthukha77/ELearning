package com.blissstock.mappingSite.exceptions;

public class CourseNotFoundException extends Exception{

    public static String errorMessage = "Course Not Found";

    public CourseNotFoundException(long courseId) {
        super("Course "+courseId+" not found");
    }

    public CourseNotFoundException() {
        super(errorMessage);
    }
}
