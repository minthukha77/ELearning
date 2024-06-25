package com.blissstock.mappingSite.service;

import com.blissstock.mappingSite.dto.TeacherReviewDTO;
import com.blissstock.mappingSite.exceptions.CourseNotFoundException;

public interface TeacherReviewService {
  public void addReview(TeacherReviewDTO trReviewDTO, Long courseId, Long userId) throws CourseNotFoundException;
  //public UserRegisterDTO getUserByID(Long id);
}
