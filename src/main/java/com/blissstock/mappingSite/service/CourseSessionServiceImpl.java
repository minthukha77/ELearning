package com.blissstock.mappingSite.service;

import javax.servlet.http.HttpServletRequest;

import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.exceptions.CourseNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseSessionServiceImpl implements CourseSessionService {

    @Autowired
    CourseService courseService;

    public static final String COURSE_ID = "courseId";

    @Override
    public void saveCourse(HttpServletRequest httpServletRequest, long courseId) {
        httpServletRequest.getSession().setAttribute(COURSE_ID, courseId);
    }

    @Override
    public long getCourse(HttpServletRequest httpServletRequest) throws CourseNotFoundException {
        long courseId = Long.MIN_VALUE;
        try {
            courseId = (long) httpServletRequest.getSession().getAttribute(COURSE_ID);
            httpServletRequest.getSession().removeAttribute(COURSE_ID);
            // Check if course really exist
            CourseInfo courseInfo = courseService.getCourseById(courseId);
            if (courseInfo == null) {
                throw new CourseNotFoundException(courseId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new CourseNotFoundException(courseId);

           
        }

        return courseId;
    }
}
