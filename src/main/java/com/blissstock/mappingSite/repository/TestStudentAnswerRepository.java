package com.blissstock.mappingSite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blissstock.mappingSite.entity.TestStudentAnswer;

public interface TestStudentAnswerRepository extends JpaRepository<TestStudentAnswer, Long> {
        @Query(value = "Select * from test_student_answer where student_account_id = :student_account_id", nativeQuery = true)
        public List<TestStudentAnswer> getStudentAnswerList(@Param("student_account_id") Long student_account_id);

        @Query(value = "Select * from test_student_answer where student_account_id = :student_account_id and test_test_id = :test_id", nativeQuery = true)
        public List<TestStudentAnswer> getStudentAnswerListByTestAndStudent(
                        @Param("student_account_id") Long student_account_id,
                        @Param("test_id") Long test_id);

        @Query(value = "Select count(id) from test_student_answer where student_account_id = :student_account_id and test_test_id = :test_id", nativeQuery = true)
        public Integer getCountStudentAnswerListByTestAndStudent(
                        @Param("test_id") Long test_id,
                        @Param("student_account_id") Long student_account_id);

        @Query(value = "Select * from test_student_answer where test_test_id = :test_id", nativeQuery = true)
        public List<TestStudentAnswer> getStudentAnswerListByTest(@Param("test_id") Long test_id);

        @Query(value = "Select * from test_student_answer where student_account_id = :student_account_id and question_id = :question_id limit 1", nativeQuery = true)
        public TestStudentAnswer getStudentAnswer(@Param("student_account_id") Long student_account_id,
                        @Param("question_id") Long question_id);

        @Query(value = "Select count(id) from test_student_answer where marked_status = 'MARKING' and test_test_id = :test_test_id", nativeQuery = true)
        public Integer getMarkingQuestionCount(@Param("test_test_id") Long test_id);

        @Query(value = "Select * from test_student_answer where id = :id limit 1", nativeQuery = true)
        public TestStudentAnswer getStudentAnswerByID(@Param("id") Long id);

        @Query(value = "Select * from test_student_answer where question_id = :question_id and student_account_id = :student_account_id limit 1", nativeQuery = true)
        public TestStudentAnswer getStudentAnswerByQuestionID(@Param("question_id") Long question_id,
                        @Param("student_account_id") Long student_account_id);

        @Query(value = "Select count(id) from test_student_answer where marked_status = 'MARKING' and test_test_id = :test_test_id and student_account_id = :student_account_id", nativeQuery = true)
        public Integer getUnCheckAnswerCountByTestAndStudent(@Param("test_test_id") Long test_test_id,
                        @Param("student_account_id") Long student_account_id);
}
