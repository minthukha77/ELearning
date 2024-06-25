package com.blissstock.mappingSite.service;

import com.blissstock.mappingSite.dto.StudentReviewDTO;
import com.blissstock.mappingSite.exceptions.CourseNotFoundException;

public interface StudentReviewService {
  public void addReview(StudentReviewDTO stuReviewDTO, Long courseId, Long uid) throws CourseNotFoundException;
  //public UserRegisterDTO getUserByID(Long id);
}
