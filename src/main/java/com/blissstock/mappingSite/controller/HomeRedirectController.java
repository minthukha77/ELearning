package com.blissstock.mappingSite.controller;

import javax.servlet.http.HttpServletRequest;

import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.exceptions.CourseNotFoundException;
import com.blissstock.mappingSite.service.CourseSessionService;
import com.blissstock.mappingSite.service.UserSessionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeRedirectController {

  @Autowired
  UserSessionService userSessionService;

  @Autowired
  CourseSessionService courseSessionService;

  private static final Logger logger = LoggerFactory.getLogger(HomeRedirectController.class);

  @RequestMapping("/home")
  public String redirectHome(Model model, HttpServletRequest request) {
    UserRole userRole= userSessionService.getRole();
    if(userRole == null){
        //redirect to 
        return "redirect:/";
    }
    if(userRole == UserRole.ADMIN || userRole == UserRole.SUPER_ADMIN){
        return "redirect:/admin/top/";
    }
    if(userRole == UserRole.STUDENT ){
       try {
        long courseId = courseSessionService.getCourse(request);
        return "redirect:guest/course-detail/"+courseId;
      } catch (CourseNotFoundException e) {
        logger.debug("No course is stored in session");
      }
       return "redirect:student/my-course";
    }
    if(userRole == UserRole.TEACHER){
      return "redirect:/teacher/my-course";
    }
    return "redirect:/";
  }
}
