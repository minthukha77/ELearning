package com.blissstock.mappingSite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.blissstock.mappingSite.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    // Custom query methods if needed
    @Query(value = "Select * from question where test_id = :test_id order by test_id desc", nativeQuery = true)
    List <Question> getListByTestId(@Param("test_id") Long test_id);
}
