package com.blissstock.mappingSite.entity;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @Column(name = "question_text", columnDefinition = "TEXT")
    private String questionText;

    @Column(name = "question_material")
    private String questionMaterial;

    @Column(name = "choice")
    private String choice;

    @Column(name = "question_type")
    private String questionType;

    @Column(name = "maximum_mark")
    private Integer maximumMark;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionMaterial() {
        return questionMaterial;
    }

    public void setQuestionMaterial(String questionMaterial) {
        this.questionMaterial = questionMaterial;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Integer getMaximumMark() {
        return maximumMark;
    }

    public void setMaximumMark(Integer maximumMark) {
        this.maximumMark = maximumMark;
    }

    
}
