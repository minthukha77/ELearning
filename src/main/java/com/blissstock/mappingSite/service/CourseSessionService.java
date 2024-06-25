package com.blissstock.mappingSite.service;

import javax.servlet.http.HttpServletRequest;

import com.blissstock.mappingSite.exceptions.CourseNotFoundException;

public interface CourseSessionService {
    public void saveCourse(HttpServletRequest httpServletRequest, long courseId);
    public long getCourse(HttpServletRequest httpServletRequest) throws CourseNotFoundException;
}
