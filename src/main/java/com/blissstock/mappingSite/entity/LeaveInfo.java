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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "leave_info")
public class LeaveInfo {

  @Column(name = "leave_id")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long leaveId;

  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "leave_date")
  private Date leaveDate;

  @Column(
    name = "leave_start_time"
    // ,columnDefinition = "TIMESTAMP WITH TIME ZONE"
  )
  // @NotNull
  // @Temporal(TemporalType.TIME)
  private String leaveStartTime;

  @Column(
    name = "leave_end_time"
    // ,columnDefinition = "TIMESTAMP WITH TIME ZONE"
  )
  // @NotNull
  // @Temporal(TemporalType.TIME)
  private String leaveEndTime;

  @NotBlank(message = "Please fill reason")
  @Column(name = "reason")
  private String reason;

  //mapping
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "join_fkey")
  @JsonIgnore
  private JoinCourseUser join;

  // @NotNull
  // @DateTimeFormat(pattern = "MM-dd-yyyy")
  // //@Temporal(TemporalType.DATE)
  // @Column(name = "leave_start_date")
  // private Date leaveStartDate;

  // @NotNull
  // @DateTimeFormat(pattern = "MM-dd-yyyy")
  // //@Temporal(TemporalType.DATE)
  // @Column(name = "leave_end_date")
  // private Date leaveEndDate;


  //mapping
  // @ManyToOne(fetch = FetchType.EAGER, optional = false)
  // @JoinColumn(name = "uid_fkey")
  // @JsonIgnore
  // private UserInfo userInfo;

  // @ManyToOne(fetch = FetchType.EAGER, optional = false)
  // @JoinColumn(name = "courseId_fkey")
  // @JsonIgnore
  // private CourseInfo courseInfo;

  // public Long getLeaveId() {
  //   return this.leaveId;
  // }

  // public void setLeaveId(Long leaveId) {
  //   this.leaveId = leaveId;
  // }

  // public Date getLeaveStartTime() {
  //   return this.leaveStartTime;
  // }

  // public void setLeaveStartTime(Time leaveStartTime) {
  //   this.leaveStartTime = leaveStartTime;
  // }

  // public Date getLeaveEndTime() {
  //   return this.leaveEndTime;
  // }

  // public void setLeaveEndTime(Time leaveEndTime) {
  //   this.leaveEndTime = leaveEndTime;
  // }

  // public String getReason() {
  //   return this.reason;
  // }

  // public void setReason(String reason) {
  //   this.reason = reason;
  // }

  // public UserInfo getUserInfo() {
  //   return this.userInfo;
  // }

  // public void setUserInfo(UserInfo userInfo) {
  //   this.userInfo = userInfo;
  // }

  // public CourseInfo getCourseInfo() {
  //   return this.courseInfo;
  // }

  // public void setCourseInfo(CourseInfo courseInfo) {
  //   this.courseInfo = courseInfo;
  // }

  // public Date getLeaveStartDate() {
  //   return this.leaveStartDate;
  // }

  // public void setLeaveStartDate(Date leaveStartDate) {
  //   this.leaveStartDate = leaveStartDate;
  // }

  // public Date getLeaveEndDate() {
  //   return this.leaveEndDate;
  // }

  // public void setLeaveEndDate(Date leaveEndDate) {
  //   this.leaveEndDate = leaveEndDate;
  // }

  // public LeaveInfo(
  //   Long leaveId,
  //   Date leaveDate,
  //   // Date leaveStartDate,
  //   // Date leaveEndDate,
  //   Time leaveStartTime,
  //   Time leaveEndTime,
  //   String reason,
  //   // UserInfo userInfo,
  //   // CourseInfo courseInfo,
  //   JoinCourseUser join
  // ) {
  //   this.leaveId = leaveId;
  //   this.leaveDate = leaveDate;
  //   // this.leaveStartDate = leaveStartDate;
  //   // this.leaveEndDate = leaveEndDate;
  //   this.leaveStartTime = leaveStartTime;
  //   this.leaveEndTime = leaveEndTime;
  //   this.reason = reason;
  //   // this.userInfo = userInfo;
  //   // this.courseInfo = courseInfo;
  //   this.join = join;
  // }

  // public LeaveInfo() {}
}
