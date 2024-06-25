package com.blissstock.mappingSite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "payment_remark")
public class PaymentRemark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_remark_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payment_history_id")
    private PaymentHistory paymentHistory;

    @ManyToOne
    @JoinColumn(name = "uid")
    private UserInfo userInfo;

    @Column(name = "remark_date")
    private Date remarkDate;

    @Column(name = "remark_status")
    private boolean remarkStatus;

    @Column(name = "remark", length = 255)
    private String remark;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentHistory getPaymentHistory() {
        return paymentHistory;
    }

    public void setPaymentHistory(PaymentHistory paymentHistory) {
        this.paymentHistory = paymentHistory;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Date getRemarkDate() {
        return remarkDate;
    }

    public void setRemarkDate(Date remarkDate) {
        this.remarkDate = remarkDate;
    }

    public boolean getRemarkStatus() {
        return remarkStatus;
    }

    public void setRemarkStatus(boolean remarkStatus) {
        this.remarkStatus = remarkStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}