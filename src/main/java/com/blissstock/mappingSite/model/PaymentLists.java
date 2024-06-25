package com.blissstock.mappingSite.model;

import java.util.Date;

public class PaymentLists {
    String paymentDate;
    String courseStartDate;
    String courseEndDate;
    String paymentStatus;
    String userName;
    String teacherName;
    String courseName;
    int courseFees;
    Long userId;
    Long courseId;

    public PaymentLists() {
    }
    
        public PaymentLists(String paymentDate, String courseStartDate, String courseEndDate, String paymentStatus, String userName, String teacherName, String courseName, int courseFees, Long userId, Long courseId) {
            this.paymentDate = paymentDate;
            this.courseStartDate = courseStartDate;
            this.courseEndDate = courseEndDate;
            this.paymentStatus = paymentStatus;
            this.userName = userName;
            this.teacherName = teacherName;
            this.courseName = courseName;
            this.courseFees = courseFees;
            this.userId = userId;
            this.courseId = courseId;
        }
    

    public PaymentLists(String paymentStatus2) {

        this.paymentStatus = paymentStatus2;

        }

    public String getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCourseStartDate() {
        return this.courseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public String getCourseEndDate() {
        return this.courseEndDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public String getPaymentStatus() {
        return this.paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTeacherName()  {
        return this.teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseFees() {
        return this.courseFees;
    }

    public void setCourseFees(int courseFees) {
        this.courseFees = courseFees;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
        
    
}

