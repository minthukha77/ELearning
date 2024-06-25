package com.blissstock.mappingSite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.blissstock.mappingSite.entity.StudentAnswer;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {
    // Custom methods can be added here as needed
    @Query(value = "Select * from student_answer where test_id = :test_id order by test_id desc", nativeQuery = true)
    List <StudentAnswer> getListByTestId(@Param("test_id") Long test_id);
}
