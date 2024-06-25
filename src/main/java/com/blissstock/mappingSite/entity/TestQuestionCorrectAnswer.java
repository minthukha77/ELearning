package com.blissstock.mappingSite.entity;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "test_question_correct_answer")
public class TestQuestionCorrectAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    private TestQuestion testQuestion;

    @Column(name = "correct_answer")
    private String correctAnswer;

    public TestQuestionCorrectAnswer(Long id, TestQuestion testQuestion, String correctAnswer) {
        this.id = id;
        this.testQuestion = testQuestion;
        this.correctAnswer = correctAnswer;
    }
}
