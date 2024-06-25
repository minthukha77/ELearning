package com.blissstock.mappingSite.model;
import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class TeacherPaymentLists {
    // String paymentDate;
    // String courseStartDate;
    // String courseEndDate;
    // String paymentStatus;
    // // String userName;
    // String teacherName;
    // String courseName;
    // Double courseFees;
    // Long teacherId;
    // // Long userId;
    // // Long courseId;
    // String duration;
    // int payAmount;

        Long paymentForTeacherId;    
        String teacherName; 
        String courseName;
        String courseStartDate; 
        String courseEndDate;
        String duration;
        LocalDate paymentDate;
        Date calculateFrom;
        Date calculateTo;
        int courseFees;
        int totalAmount;
        int payAmount;
        String paymentStatus;
        long teacherId;
        long  courseId;
        String varifyStateString;


    
    public TeacherPaymentLists(Long paymentForTeacherId, String teacherName, String courseName, String courseStartDate, String courseEndDate, String duration, LocalDate paymentDate, Date calculateFrom, Date calculateTo, int courseFees, int totalAmount, int payAmount, String paymentStatus, long teacherId, long  courseId, String varifyStateString) {
        this.paymentForTeacherId = paymentForTeacherId;
        this.teacherName = teacherName;
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.duration = duration;
        this.paymentDate = paymentDate;
        this.calculateFrom = calculateFrom;
        this.calculateTo = calculateTo;
        this.courseFees = courseFees;
        this.totalAmount = totalAmount;
        this.payAmount = payAmount;
        this.paymentStatus = paymentStatus;
        this.teacherId = teacherId;
        this.courseId = courseId;
        this.varifyStateString = varifyStateString;
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

