package com.blissstock.mappingSite.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.blissstock.mappingSite.dto.JoinCourseDTO;
import com.blissstock.mappingSite.dto.UserRegisterDTO;
import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.JoinCourseUser;
import com.blissstock.mappingSite.entity.PaymentForTeacher;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.repository.CourseInfoRepository;
import com.blissstock.mappingSite.repository.UserInfoRepository;
import com.blissstock.mappingSite.service.JoinCourseService;
import com.blissstock.mappingSite.service.PaymentForTeacherService;
import com.blissstock.mappingSite.service.UserSessionService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EnrollStudentController {

    private static final Logger logger = LoggerFactory.getLogger(
            EnrollStudentController.class);

    @Autowired
    UserSessionService userSessionService;

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private JoinCourseService joinCourseService;

    @Autowired
    private CourseInfoRepository courseRepo;

    @Autowired
    private PaymentForTeacherService paymentForTeacherService;

    // @Autowired
    // private CourseTimeRepository courseTimeRepo;

    @GetMapping(value = { "/admin/enrollStudent/course/{id}/{status}", "/admin/enrollStudent/course/{id}" })
    private String enrollStudent(@PathVariable(name = "id", required = true) Long id,
            @PathVariable(name = "status", required = false) Optional<String> status, Model model) {
        logger.info("Get Method");
        UserRole userRole = userSessionService.getRole();
        
        if (userRole.equals(UserRole.SUPER_ADMIN) || userRole.equals(UserRole.ADMIN)) {
            if (id != null) {
                try {
                    // http://localhost:8080/admin/enrollStudent/course/50001
                    Optional<CourseInfo> courseInfo = courseRepo.findById(id);
                    if (courseInfo.isPresent()) {
                        CourseInfo course = courseInfo.get();

                        model.addAttribute("courseName", course.getCourseName());
                        // todo find teacher name
                        // course.getUserInfo();
                        model.addAttribute("teachername", "Teacher");
                        model.addAttribute("level", course.getLevel());
                        model.addAttribute("CourseType", course.getClassType());
                        List<UserInfo> userInfo = userInfoRepository.findStudentsToEnroll(id);

                        // System.out.println(userInfo.get(0).getUserName());
                        // //System.out.println(userInfo.toString());
                        List<UserRegisterDTO> userRegisterDTO = UserRegisterDTO.fromUserInfoList(userInfo);
                        // System.out.println(userRegisterDTO.toString());
                        model.addAttribute("isCourseFound", "true");
                        model.addAttribute("students", userRegisterDTO);

                    } else {
                        logger.info("Course not found");
                        model.addAttribute("isCourseFound", "false");
                        model.addAttribute("status", "error");
                        model.addAttribute("errorMsg", "Course with id : " + id + " is not found. ");

                    }
                } catch (Exception e) {
                    logger.info(e.toString());
                }

            }
            if (status.isPresent()) {
                String state = status.get();
                if (state.equals("UserExists")) {
                    model.addAttribute("status", "error");
                    model.addAttribute("errorMsg", "User has already been enrolled");
                }
                if (state.equals("success")) {
                    model.addAttribute("status", "success");
                    model.addAttribute("Msg", "User has been enrolled Successfully");
                }
            }
            return "AD0002_EnrollStudent";
        } else {
            return "redirect:/error/404";
        }

    }

    @GetMapping(value = { "/admin/enrollStudent/course/{cid}/enroll/{uid}",
            "/admin/enrollStudent/course/{cid}/success/enroll/{uid}",
            "/admin/enrollStudent/course/{cid}/UserExists/enroll/{uid}" })
    public String enorllStudent(Model model, @PathVariable(name = "cid", required = false) Long cid,
            @PathVariable(name = "uid", required = false) Long uid) {
        // System.out.println("uid and cid is :" + uid + " " + cid);

        UserRole userRole = userSessionService.getRole();
        

        // admin enroll course limit
        CourseInfo courseInfo = courseRepo.findById(cid).get();
        Integer maxStudent = courseInfo.getMaxStu();
        List<UserInfo> studentList = new ArrayList<>();
        for (JoinCourseUser joinCourseUser : courseInfo.getJoin()) {
            if (joinCourseUser.getUserInfo().getUserAccount().getRole().equals(UserRole.STUDENT.getValue()))
                studentList.add(joinCourseUser.getUserInfo());
        }
        Integer stuListSize = studentList.size();
        Integer availableStuList;
        try {
            availableStuList = maxStudent - stuListSize;
        } catch (NullPointerException e) {
            availableStuList = 0;
        }

        // TODO
        if (availableStuList <= 0) {
            return "redirect:/error/404";
        }

        if (userRole.equals(UserRole.SUPER_ADMIN) || userRole.equals(UserRole.ADMIN)) {
            JoinCourseDTO joinCourseDTO = new JoinCourseDTO();
            joinCourseDTO.setUid(uid);
            joinCourseDTO.setCourseId(cid);

            try {
                joinCourseService.enrollStudent(joinCourseDTO);
            } catch (Exception e) {
                logger.info(e.toString());

                return "redirect:/admin/enrollStudent/course/" + cid + "/UserExists";
            }

            List<PaymentForTeacher> paymentList = paymentForTeacherService.getPaymentForTeacherByCourseId(cid);
            
            for (PaymentForTeacher payment : paymentList) {

                // perform necessary updates on payment entity
                if (payment.getCalculateDateFrom() != null && payment.getStatus().equals("PENDING")) {
                    int noOfStudent = stuListSize + 1;
                    double courseFee = payment.getCourseInfo().getFees();
                    double totalAmount = courseFee * noOfStudent;
                    double totalAmountTenPercent = totalAmount * 0.90 ;
                    payment.setNoOfEnrollPerson(noOfStudent); 
                    payment.setPaymentAmount(totalAmount);
                    payment.setPaymentAmountPercentage(totalAmountTenPercent);
                    paymentForTeacherService.savePaymentForTeacher(payment);

                    
                }
                else {
                    System.out.println("Payment Update not needed.");
                }
            
                
                
            }

            

            return "redirect:/admin/enrollStudent/course/" + cid + "/success";            

        } else {
            return "redirect:/error/404";
        }        
    }    

}
