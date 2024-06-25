package com.blissstock.mappingSite.controller;

import java.util.ArrayList;
import java.util.List;

import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.model.BreadcrumbLists;
import com.blissstock.mappingSite.service.UserSessionService;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompleteScreenController {

    @Autowired
    UserSessionService userSessionService;

    // private static final Logger logger =
    // LoggerFactory.getLogger(CompleteScreenController.class);

    @RequestMapping("/card")
    public String CardSample() {
        return "card";
    }

    @RequestMapping("/admin/register/complete")
    public String AdminRegisterComplete(Model model) {
        String header3 = "Admin Register Complete";
        String header5 = "Congratulation!";
        String paragraph = "Successfully Registered A new Admin";
        model.addAttribute("header3", header3);
        model.addAttribute("header5", header5);
        model.addAttribute("paragraph", paragraph);
        // List<String> breadcrumbList = new ArrayList<>();
        // breadcrumbList.add("Admin");
        // breadcrumbList.add("Register");
        // breadcrumbList.add("Complete");
        // model.addAttribute("breadcrumbList", breadcrumbList);
        List<BreadcrumbLists> breadcrumbList = new ArrayList<>();
        breadcrumbList.add(new BreadcrumbLists("Email", "/check_email/register/admin", ""));
        breadcrumbList.add(new BreadcrumbLists("Registration", "", "back2"));
        breadcrumbList.add(new BreadcrumbLists("Confirmation", "", "back"));
        model.addAttribute("Complete", "Complete");
        model.addAttribute("breadcrumbList", breadcrumbList);
        String nav_type = "fragments/guestnav";
        model.addAttribute("nav_type", nav_type);
        return "CM0001_CompleteScreen";
    }

    // TODO Change NavBars
    @RequestMapping("/studentAccount/register/complete")
    public String StudentRegisterComplete(Model model) {
        String header3 = "Check Your Email";
        String header5 = "Congratulation!";
        String paragraph = "Thank you very much for your registration. You are completed in registration process. ";
        String header4 = "Please check your email to start using your account.";
        model.addAttribute("header3", header3);
        model.addAttribute("header5", header5);
        model.addAttribute("paragraph", paragraph);
        model.addAttribute("header4", header4);

        model.addAttribute("buttonName", "Go to Top Page");
        // List<String> breadcrumbList = new ArrayList<>();
        // breadcrumbList.add("Student");
        // breadcrumbList.add("Register");
        // breadcrumbList.add("Complete");
        // model.addAttribute("breadcrumbList", breadcrumbList);
        List<BreadcrumbLists> breadcrumbList = new ArrayList<>();
        breadcrumbList.add(new BreadcrumbLists("Email", "/check_email/register/student", ""));
        breadcrumbList.add(new BreadcrumbLists("Registration", "", "back2"));
        breadcrumbList.add(new BreadcrumbLists("Confirmation", "", "back"));
        model.addAttribute("Complete", "Complete");
        model.addAttribute("breadcrumbList", breadcrumbList);
        String nav_type = "fragments/guestnav";
        model.addAttribute("nav_type", nav_type);
        return "CM0001_CompleteScreen";
    }

    @RequestMapping("/admin/student-list/register/complete")
    public String StudentRegisterCompleteByAdmin(Model model) {
        String header3 = "Check Your Email";
        String header5 = "Congratulation!";
        String paragraph = "Thank you very much for your registration. You are completed in registration process.";
        String header4 = "Please check your email to start using your account.";
        model.addAttribute("header3", header3);
        model.addAttribute("header5", header5);
        model.addAttribute("paragraph", paragraph);
        model.addAttribute("header4", header4);

        // List<String> breadcrumbList = new ArrayList<>();
        // breadcrumbList.add("Admin");
        // breadcrumbList.add("StudentList");
        // breadcrumbList.add("Register");
        // breadcrumbList.add("Complete");
        // model.addAttribute("breadcrumbList", breadcrumbList);
        List<BreadcrumbLists> breadcrumbList = new ArrayList<>();
        model.addAttribute("backButton", "/admin/student-list");
        breadcrumbList.add(new BreadcrumbLists("Top", "/admin/top/", ""));
        breadcrumbList.add(new BreadcrumbLists("Student List", "/admin/student-list", ""));
        breadcrumbList.add(new BreadcrumbLists("Email", "/check_email/register/student", ""));
        breadcrumbList.add(new BreadcrumbLists("Registration", "", "back2"));
        breadcrumbList.add(new BreadcrumbLists("Confirmation", "", "back"));
        model.addAttribute("Complete", "Complete");
        model.addAttribute("breadcrumbList", breadcrumbList);
        String nav_type = "fragments/adminnav";
        model.addAttribute("nav_type", nav_type);
        return "CM0001_CompleteScreen";
    }

    @RequestMapping("/teacherAccount/register/complete")
    public String TeacherRegisterComplete(Model model) {

        String header3 = "Please Check Your Email";
        String paragraph = "Thank you very much for your registration at Pyinnyar Subuu website.";

        model.addAttribute("header3", header3);
        model.addAttribute("paragraph", paragraph);

        // model.addAttribute("buttonName", "Go to Top Page");

        // List<String> breadcrumbList = new ArrayList<>();
        // breadcrumbList.add("Teacher");
        // breadcrumbList.add("Register");
        // breadcrumbList.add("Complete");
        // model.addAttribute("breadcrumbList", breadcrumbList);
        List<BreadcrumbLists> breadcrumbList = new ArrayList<>();
        breadcrumbList.add(new BreadcrumbLists("Email", "/check_email/register/teacher", ""));
        breadcrumbList.add(new BreadcrumbLists("Registration", "", "back2"));
        breadcrumbList.add(new BreadcrumbLists("Confirmation", "", "back"));
        model.addAttribute("Complete", "Complete");
        model.addAttribute("breadcrumbList", breadcrumbList);
        String nav_type = "fragments/guestnav";
        model.addAttribute("nav_type", nav_type);
        return "CM0001_CompleteScreen";
    }

    @RequestMapping("/review/complete")
    public String ReviewComplete(Model model) {
        String header3 = "Review Complete";
        String header5 = "Congratulation!";
        String paragraph = "Thank you for using our services! We have received your feedback.";
        model.addAttribute("header3", header3);
        model.addAttribute("header5", header5);
        model.addAttribute("paragraph", paragraph);
        // List<String> breadcrumbList = new ArrayList<>();
        // breadcrumbList.add("Review");
        // breadcrumbList.add("Complete");
        // model.addAttribute("breadcrumbList", breadcrumbList);
        UserRole role = userSessionService.getRole();
        if (role == UserRole.STUDENT) {
            String nav_type = "fragments/student-nav";
            model.addAttribute("nav_type", nav_type);

            List<BreadcrumbLists> breadcrumbList = new ArrayList<>();
            model.addAttribute("backButton", "/student/my-course");

            breadcrumbList.add(new BreadcrumbLists("My Course", "/student/my-course", ""));
            breadcrumbList.add(new BreadcrumbLists("Course Details", "", "../"));

            breadcrumbList.add(new BreadcrumbLists("Review", "", "../"));
            breadcrumbList.add(new BreadcrumbLists("Confirm", "", "../"));
            model.addAttribute("Complete", "Complete");

            model.addAttribute("breadcrumbList", breadcrumbList);
        } else {

            String nav_type = "fragments/teacher-nav";
            model.addAttribute("nav_type", nav_type);

            List<BreadcrumbLists> breadcrumbList = new ArrayList<>();
            breadcrumbList.add(new BreadcrumbLists("My Course", "/teacher/my-course", ""));
            breadcrumbList.add(new BreadcrumbLists("Course Details", "", ".../"));
            breadcrumbList.add(new BreadcrumbLists("Review", "", "../"));
            breadcrumbList.add(new BreadcrumbLists("Confirm", "", "../"));
            model.addAttribute("Complete", "Complete");
            model.addAttribute("breadcrumbList", breadcrumbList);
        }

        return "CM0001_CompleteScreen";
    }

    @RequestMapping("/admin/review/complete")
    public String ReviewCompleteByAdmin(Model model) {
        String header3 = "Review Complete";
        String header5 = "Thank you for your feedback!";
        String paragraph = "Thank you for using our services! We have received your feedback.";
        model.addAttribute("header3", header3);
        model.addAttribute("header5", header5);
        model.addAttribute("paragraph", paragraph);
        // List<String> breadcrumbList = new ArrayList<>();
        // breadcrumbList.add("Admin");
        // breadcrumbList.add("Review");
        // breadcrumbList.add("Complete");
        // model.addAttribute("breadcrumbList", breadcrumbList);
        List<BreadcrumbLists> breadcrumbList = new ArrayList<>();
        model.addAttribute("backButton", "/guest/explore");
        breadcrumbList.add(new BreadcrumbLists("Top", "/admin/top/", ""));
        breadcrumbList.add(new BreadcrumbLists("Courses", "/guest/explore", ""));
        breadcrumbList.add(new BreadcrumbLists("Course Details", "", "../"));
        breadcrumbList.add(new BreadcrumbLists("Review", "", "../"));
        breadcrumbList.add(new BreadcrumbLists("Confirm", "", "../"));
        model.addAttribute("Complete", "Complete");
        model.addAttribute("breadcrumbList", breadcrumbList);
        String nav_type = "fragments/adminnav";
        model.addAttribute("nav_type", nav_type);
        return "CM0001_CompleteScreen";
    }

    @RequestMapping("/payment/complete")
    public String PaymentComplete(Model model) {
        String header3 = "Payment Complete";
        String header5 = "Acknowledgement!";
        String paragraph = "Thank you for using our services! Your payment has been successful.";
        model.addAttribute("header3", header3);
        model.addAttribute("header5", header5);
        model.addAttribute("paragraph", paragraph);
        // List<String> breadcrumbList = new ArrayList<>();
        // breadcrumbList.add("Payment");
        // breadcrumbList.add("Complete");
        // model.addAttribute("breadcrumbList", breadcrumbList);
        List<BreadcrumbLists> breadcrumbList = new ArrayList<>();
        model.addAttribute("backButton", "/student/my-course");
        breadcrumbList.add(new BreadcrumbLists("My Course", "/student/my-course", ""));
        breadcrumbList.add(new BreadcrumbLists("Course Details", "", "../"));
        breadcrumbList.add(new BreadcrumbLists("Payment", "", "../"));
        breadcrumbList.add(new BreadcrumbLists("Confirm", "", "../"));
        model.addAttribute("Complete", "Complete");
        model.addAttribute("breadcrumbList", breadcrumbList);

        String nav_type = "fragments/usernav";
        model.addAttribute("nav_type", nav_type);
        return "CM0001_CompleteScreen";
    }

    @RequestMapping("/leave/complete")
    public String TakeALeaveComplete(Model model) {
        String header3 = "Requesting a Leave Complete";
        String header5 = "Acknowledgement!";
        String paragraph = "Thank you for using our services! Your request for taking a leave have been received.";
        model.addAttribute("header3", header3);
        model.addAttribute("header5", header5);
        model.addAttribute("paragraph", paragraph);
        // List<BreadcrumbLists> breadcrumbList = new ArrayList<>();

        // model.addAttribute("breadcrumbList", breadcrumbList);
        UserRole role = userSessionService.getRole();
        if (role == UserRole.STUDENT) {
            String nav_type = "fragments/student-nav";
            model.addAttribute("nav_type", nav_type);

            List<BreadcrumbLists> breadcrumbList = new ArrayList<>();
            model.addAttribute("backButton", "/student/my-course");
            breadcrumbList.add(new BreadcrumbLists("My Course", "/student/my-course", ""));
            breadcrumbList.add(new BreadcrumbLists("Course Details", "", "../"));
            breadcrumbList.add(new BreadcrumbLists("Absent", "", "../"));
            breadcrumbList.add(new BreadcrumbLists("Confirm", "", "../"));
            model.addAttribute("Complete", "Complete");
            model.addAttribute("breadcrumbList", breadcrumbList);
        } else if (role == UserRole.TEACHER) {
            String nav_type = "fragments/teacher-nav";
            model.addAttribute("nav_type", nav_type);

            List<BreadcrumbLists> breadcrumbList = new ArrayList<>();
            model.addAttribute("backButton", "/teacher/my-course");
            breadcrumbList.add(new BreadcrumbLists("My Course", "/teacher/my-course", ""));
            breadcrumbList.add(new BreadcrumbLists("Course Details", "/teacher/course-details/", ""));
            breadcrumbList.add(new BreadcrumbLists("Absent", "", "back2"));
            breadcrumbList.add(new BreadcrumbLists("Confirm", "", "back"));
            model.addAttribute("Complete", "Complete");
            model.addAttribute("breadcrumbList", breadcrumbList);
        } else {

            String nav_type = "fragments/adminnav";
            model.addAttribute("nav_type", nav_type);

            List<BreadcrumbLists> breadcrumbList = new ArrayList<>();
            model.addAttribute("backButton", "/admin/top/");
            breadcrumbList.add(new BreadcrumbLists("Top", "/admin/top/", ""));
            breadcrumbList.add(new BreadcrumbLists("User List", "/admin/user-list/", ""));
            breadcrumbList.add(new BreadcrumbLists("Absent", "", "back2"));
            breadcrumbList.add(new BreadcrumbLists("Confirm", "", "back"));
            model.addAttribute("Complete", "Complete");
            model.addAttribute("breadcrumbList", breadcrumbList);
        }
        return "CM0001_CompleteScreen";
    }

    @RequestMapping("/teacher/course-upload/complete")
    public String UploadCourseComplete(Model model) {
        String header3 = "Course Upload Complete";
        String header5 = "Acknowledgement!";
        String paragraph = "Course upload has been successful.";
        model.addAttribute("header3", header3);
        model.addAttribute("header5", header5);
        model.addAttribute("paragraph", paragraph);
        model.addAttribute("backButton", "/teacher/my-course");
        List<BreadcrumbLists> breadcrumbList = new ArrayList<>();

        breadcrumbList.add(new BreadcrumbLists("My Course", "/teacher/my-course", ""));
        breadcrumbList.add(new BreadcrumbLists("Course Registration", "", "../"));
        breadcrumbList.add(new BreadcrumbLists("Confirmation", "", "../"));
        // breadcrumbList.add(new BreadcrumbLists("Complete","","active"));
        model.addAttribute("Complete", "Complete");
        model.addAttribute("breadcrumbList", breadcrumbList);
        String nav_type = "fragments/teacher-nav";
        model.addAttribute("nav_type", nav_type);
        return "CM0001_CompleteScreen";
    }

    @RequestMapping("/admin/course-upload/complete")
    public String UploadCourseCompleteByAdmin(Model model) {
        String header3 = "Course Upload Complete";
        String header5 = "Acknowledgement!";
        String paragraph = "Course upload has been successful.";
        model.addAttribute("header3", header3);
        model.addAttribute("header5", header5);
        model.addAttribute("paragraph", paragraph);
        model.addAttribute("backButton", "/admin/top/");
        List<BreadcrumbLists> breadcrumbList = new ArrayList<>();
        breadcrumbList.add(new BreadcrumbLists("Top", "/admin/top/", ""));
        breadcrumbList.add(new BreadcrumbLists("Teacher List", "/admin/teacher-list", ""));
        breadcrumbList.add(new BreadcrumbLists("Course Registration", "", "back2"));
        breadcrumbList.add(new BreadcrumbLists("Confirmation", "", "back"));
        // breadcrumbList.add(new BreadcrumbLists("Complete","","active"));
        model.addAttribute("breadcrumbList", breadcrumbList);
        String nav_type = "fragments/adminnav";
        model.addAttribute("nav_type", nav_type);
        model.addAttribute("Complete", "Complete");
        return "CM0001_CompleteScreen";
    }

    @RequestMapping("/teacher/course-edit/complete")
    public String EditCourseComplete(Model model) {
        String header3 = "Course Edit Complete";
        String header5 = "Acknowledgement!";
        String paragraph = "Course edit has been successful.";
        model.addAttribute("header3", header3);
        model.addAttribute("header5", header5);
        model.addAttribute("paragraph", paragraph);
        model.addAttribute("backButton", "/teacher/my-course");
        List<BreadcrumbLists> breadcrumbList = new ArrayList<>();
        breadcrumbList.add(new BreadcrumbLists("My Course", "/teacher/my-course", ""));
        breadcrumbList.add(new BreadcrumbLists("Course Edit", "", "../"));
        breadcrumbList.add(new BreadcrumbLists("Confirmation", "", "../"));
        // breadcrumbList.add(new BreadcrumbLists("Complete","","active"));
        model.addAttribute("Complete", "Complete");
        model.addAttribute("breadcrumbList", breadcrumbList);
        String nav_type = "fragments/teacher-nav";
        model.addAttribute("nav_type", nav_type);
        return "CM0001_CompleteScreen";
    }

    @RequestMapping("/admin/course-edit/complete")
    public String EditCourseCompleteByAdmin(Model model) {
        String header3 = "Course Edit Complete";
        String header5 = "Acknowledgement!";
        String paragraph = "Course edit has been successful.";
        model.addAttribute("header3", header3);
        model.addAttribute("header5", header5);
        model.addAttribute("paragraph", paragraph);
        model.addAttribute("backButton", "/guest/explore");
        List<BreadcrumbLists> breadcrumbList = new ArrayList<>();
        breadcrumbList.add(new BreadcrumbLists("Top", "/admin/top/", ""));
        breadcrumbList.add(new BreadcrumbLists("Teacher List", "/admin/teacher-list", ""));
        breadcrumbList.add(new BreadcrumbLists("Course Edit", "", "../"));
        breadcrumbList.add(new BreadcrumbLists("Confirmation", "", "../"));
        // breadcrumbList.add(new BreadcrumbLists("Complete","","active"));
        model.addAttribute("breadcrumbList", breadcrumbList);
        String nav_type = "fragments/adminnav";
        model.addAttribute("nav_type", nav_type);
        model.addAttribute("Complete", "Complete");
        return "CM0001_CompleteScreen";
    }

    @RequestMapping("/passwordChangeSuccess")
    public String PasswordChange(Model model) {
        String header3 = "Password Change Complete";
        String header5 = "";
        String paragraph = "PLease login with new password to continue";
        model.addAttribute("header3", header3);
        model.addAttribute("header5", header5);
        model.addAttribute("paragraph", paragraph);
        List<String> breadcrumbList = new ArrayList<>();
        breadcrumbList.add(" ");
        breadcrumbList.add("Password Change");
        breadcrumbList.add("Complete");
        model.addAttribute("breadcrumbList", breadcrumbList);
        String nav_type = "fragments/usernav";
        model.addAttribute("nav_type", nav_type);
        return "CM0001_CompleteScreen";
    }
    // @GetMapping("/complete")
    // public String completebox(){
    // return "CM0001_Complete";
    // }

    // @GetMapping("/complete")
    // public String getCondition(@RequestParam(required = false) String
    // confirmClick, Model model) {
    // model.addAttribute("confirm", confirmClick);
    // return "confirm/CM0001_Complete";
    // }

    // @GetMapping("/complete")
    // public String completeDirect(Model model) {
    // model.addAttribute("complete", new Greeting());
    // return "redirect:complete#registercomplete";
    // }

}
