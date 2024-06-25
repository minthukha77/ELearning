package com.blissstock.mappingSite.repository;


import java.util.List;

import com.blissstock.mappingSite.entity.CourseTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTimeRepository extends CrudRepository<CourseTime, Long> {
    @Query(value="DELETE FROM course_time WHERE course_id_fkey=:courseID",nativeQuery = true)
    public List<CourseTime> deleteByCourseID(@Param("courseID") Long courseID);

    @Query(value="SELECT * FROM course_time WHERE course_id_fkey=:courseID",nativeQuery = true)
    public List<CourseTime> searchTimeByCourseID(@Param("courseID") Long courseID);
}
    
    
