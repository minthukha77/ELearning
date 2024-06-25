package com.blissstock.mappingSite.model;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class TeacherPaymentHistoryLists {
    String courseName;
    String teacherName;
    Long teacherId;
    Date paymentDate;
    int courseFees;
    Long courseId;
    Long paymentForTeacherId;
    
    public TeacherPaymentHistoryLists(String courseName, String teacherName, Long teacherId, Date paymentDate,int courseFees,Long courseId, Long paymentForTeacherId) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.teacherId = teacherId;
        this.paymentDate = paymentDate;
        this.courseFees = courseFees;
        this.courseId = courseId;
        this.paymentForTeacherId = paymentForTeacherId;
    }
    


    
   




    


// public TeacherPaymentLists(String paymentStatus2) {

//     this.paymentStatus = paymentStatus2;

//     }

// public void setDurat(Long teacherId) {
//     this.teacherId = teacherId;
// }

// public Long getTeacherId()  {
//     return this.teacherId;
// }

// public void setTeacherId(Long teacherId) {
//     this.teacherId = teacherId;
// }

// public Long getTeacherId()  {
//     return this.teacherId;
// }

// public void setTeacherName(String teacherName) {
//     this.teacherName = teacherName;
// }

// public String getTeacherName()  {
//     return this.teacherName;
// }

// public void setCourseName(String courseName) {
//     this.courseName = courseName;
// }

// public String getCourseName() {
//     return this.courseName;
// }

// public void setCourseStartDate(String courseStartDate) {
//     this.courseStartDate = courseStartDate;
// }

// public String getCourseStartDate() {
//     return this.courseStartDate;
// }

// public void setCourseEndDate(String courseEndDate) {
//     this.courseEndDate = courseEndDate;
// }

// public String getCourseEndDate() {
//     return this.courseEndDate;
// }

// public void setPaymentDate(String paymentDate) {
//     this.paymentDate = paymentDate;
// }

// public String getPaymentDate() {
//     return this.paymentDate;
// }

// public void setCourseFees(int courseFees) {
//     this.courseFees = courseFees;
// }

// public int getCourseFees() {
//     return this.courseFees;
// }

// public void setPaymentStatus(String paymentStatus) {
//     this.paymentStatus = paymentStatus;
// }

// public String getPaymentStatus() {
//     return this.paymentStatus;
// }



}

