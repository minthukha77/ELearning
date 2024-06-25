package com.blissstock.mappingSite.model;

import java.util.Date;

//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class TestStudentList {
    String studentName;
    String studentPhoneNumber;
    String studentEmail;
    Integer checkedQuestion;
    Integer totalQuestion;
    FileInfo profilePic;
    Long testStudentId;
    
    public TestStudentList(String studentName, String studentPhoneNumber, String studentEmail, Integer checkedQuestion, Integer totalQuestion, FileInfo profilePic, Long testStudentId) {
        this.studentName = studentName;
        this.studentPhoneNumber = studentPhoneNumber;
        this.studentEmail = studentEmail;
        this.checkedQuestion = checkedQuestion;
        this.totalQuestion = totalQuestion;
        this.profilePic = profilePic;
        this.testStudentId = testStudentId;
        
    }

}
    

