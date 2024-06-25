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
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long resultId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Test test;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private UserInfo user;

    @Column(name = "mark")
    private Integer resultMark;

    @Column(name = "result", length = 100)
    private String result;

    @Column(name = "teacher_comment", columnDefinition = "TEXT")
    private String teacherComment;

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public Integer getResultMark() {
        return resultMark;
    }

    public void setResultMark(Integer resultMark) {
        this.resultMark = resultMark;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }

    public Result(Long resultId, Test test, UserInfo user, Integer resultMark, String result, String teacherComment) {
        this.resultId = resultId;
        this.test = test;
        this.user = user;
        this.resultMark = resultMark;
        this.result = result;
        this.teacherComment = teacherComment;
    }

}

