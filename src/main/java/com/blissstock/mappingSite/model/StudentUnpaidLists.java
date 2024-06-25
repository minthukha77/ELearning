package com.blissstock.mappingSite.model;

import java.util.Date;

public class StudentUnpaidLists {
    String userName;
    String courseName;
    String teacherName;
    String courseType;
    String courseStartDate;
    String courseEndDate;
    int courseFees;
    Long userId;
    Long teacherId;
    Long courseId;
    String paymentStatus;

    public StudentUnpaidLists() {
    }
    
        public StudentUnpaidLists(String userName, String courseName, String teacherName, String courseType, String courseStartDate, String courseEndDate, int courseFees, Long userId, Long teacherId, Long courseId, String paymentStatus) {
            this.userName = userName;
            this.courseName = courseName;
            this.teacherName = teacherName;
            this.courseType = courseType;
            this.courseStartDate = courseStartDate;
            this.courseEndDate = courseEndDate;
            this.courseFees = courseFees;
            this.userId = userId;
            this.teacherId = teacherId;
            this.courseId = courseId;
            this.paymentStatus = paymentStatus;
            
        }
    

    // public StudentUnpaidLists(String paymentStatus2) {

    //     this.paymentStatus = paymentStatus2;

    //     }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherName()  {
        return this.teacherName;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseType() {
        return this.courseType;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public String getCourseStartDate() {
        return this.courseStartDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public String getCourseEndDate() {
        return this.courseEndDate;
    }

    public void setCourseFees(int courseFees) {
        this.courseFees = courseFees;
    }

    public int getCourseFees() {
        return this.courseFees;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() {
        return this.teacherId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return this.courseId;
    }

    
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    public String getPaymentStatus() {
        return this.paymentStatus;
    }


        
    
}

