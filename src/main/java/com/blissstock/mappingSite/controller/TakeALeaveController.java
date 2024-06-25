package com.blissstock.mappingSite.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.JoinCourseUser;
import com.blissstock.mappingSite.entity.LeaveInfo;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.repository.CourseInfoRepository;
// import com.blissstock.mappingSite.entity.LeaveTest;
// import com.blissstock.mappingSite.repository.CourseRepository;
import com.blissstock.mappingSite.repository.JoinCourseUserRepository;
// import com.blissstock.mappingSite.repository.CourseTestingRepository;
import com.blissstock.mappingSite.repository.LeaveInfoRepository;
import com.blissstock.mappingSite.repository.UserRepository;
import com.blissstock.mappingSite.service.UserSessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TakeALeaveController {
  /*
   * @RequestMapping(value="TakeALeave")
   * public String TakeALeave(@RequestParam(value = "record_date",
   * defaultValue="Japanese N3") String course, @RequestParam(value =
   * "user_name",defaultValue = "Nani") String name, Model model) {
   * model.addAttribute("course",course);
   * model.addAttribute("name", name);
   * return "LeaveScreen";
   * }
   */

  // @RequestMapping("/TakeALeave")
  // public String TakeALeave() {

  // return "LeaveScreen";
  // }

  @Autowired
  UserRepository userRepo;

  // @Autowired
  // CourseTestingRepository courseTestRepo;

  // @Autowired
  // CourseRepository courseRepo;

  @Autowired
  LeaveInfoRepository leaveRepo;

  @Autowired
  JoinCourseUserRepository joinRepo;

  @Autowired
  CourseInfoRepository courseInfoRepo;

  @Autowired
  UserSessionService userSessionService;

  @Valid
  @GetMapping(value = { "/student/leave/{courseId}", "/teacher/leave/{courseId}", "/admin/leave/{courseId}/{userId}" })
  private String getTakeALeaveForm(@PathVariable Long courseId, @PathVariable(required = false) Long userId,
      Model model) {
    CourseInfo courseInfo = courseInfoRepo.findByCourseID(courseId);
    String course = courseInfo.getCourseName();
    UserInfo userInfo = courseInfo.getUserInfo();
    String name = userInfo.getUserName();

    LeaveInfo leaveInfo = new LeaveInfo();

    model.addAttribute("course", course);
    model.addAttribute("name", name);
    model.addAttribute("leave", leaveInfo);
    UserRole role = userSessionService.getRole();

    if (role == UserRole.STUDENT) {
      model.addAttribute("postAction", "/student/leave/" + courseId + "/confirm");
      List<String> breadcrumbList = new ArrayList<>();
      breadcrumbList.add("My Course");
      breadcrumbList.add("Course Detail");
      // breadcrumbList.add("Take A Leave");
      model.addAttribute("Absent", "Absent");
      model.addAttribute("breadcrumbList", breadcrumbList);
      String nav_type = "fragments/student-nav";
      model.addAttribute("nav_type", nav_type);

    } else if (role == UserRole.TEACHER) {
      model.addAttribute("postAction", "/teacher/leave/" + courseId + "/confirm");
      List<String> breadcrumbList = new ArrayList<>();
      breadcrumbList.add("My Course");
      breadcrumbList.add("Course Detail");
      // breadcrumbList.add("Take A Leave");
      model.addAttribute("Absent", "Absent");
      model.addAttribute("breadcrumbList", breadcrumbList);
      String nav_type = "fragments/teacher-nav";
      model.addAttribute("nav_type", nav_type);
    } else {

      model.addAttribute("postAction", "/admin/leave/" + courseId + "/" + userId + "/confirm");
      List<String> breadcrumbList = new ArrayList<>();
      breadcrumbList.add("Top");
      breadcrumbList.add("User List");
      // breadcrumbList.add("Take A Leave");
      model.addAttribute("Absent", "Absent");
      model.addAttribute("breadcrumbList", breadcrumbList);
      String nav_type = "fragments/adminnav";
      model.addAttribute("nav_type", nav_type);
    }

    return "ST0003_TakeALeaveScreen";
  }

  @PostMapping(value = { "/student/leave/{courseId}/confirm", "/teacher/leave/{courseId}/confirm",
      "/admin/leave/{courseId}/{userId}/confirm" })
  public String postCourseInfoForm(@Valid @ModelAttribute("leave") LeaveInfo leaveInfo, @PathVariable Long courseId,
      Model model, @PathVariable(required = false) Long userId) {
    // if(bindingResult.hasErrors()) {
    // return "AT00001_CourseRegisteration";
    // }
    // System.out.print("Take A Leave ID in confirm
    // screen:"+leaveInfo.getLeaveId());
    CourseInfo courseInfo = courseInfoRepo.findByCourseID(courseId);
    String course = courseInfo.getCourseName();
    UserInfo userInfo = courseInfo.getUserInfo();
    String name = userInfo.getUserName();

    model.addAttribute("course", course);
    model.addAttribute("name", name);
    UserRole role = userSessionService.getRole();

    if (role == UserRole.STUDENT) {
      model.addAttribute("postAction", "/student/leave/" + courseId + "/complete");
      model.addAttribute("previousAction", "/student/leave/" + courseId + "/confirm");
      List<String> breadcrumbList = new ArrayList<>();
      breadcrumbList.add("My Course");
      breadcrumbList.add("Course Detail");
      breadcrumbList.add("Take A Leave");
      model.addAttribute("Confirm", "Confirm");
      model.addAttribute("breadcrumbList", breadcrumbList);
      String nav_type = "fragments/student-nav";
      model.addAttribute("nav_type", nav_type);
    } else if (role == UserRole.TEACHER) {
      model.addAttribute("postAction", "/teacher/leave/" + courseId + "/complete");
      model.addAttribute("previousAction", "/teacher/leave/" + courseId + "/confirm");
      List<String> breadcrumbList = new ArrayList<>();
      breadcrumbList.add("My Course");
      breadcrumbList.add("Course Detail");
      breadcrumbList.add("Take A Leave");
      model.addAttribute("Confirm", "Confirm");
      model.addAttribute("breadcrumbList", breadcrumbList);
      String nav_type = "fragments/teacher-nav";
      model.addAttribute("nav_type", nav_type);
    } else {
      model.addAttribute("postAction", "/admin/leave/" + courseId + "/" + userId + "/complete");
      model.addAttribute("previousAction", "/admin/leave/" + courseId + "/" + userId + "/confirm");
      List<String> breadcrumbList = new ArrayList<>();
      breadcrumbList.add("Top");
      breadcrumbList.add("User List");
      breadcrumbList.add("Take A Leave");
      model.addAttribute("Confirm", "Confirm");
      model.addAttribute("breadcrumbList", breadcrumbList);
      String nav_type = "fragments/adminnav";
      model.addAttribute("nav_type", nav_type);
    }

    return "ST0004_LeaveConfirmScreen";
  }

  @PostMapping(value = { "/student/leave/{courseId}/complete", "/teacher/leave/{courseId}/complete",
      "/admin/leave/{courseId}/{userId}/complete" })
  public String saveLeaveRequestForm(Model model,
      @Valid @ModelAttribute("leave") LeaveInfo leaveInfo,
      @PathVariable Long courseId,
      @PathVariable(required = false) Long userId,
      @RequestParam(value = "action", required = true) String action,
      HttpServletRequest request) {

    // LeaveInfo saveLeave = new LeaveInfo(null, leaveInfo.getLeaveDate(),
    // leaveInfo.getLeaveStartTime(),
    // leaveInfo.getLeaveEndTime(),leaveInfo.getReason(),leaveInfo.getJoin());
    System.out.print("New Leave Request:" + leaveInfo.getLeaveDate());
    // LeaveInfo leave= leaveRepo.findById(courseId).orElse(null);
    // leaveRepo.save(leaveInfo);

    UserRole role = userSessionService.getRole();

    if (role == UserRole.ADMIN || role == UserRole.SUPER_ADMIN) {
      Long uid = userId;
      List<JoinCourseUser> joins = joinRepo.findByCourseUser(courseId, uid);
      for (JoinCourseUser join : joins) {
        leaveInfo.setJoin(join);
        leaveRepo.save(leaveInfo);
        joinRepo.save(join);

      }

    } else {
      Long uid = userSessionService.getUserAccount().getAccountId();
      System.out.print("Current User ID: " + uid);
      List<JoinCourseUser> joins = joinRepo.findByCourseUser(courseId, uid);
      for (JoinCourseUser join : joins) {
        leaveInfo.setJoin(join);
        leaveRepo.save(leaveInfo);
        joinRepo.save(join);

      }
    }

    // List<JoinCourseUser> joins=joinRepo.findByCourseUser(courseId, uid);
    // for(JoinCourseUser join:joins){
    // leaveInfo.setJoin(join);
    // leaveRepo.save(leaveInfo);
    // joinRepo.save(join);

    // }

    return "redirect:/leave/complete";

  }
}