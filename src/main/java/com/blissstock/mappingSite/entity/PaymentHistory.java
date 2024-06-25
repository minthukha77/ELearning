package com.blissstock.mappingSite.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//mport java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "payment_history")
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_history_id")
    private Long paymentHistoryId;

    @ManyToOne
    @JoinColumn(name = "payment_for_teacher_id")
    private PaymentForTeacher paymentForTeacher;

    @OneToMany(
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "paymentHistory"
    )
    @JsonIgnore
    private List<PaymentRemark> paymentRemarks = new ArrayList<>();

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "payment_amount")
    private double paymentAmount;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "slip_img")
    private String slipImg;

    public Long getPaymentHistoryId() {
        return paymentHistoryId;
    }

    public void setPaymentHistoryId(Long paymentHistoryId) {
        this.paymentHistoryId = paymentHistoryId;
    }

    public PaymentForTeacher getPaymentForTeacher() {
        return paymentForTeacher;
    }

    public void setPaymentForTeacher(PaymentForTeacher paymentForTeacher) {
        this.paymentForTeacher = paymentForTeacher;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getSlipImg() {
        return slipImg;
    }

    public void setSlipImg(String slipImg) {
        this.slipImg = slipImg;
    }
}