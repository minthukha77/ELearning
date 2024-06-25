package com.blissstock.mappingSite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "test_student")
public class TestStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Test test;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserInfo userInfo;

    @Column(length = 15)
    private String student_exam_start_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public UserInfo getUserAccount() {
        return userInfo;
    }

    public void UserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getStudentExamStartTime() {
        return student_exam_start_time;
    }

    public void setStudentExamStartTime(String student_exam_start_time) {
        this.student_exam_start_time = student_exam_start_time;
    }

        public TestStudent(Long id, Test test, UserInfo userInfo, String student_exam_start_time) {
        this.id = id;
        this.test = test;
        this.userInfo = userInfo;
        this.student_exam_start_time = student_exam_start_time;
    }
}
