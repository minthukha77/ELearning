package com.blissstock.mappingSite.exceptions;

public class SyllabusNotFoundException extends Exception{

    public static String errorMessage = "Course Not Found";

    public SyllabusNotFoundException(long syllabusId) {
        super("Syllabus "+syllabusId+" not found");
    }

    public SyllabusNotFoundException() {
        super(errorMessage);
    }
}
