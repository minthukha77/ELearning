package com.blissstock.mappingSite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "test_student_answer")
public class TestStudentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Test test;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserInfo student;

    @ManyToOne(fetch = FetchType.EAGER)
    private TestQuestion question;

    @Column(name = "student_answer", length = 1000)
    private String student_answer;

    @Column(name = "student_answer_link", length = 255)
    private String student_answer_link;

    @Column(name = "correct_status", length = 255)
    private String correct_status;

    @Column(name = "acquired_mark")
    private Integer acquired_mark;

    @Column(name = "marked_status", length = 255)
    private String marked_status;

    public TestStudentAnswer(Long id, Test test, UserInfo student, TestQuestion question, String student_answer,
            String student_answer_link, String correct_status, Integer acquired_mark, String marked_status) {
        this.id = id;
        this.test = test;
        this.student = student;
        this.question = question;
        this.student_answer = student_answer;
        this.student_answer_link = student_answer_link;
        this.correct_status = correct_status;
        this.acquired_mark = acquired_mark;
        this.marked_status = marked_status;
    }

    
}
