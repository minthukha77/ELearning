package com.blissstock.mappingSite.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.blissstock.mappingSite.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity

@Table(name = "payment_receive")
public class PaymentReceive {

  @Column(name = "payment_receive_id")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long paymentReceiveId;

  @Column(name = "slip")
  private String slip;

  // @NotNull
  @Column(name = "payment_status", length = 15)
  private String paymentStatus = PaymentStatus.REQUESTED.getValue();

  // @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "payment_receive_date")
  private Date paymentReceiveDate;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "join_id")
  JoinCourseUser join;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "join_id", insertable=false, updatable=false)
    @JsonIgnore
    private JoinCourseUser joinByList;
  
}
