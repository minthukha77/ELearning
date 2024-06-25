package com.blissstock.mappingSite.service;

import java.util.List;

import com.blissstock.mappingSite.dto.CourseInfoDTO;
import com.blissstock.mappingSite.entity.CourseCategory;
import com.blissstock.mappingSite.entity.CourseInfo;

public interface CourseService {

    public List<CourseInfo> getCourseList(CourseInfoDTO courseInfoDTO);

    List<CourseCategory> getAllCourseCategory();
    void addCategory(CourseCategory categoryName); 
    CourseCategory getCategoryById(long categoryId);
    void deleteCategoryById(long categoryId);

    public CourseInfo getCourseById(long id);

    public void deleteCourseInfo(CourseInfo courseInfo);

    public void verifyCourseInfo(CourseInfo courseInfo);
}
