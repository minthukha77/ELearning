package com.blissstock.mappingSite.model;

import java.util.Date;

//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class StudentListForExamineeList {
    String studentName;
    String studentPhoneNumber;
    String studentEmail;    
    FileInfo profilePic;
    Long testId;
    Long uid;
    
    public StudentListForExamineeList(String studentName, String studentPhoneNumber, String studentEmail, FileInfo profilePic, Long testId, Long uid) {
        this.studentName = studentName;
        this.studentPhoneNumber = studentPhoneNumber;
        this.studentEmail = studentEmail;        
        this.profilePic = profilePic;
        this.testId = testId;
        this.uid = uid;
    }

}
    

