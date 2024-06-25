package com.blissstock.mappingSite.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "join_course_user")
public class JoinCourseUser {

  @Column(name = "join_id")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long joinId;

  // mapping
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "courseId_fkey")
  @JsonIgnore
  private CourseInfo courseInfo;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "uid_fkey")
  @JsonIgnore
  private UserInfo userInfo;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "join")
  @JsonIgnore
  private List<Review> review = new ArrayList<>();

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "join")
  @JsonIgnore
  private List<LeaveInfo> leaveInfo = new ArrayList<>();

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "join")
  @JsonIgnore
  private PaymentReceive paymentReceive;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "joinByList")
  @JsonIgnore
  private List<PaymentReceive> paymentReceivesByList = new ArrayList<>();
}
