package com.blissstock.mappingSite.entity;

import javax.persistence.*;

@Entity
@Table(name = "student_answer")
public class StudentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_answer_id")
    private Long studentAnswerId;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private UserInfo student;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "student_answer", length = 100)
    private String studentAnswer;

    @Column(name = "correct_status", length = 100)
    private String correctStatus;

    @Column(name = "acquired_mark")
    private Integer acquiredMark;

    @Column(name = "marked_status", length = 100)
    private String markedStatus;

    public Long getStudentAnswerId() {
        return studentAnswerId;
    }

    public void setStudentAnswerId(Long studentAnswerId) {
        this.studentAnswerId = studentAnswerId;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public UserInfo getStudent() {
        return student;
    }

    public void setStudent(UserInfo student) {
        this.student = student;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public String getCorrectStatus() {
        return correctStatus;
    }

    public void setCorrectStatus(String correctStatus) {
        this.correctStatus = correctStatus;
    }

    public Integer getAcquiredMark() {
        return acquiredMark;
    }

    public void setAcquiredMark(Integer acquiredMark) {
        this.acquiredMark = acquiredMark;
    }

    public String getMarkedStatus() {
        return markedStatus;
    }

    public void setMarkedStatus(String markedStatus) {
        this.markedStatus = markedStatus;
    }

    
}
