package com.blissstock.mappingSite.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.PaymentForTeacher;

@Repository
public interface PaymentForTeacherRepository extends JpaRepository<PaymentForTeacher, Long> {
    
    
    @Query("SELECT p FROM PaymentForTeacher p JOIN p.courseInfo c WHERE c.id = :courseId")
    List<PaymentForTeacher> findByCourseId(@Param("courseId") Long courseId);
    
}