package com.blissstock.mappingSite.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class StudentListForExamResult {
    String studentName;
    String studentEmail;
    String studentPhone;
    String examResult;
    Integer stuMarks;
    Integer maxMarks;
    FileInfo profilePic;
    
    
    public StudentListForExamResult(String studentName, String studentEmail, String studentPhone, String examResult, Integer stuMarks, Integer maxMarks, FileInfo profilePic) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.examResult = examResult;
        this.stuMarks = stuMarks;
        this.maxMarks = maxMarks;
        this.profilePic = profilePic;
                
    }

}
    
