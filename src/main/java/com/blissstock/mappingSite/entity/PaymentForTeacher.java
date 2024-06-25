package com.blissstock.mappingSite.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

// import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// import org.hibernate.annotations.OnDelete;
// import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "payment_for_teacher")
public class PaymentForTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_for_teacher_id")
    private long paymentForTeacherId;

    // @Column(name = "course_id")
    // private Integer courseId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseInfo courseInfo;

    @OneToMany(
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "paymentForTeacher"
    )
    @JsonIgnore
    private List<PaymentHistory> paymentHistory = new ArrayList<>();

    

    @Column(name = "no_of_enroll_person")
    private Integer noOfEnrollPerson;

    @Column(name = "calculate_date_from")
    private Date calculateDateFrom;

    @Column(name = "calculate_date_to")
    private Date calculateDateTo;
  
    @Column(name = "course_fee")
    private double courseFee;	

    @Column(name = "payment_amount")
    private double paymentAmount;

    @Column(name = "payment_amount_percentage")
    private double paymentAmountPercentage;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "status")
    private String status;

    @Column(name = "payment_verify")
    private boolean paymentVerify;

    public long getPaymentForTeacherId() {
        return paymentForTeacherId;
    }

    public void setPaymentForTeacherId(Long paymentForTeacherId) {
        this.paymentForTeacherId = paymentForTeacherId;
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

    // public Integer getCourseId() {
    //     return courseId;
    // }

    // public void setCourseId(Integer courseId) {
    //     this.courseId = courseId;
    // }
    
    public Integer getNoOfEnrollPerson() {
        return noOfEnrollPerson;
    }

    public void setNoOfEnrollPerson(Integer noOfEnrollPerson) {
        this.noOfEnrollPerson = noOfEnrollPerson;
    }

    public Date getCalculateDateFrom() {
        return calculateDateFrom;
    }

    public void setCalculateDateFrom(Date calculateDateFrom) {
        this.calculateDateFrom = calculateDateFrom;
    }

    public Date getCalculateDateTo() {
        return calculateDateTo;
    }

    public void setCalculateDateTo(Date calculateDateTo) {
        this.calculateDateTo = calculateDateTo;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public double getPaymentAmountPercentage() {
        return paymentAmountPercentage;
    }

    public void setPaymentAmountPercentage(double paymentAmountPercentage) {
        this.paymentAmountPercentage = paymentAmountPercentage;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getPaymentVerify() {
        return paymentVerify;
    }

    public void setPaymentVerify(boolean paymentVerify) {
        this.paymentVerify = paymentVerify;
    }

}