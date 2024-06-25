package com.blissstock.mappingSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blissstock.mappingSite.entity.CourseCategory;

@Repository
public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long>{

       

}
