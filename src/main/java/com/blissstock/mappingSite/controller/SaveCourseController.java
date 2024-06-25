package com.blissstock.mappingSite.controller;

import javax.servlet.http.HttpServletRequest;

import com.blissstock.mappingSite.service.CourseSessionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SaveCourseController {

    @Autowired
    CourseSessionService courseSessionService;

    private static final Logger logger = LoggerFactory.getLogger(SaveCourseController.class);
    
    @PostMapping("/guest/save/course-id/{courseId}")
    public String saveCourse(@PathVariable long courseId, HttpServletRequest request){
        logger.info("Post Request");
        courseSessionService.saveCourse(request, courseId);
        return "redirect:/login";
    }

    // @GetMapping("/guest/get/saved")
    // public String getCourse( HttpServletRequest request){
    //     logger.info("Get Request");
    //     long courseId = -1;
    //     try{
    //         courseId = (long) request.getSession().getAttribute("courseId");
    //         request.getSession().removeAttribute("courseId");
    //     }catch(NullPointerException e){
    //         e.printStackTrace();
            
    //     }
    //     logger.info("courseId: {}",courseId);
        
    //     return "redirect:/login";
    // }
    
}
