package com.blissstock.mappingSite.repository;

import java.util.List;

import com.blissstock.mappingSite.entity.CourseInfo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseInfoRepository extends JpaRepository<CourseInfo, Long> {

    public List<CourseInfo> findByClassType(String classType);

    public List<CourseInfo> findByClassTypeIgnoreCase(String classType);

    public List<CourseInfo> findAll(Specification<CourseInfo> spec, Pageable pageable);

    public List<CourseInfo> findByClassTypeAndIsCourseApproved(String classType, Boolean isCourseApproved);

    // public List<CourseInfo> findByCourseName(String courseName);
    // public List<CourseInfo> findByStartDate(Date startDate);
    // //List<CourseInfo> findByUserInfo(UserInfo userInfo);
    // public List<CourseInfo> findByLevel(String level);
    // public List<CourseInfo> findByCategory(String category);

    // public List<CourseInfo> findByLevelAndCategory(String level, String
    // category);
    // public List<CourseInfo> findByLevelAndClassType(String level, String
    // classType);
    // public List<CourseInfo> findByCategoryAndClassType(String category, String
    // classType);
    // public List<CourseInfo> findByLevelAndCategoryAndClassType(String level,
    // String category, String classType);

    @Query(nativeQuery = true, value = "select * from course_info where first_name ilike %:courseName%")
    public List<CourseInfo> findByCourseName(@Param("courseName") String courseName);

    @Query(nativeQuery = true, value = "select * from course_info where course_id=:courseID")
    public CourseInfo findByCourseID(@Param("courseID") Long courseID);

    @Query(value = "SELECT * FROM course_info WHERE course_id=:courseID AND uid_fkey=:uid", nativeQuery = true)
    public CourseInfo findByCourseIDUID(@Param("courseID") Long courseID, @Param("uid") Long uid);

    @Query(value = "UPDATE course_info SET course_name=:courseName, category=:category, class_link=:classLink, level=:level, about_course=:aboutCourse, prerequisite=:prerequisite, course_fees=:fees, is_course_approved=:isCourseApproved WHERE course_id=:courseID", nativeQuery = true)
    public CourseInfo updateVideoCourse(@Param("courseID") Long courseID, @Param("courseName") String courseName,
            @Param("category") String category, @Param("classLink") String classLink, @Param("level") String level,
            @Param("aboutCourse") String aboutCourse, @Param("prerequisite") String prerequisite,
            @Param("fees") int fees, @Param("isCourseApproved") boolean isCourseApproved);

    @Query(value = "SELECT * FROM course_info WHERE uid_fkey=:uid", nativeQuery = true)
    public List<CourseInfo> findByUID(@Param("uid") Long uid);
}