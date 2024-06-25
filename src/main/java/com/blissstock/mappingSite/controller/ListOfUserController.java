package com.blissstock.mappingSite.controller;

import com.blissstock.mappingSite.model.AddAdmin;
import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.CourseTime;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.entity.JoinCourseUser;
import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.repository.CourseInfoRepository;
import com.blissstock.mappingSite.repository.UserAccountRepository;
import com.blissstock.mappingSite.repository.UserRepository;
import com.blissstock.mappingSite.repository.JoinCourseUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.ArrayList;
import java.util.List;

@Controller
public class ListOfUserController {


    private static final Logger logger = LoggerFactory.getLogger(ListOfUserController.class);
    
    @Autowired
    UserRepository userRepo;
    
    @Autowired
    UserAccountRepository userAccountRepo;
    
    @Autowired
    CourseInfoRepository courseInfoRepo;

    @Autowired
    JoinCourseUserRepository joinCourseUserRepo;
    
    long courseID = 50004;
    
    @RequestMapping("/admin/teacher-list")
    public String ListOfTeacher(Model model)
    {
        List<UserInfo> tAllRecord = userRepo.findByUserRoleI("ROLE_TEACHER");
        // System.out.println(tAllRecord);
        logger.info("Teacher List of Pyinnyar Subuu {}",tAllRecord);
        model.addAttribute("tAllTeacherList", tAllRecord);
        // List<String> breadcrumbList = new ArrayList<>();
        // breadcrumbList.add("Top");
        // breadcrumbList.add("Teacher List");
        // model.addAttribute("breadcrumbList",breadcrumbList);
        String nav_type = "fragments/adminnav";
        model.addAttribute("nav_type",nav_type);
        String accountStatus = "VERIFIED";
        model.addAttribute("accountStatus", accountStatus);
        return "AT0003_ListofTeachersScreen";
    }
    @RequestMapping(value = "/admin/student-list",method = RequestMethod.GET)
    public String ListOfStudent(Model model)
    {
        System.out.println("Student Console");
        // List<UserInfo> sAllRecord = userRepo.findAll();
        List<UserInfo> sAllRecord = userRepo.findByUserRoleI("ROLE_STUDENT");
        // System.out.println("Student List Console"+sAllRecord);
        
        logger.info("Student List of Pyinnyar Subuu {}",sAllRecord);

        model.addAttribute("sAllStudentList", sAllRecord);
        // System.out.println(sAllRecord);
        // List<UserAccount> uAllRecord = userAccountRepo.findByUserRoleU("Student");
        // model.addAttribute("UAllStudentList", uAllRecord);

        // List<String> breadcrumbList = new ArrayList<>();
        // breadcrumbList.add("Top");
        // breadcrumbList.add("Student List");
        // model.addAttribute("breadcrumbList",breadcrumbList);
        String nav_type = "fragments/adminnav";
        model.addAttribute("nav_type",nav_type);

        return "AT0003_ListofStudentsScreen";
    }
    
    @RequestMapping("/admin/admin-list")
    public String ListOfAdmin(Model model)
    {
        String adminRole = "ROLE_ADMIN";
        System.out.println(adminRole);
        AddAdmin newAdmin = new AddAdmin();
        
        List<UserAccount> aAllRecord = userAccountRepo.findByRole("ROLE_ADMIN");

        logger.info("Admin List of Pyinnyar Subuu {}",aAllRecord);


        
        model.addAttribute("aAllAdminList", aAllRecord);
        model.addAttribute("adminRegister", newAdmin);
        model.addAttribute("adminRole", adminRole);
        // System.out.println(servletContext.getContextPath());
        // System.out.println("Previous Path Info "+req.getRequestURL());
        // List<String> breadcrumbList = new ArrayList<>();
        // breadcrumbList.add("Top");
        // breadcrumbList.add("Admin List");
        // model.addAttribute("breadcrumbList",breadcrumbList);
        String nav_type = "fragments/adminnav";
        model.addAttribute("nav_type",nav_type);

        return "AT0003_ListofAdminsScreen";
    }

    
    // @RequestMapping(value = "/teacher/course-list/{courseId}/student-list",method = RequestMethod.GET)
    @RequestMapping(value = "/teacher/student-list/{courseId}",method = RequestMethod.GET)
    public String ListOfStudentByTeacher(@PathVariable Long courseId,Model model)
    {
        // Long courseId = (long) 50004;
        
        CourseInfo course = courseInfoRepo.findById(courseId).get();

        logger.info("Course ID {}","Course Info {}",courseId,course);

        List<JoinCourseUser> joinCourseUser = course.getJoin();
        // List<UserInfo> userList = new ArrayList<>();
        List<UserInfo> userList = new ArrayList<UserInfo>();
        // List<UserInfo> userList = joinCourseUser.getUserInfo();
        for(JoinCourseUser joinCU:joinCourseUser)
        {
            UserInfo userinfo = joinCU.getUserInfo();
            if(userinfo.getUserAccount().getRole().equals(UserRole.STUDENT.getValue()))
                userList.add(userinfo);
        }
        // List<JoinCourseUser> joinCourseUser = joinCourseUserRepo.findByCourseID(courseID);
        
        // System.out.println("Course Joined"+course.getUserInfo());
        // List<UserInfo> userList=course.getUserInfo();
        logger.info("Course ID {}","Student List {}",courseId,userList);

        System.out.println("User List"+userList);
        // List<UserInfo> stAllRecord = userRepo.findByCourseI();
        // System.out.println(stAllRecord);
        Integer maxStudent = course.getMaxStu();
        Integer currentStuList = userList.size();
        Integer availableStuList = maxStudent - currentStuList;
        model.addAttribute("availableStuList", availableStuList);

        UserInfo teacherInfo = course.getUserInfo();
        String teacherName = teacherInfo.getUserName();
        List<CourseTime> courseTimeList = course.getCourseTime();
        model.addAttribute("courseInfo", course);
        model.addAttribute("teacherName", teacherName);
        model.addAttribute("courseTimeList", courseTimeList);
        model.addAttribute("allStudentList", userList);
        model.addAttribute("postAction", "/teacher/course-details/" + courseId);
        
        List<UserInfo> tAllRecord = userRepo.findByUserRoleI("ROLE_TEACHER");
        System.out.println("Teacher List"+tAllRecord);

        // model.addAttribute("tAllTeacherList", tAllRecord);
        return "AT0003_ListofStudentsByT";
    }
}
