package com.blissstock.mappingSite.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.blissstock.mappingSite.entity.Syllabus;
import com.blissstock.mappingSite.exceptions.CourseNotFoundException;
import com.blissstock.mappingSite.exceptions.InsuffientPermssionException;
import com.blissstock.mappingSite.service.SyllabusService;
import com.blissstock.mappingSite.service.UserSessionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CourseSyllabusController {

  @Autowired
  SyllabusService syllabusService;

  @Autowired
  UserSessionService userSessionService;

  private static Logger logger = LoggerFactory.getLogger(
      CourseSyllabusController.class);

  @ExceptionHandler(CourseNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> handleResourceNotFoundException() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
  }

  @GetMapping(path = {
      "/teacher/course_syllabus/course_id/{id}",
      "/admin/course_syllabus/course_id/{id}",
  })
  public String contextView(
      Model model,
      @PathVariable("id") long id,
      HttpServletRequest httpServletRequest)
      throws CourseNotFoundException {
    // For the purpose of display
    logger.info("user requested syllabus of course_id: {}", id);

    //Sort by title
    List<Syllabus> syllabusList = syllabusService.getAllSyllabus(id).stream()
        .sorted((p1, p2) -> p1.getTitle().compareTo(p2.getTitle())).collect(Collectors.toList());

    model.addAttribute(
        "deleteAction",
        httpServletRequest.getRequestURL().toString() + "/delete");
    model.addAttribute("syllabusList", syllabusList);

    model.addAttribute("postAction", httpServletRequest.getRequestURL());

    return "course_context";
  }

  // syllabus update
  @GetMapping(path = {
    "/syllabus_details/course_id/{id}"
})
public String syllabusDetails(
    Model model,
    @PathVariable("id") long id,
    HttpServletRequest httpServletRequest)
    throws CourseNotFoundException {
  // For the purpose of display
  logger.info("user requested syllabus of course_id: {}", id);

  //Sort by title
  List<Syllabus> syllabusList = syllabusService.getAllSyllabus(id).stream()
      .sorted((p1, p2) -> p1.getTitle().compareTo(p2.getTitle())).collect(Collectors.toList());

  // model.addAttribute(
  //     "deleteAction",
  //     httpServletRequest.getRequestURL().toString() + "/delete");
  model.addAttribute("syllabusList", syllabusList);

  // model.addAttribute("postAction", httpServletRequest.getRequestURL());

  return "syllabus_details";
}

  @PostMapping(path = {
      "/teacher/course_syllabus/course_id/{id}",
      "/admin/course_syllabus/course_id/{id}",
  })
  public String addSyllabus(
      Model model,
      @ModelAttribute Syllabus syllabus,
      @PathVariable(name = "id") Long id,
      HttpServletRequest httpServletRequest) {

    logger.info("user added syllabus {}", syllabus);

    try {
      // syllabusService.deleteSyllabus(id);
      syllabusService.addSyllabus(syllabus, id);
    } catch (InsuffientPermssionException e) {
      //
    } catch (Exception e) {
      //
    }

    /*
     * if(userRole==UserRole.ADMIN){
     * 
     * return "redirect:/teacher/course_syllabus/"+id+"/";
     * }else if(userRole == UserRole.TEACHER){
     * return "redirect:/teacher/course_syllabus/"+id+"/";
     * }
     */

    return "redirect:" + httpServletRequest.getRequestURI();
  }

  @DeleteMapping(path = {
      "/teacher/course_syllabus/course_id/{id}",
      "/admin/course_syllabus/course_id/{id}",
  })
  public ResponseEntity<Object> deleteSyllabus(
      Model model,
      Long id,
      HttpServletRequest httpServletRequest) {

    try {
      syllabusService.deleteSyllabus(id);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.OK).body("operation success");
    }

    /*
     * if(userRole==UserRole.ADMIN){
     * 
     * return "redirect:/teacher/course_syllabus/"+id+"/";
     * }else if(userRole == UserRole.TEACHER){
     * return "redirect:/teacher/course_syllabus/"+id+"/";
     * }
     */

    return ResponseEntity.status(HttpStatus.OK).body("operation success");
  }
}
