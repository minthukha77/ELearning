package com.blissstock.mappingSite.repository;

import com.blissstock.mappingSite.entity.TestQuestionCorrectAnswer;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionCorrectAnswerRepositoy extends JpaRepository<TestQuestionCorrectAnswer, Long> {

    @Query(value = "Select * from test_question_correct_answer where test_question_id = :test_question_id limit 1", nativeQuery = true)
    public TestQuestionCorrectAnswer getCorrectAnswerByQuestion(@Param("test_question_id") Long question_id);

    @Transactional
    @Modifying
    @Query(value = "delete from test_question_correct_answer where test_question_id = :test_question_id", nativeQuery = true)
    public Integer deleteByQuestionID(@Param("test_question_id") Long question_id);
}
