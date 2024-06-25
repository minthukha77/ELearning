package com.blissstock.mappingSite.controller;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import com.blissstock.mappingSite.dto.JoinCourseDTO;
import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.CourseTime;
import com.blissstock.mappingSite.entity.JoinCourseUser;
import com.blissstock.mappingSite.entity.PaymentForTeacher;
import com.blissstock.mappingSite.entity.Review;
import com.blissstock.mappingSite.entity.Syllabus;
import com.blissstock.mappingSite.entity.Test;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.enums.PaymentStatus;
import com.blissstock.mappingSite.enums.ClassType;
import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.exceptions.CourseNotFoundException;
import com.blissstock.mappingSite.model.FileInfo;
import com.blissstock.mappingSite.repository.CourseInfoRepository;
import com.blissstock.mappingSite.repository.CourseTimeRepository;
import com.blissstock.mappingSite.repository.JoinCourseUserRepository;
import com.blissstock.mappingSite.repository.SyllabusRepository;
import com.blissstock.mappingSite.repository.UserAccountRepository;
import com.blissstock.mappingSite.repository.UserInfoRepository;
import com.blissstock.mappingSite.repository.UserRepository;
import com.blissstock.mappingSite.service.CourseService;
import com.blissstock.mappingSite.service.JoinCourseService;
import com.blissstock.mappingSite.service.StorageService;
import com.blissstock.mappingSite.service.UserService;
import com.blissstock.mappingSite.service.UserSessionService;
import com.blissstock.mappingSite.service.MailService;
import com.blissstock.mappingSite.service.PaymentForTeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class CourseDetailsController {

    private static final Logger logger = LoggerFactory.getLogger(CourseDetailsController.class);
    @Autowired
    MailService mailService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSessionService userSessionService;

    @Autowired
    UserService userService;

    @Autowired
    CourseInfoRepository courseInfoRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    JoinCourseUserRepository joinCourseUserRepository;

    @Autowired
    SyllabusRepository syllabusRepository;

    @Autowired
    JoinCourseService joinCourseService;

    @Autowired
    CourseService courseService;

    @Autowired
    StorageService storageService;

    @Autowired
    CourseTimeRepository courseTimeRepository;

    @Autowired
    private PaymentForTeacherService paymentForTeacherService;

    @GetMapping(value = { "/student/course-details/{courseId}", "/teacher/course-details/{courseId}",
            "/admin/course-details/{courseId}", "/guest/course-detail/{courseId}" })
    private String getCourseDetails(@PathVariable Long courseId, Model model) {
        Long userId;


        // Get course by ID
        CourseInfo courseInfo = courseInfoRepository.findById(courseId).get();
        FileInfo fileInfo = storageService.loadCoursePhoto(courseInfo);

        // if profile is not found set as place holder
        if (fileInfo == null) {
            courseInfo.setCoursePhoto("https://via.placeholder.com/150");
        } else {

            courseInfo.setCoursePhoto(fileInfo.getUrl());
        }

        model.addAttribute("courseInfo", courseInfo);

        // get classlink
        String classLink = courseInfo.getClassLink();
        model.addAttribute("classLink", classLink);

        // get test links
        List<Test> testList = courseInfo.getTest();
        model.addAttribute("testList", testList);
        model.addAttribute("testListSize", testList.size());

        // isCourseApprove
        boolean courseNotApprove = courseInfo.getIsCourseApproved() == false;
        model.addAttribute("courseNotApprove", courseNotApprove);
        logger.info("The requested course approve boolean is {} ", courseNotApprove);

        // compare course start date to find course can be enrolled

        // Get trname and course name
        String trName = courseInfo.getUserInfo().getUserName();
        model.addAttribute("trName", trName);
        String courseName = courseInfo.getCourseName();
        model.addAttribute("courseName", courseName);

        // Get joinlist of the course

        // here
        List<JoinCourseUser> joinList = courseInfo.getJoin();
        List<Review> reviewList = new ArrayList<Review>();
        for (JoinCourseUser join : joinList) {
            reviewList.addAll(join.getReview());
        }

        // Get reveiwlist
        List<Review> courseReviewList = new ArrayList<Review>();
        List<FileInfo> userProfileList = new ArrayList<FileInfo>();
        for (Review courseReview : reviewList) {
            if (courseReview.getReviewType() == 0) {
                UserInfo tempUserInfo = courseReview.getJoin().getUserInfo();
                FileInfo profilePic = storageService.loadProfileAsFileInfo(tempUserInfo);
                tempUserInfo.setPhoto(profilePic.getUrl());
                courseReview.getJoin().setUserInfo(tempUserInfo);

                courseReviewList.add(courseReview);
                model.addAttribute("courseReviewList", courseReviewList);
            }
        }

        // first 3 reviews
        if (courseReviewList.size() >= 3) {
            List<Review> threeCourseReviewList = new ArrayList<>();
            threeCourseReviewList.add(courseReviewList.get(0));
            threeCourseReviewList.add(courseReviewList.get(1));
            threeCourseReviewList.add(courseReviewList.get(2));
            model.addAttribute("courseReviewList", threeCourseReviewList);
        } else {
            model.addAttribute("courseReviewList", courseReviewList);
        }

        logger.info("The review list is {}", courseReviewList.size());

        // reviewlist empty or not
        boolean courseReviewListEmpty = courseReviewList.isEmpty();
        boolean courseReveiwListNotEmpty = !courseReviewListEmpty;
        logger.info("The boolean of courseReviewListEmpty is {}", courseReviewListEmpty);
        model.addAttribute("courseReviewListEmpty", courseReviewListEmpty);
        model.addAttribute("courseReviewListNotEmpty", courseReveiwListNotEmpty);

        // average rating
        int total_stars = 0;
        int numCourseReviewList = courseReviewList.size();
        for (Review review : courseReviewList) {
            total_stars += review.getStar();
        }
        int average = 0;
        double averageDouble = 0;
        try {
            average = (int) total_stars / numCourseReviewList;
        } catch (ArithmeticException e) {
            average = 0;
        }
        try {
            averageDouble = (double) total_stars / numCourseReviewList;
        } catch (ArithmeticException e) {
            averageDouble = 0;
        }
        String averageFloat = String.format("%.2f", averageDouble);
        model.addAttribute("average", average);
        model.addAttribute("averageFloat", averageFloat);

        String classType = courseInfo.getClassType();
        boolean isLiveClass = classType.toUpperCase().equals("LIVE");
        model.addAttribute("isLiveClass", isLiveClass);

        if (classType.toUpperCase().equals(ClassType.LIVE.getValue())) {
            LocalDateTime now = LocalDateTime.now();
            Instant currentDate = now.toInstant(ZoneOffset.UTC);
            Instant startDate = courseInfo.getStartDate().toInstant();

            logger.info("Is current date is before start date {}", currentDate.isBefore(startDate));
            model.addAttribute("isCourseDateValid", currentDate.isBefore(startDate));
        }

        // Get Time segments for course

        List<CourseTime> courseTimeList = courseTimeRepository.searchTimeByCourseID(courseId);
        model.addAttribute("courseTimeList", courseTimeList);

        // Get syllabus
        List<Syllabus> syllabusList = courseInfo.getSyllabus();
        int syllabusSize = syllabusList.size();
        // model.addAttribute("syllabusList", syllabusList);
        model.addAttribute("syllabusSize", syllabusSize);

        // first syllabus
        try {
            if (syllabusSize > 0) {

                Syllabus firstSyllabus = syllabusList.get(0);

                // String firstContent = firstSyllabus.getContent().get(0).getContent();
                model.addAttribute("firstSyllabus", firstSyllabus);
                // model.addAttribute("firstContent", firstContent);
                syllabusList.remove(0);
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.info(e.toString());
        }

        model.addAttribute("syllabusList", syllabusList);

        // Get the remaining number of students who can join course
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
        Integer currentAttending = studentList.size();
        model.addAttribute("currentAttending", currentAttending);
        model.addAttribute("availableStuList", availableStuList);

        if (userSessionService.getRole() == UserRole.TEACHER) {
            userId = userSessionService.getUserAccount().getAccountId();
            logger.info("The user id is {} ", userId);
            Long registered = courseInfo.getUserInfo().getUid();
            logger.info("The teacher id is {} ", userId);
            boolean teacherRegistered = false;
            if (userId.equals(registered)) {
                teacherRegistered = true;
            }
            model.addAttribute("teacherRegistered", teacherRegistered);
            model.addAttribute("teacher", "TEACHER");
            model.addAttribute("classlink", courseInfo.getClassLink());

        } else if (userSessionService.getRole() == UserRole.STUDENT) {
            userId = userSessionService.getUserAccount().getAccountId();
            UserInfo user = userRepository.findById(userId).orElse(null);
            boolean studentRegistered = true;

            List<JoinCourseUser> join = joinCourseService.getJoinCourseUser(userId, courseId);
            studentRegistered = join != null && !join.isEmpty();

            // payment status
            boolean paymentComplete = false;
            for (JoinCourseUser jcu : join) {
                // logging
                logger.info("the uid of joinlist is {} and session id is {}", jcu.getUserInfo().getUid(), userId);
                // logger.info("The status of joinlist of outter scope is
                // {}",jcu.getPaymentReceive().getPaymentStatus());

                // comparing two long values reference safe
                if (String.valueOf(jcu.getUserInfo().getUid()).equals(String.valueOf(userId))) {
                    // logger.info("The status of joinlist of scope id compare is
                    // {}",jcu.getPaymentReceive().getPaymentStatus());
                    if (jcu.getPaymentReceive() == null) {
                        paymentComplete = false;
                    } else if (jcu.getPaymentReceive().getPaymentStatus().equals(PaymentStatus.COMPLETE.getValue())) {
                        // logger.info("The status of joinlist of scope status compare is
                        // {}",jcu.getPaymentReceive().getPaymentStatus());
                        paymentComplete = true;
                    }
                }
            }
            model.addAttribute("paymentComplete", paymentComplete);
            logger.info("the boolean value for paymentComplete is {}", paymentComplete);

            logger.info("The boolean value for student registered is {} ", studentRegistered);
            boolean studentNotRegistered = !studentRegistered;
            model.addAttribute("studentNotRegistered", studentNotRegistered);
            model.addAttribute("studentRegistered", studentRegistered);
            model.addAttribute("student", "STUDENT");
            model.addAttribute("userId", userId);
            // Get stuReviews
            List<JoinCourseUser> joinUserList = user.getJoin();
            List<Review> stuReviews = new ArrayList<Review>();
            for (JoinCourseUser joinlist : joinUserList) {
                if (joinlist.getCourseInfo().getCourseId().equals(courseId)) {
                    stuReviews.addAll(joinlist.getReview());
                }
            }
            List<Review> studentReviewList = new ArrayList<Review>();
            for (Review studentReview : stuReviews) {
                if (studentReview.getStar() == 0) {
                    studentReviewList.add(studentReview);
                    model.addAttribute("stuReviews", studentReviewList);
                }
            }

        }

        else if (userSessionService.getRole() == UserRole.ADMIN
                || userSessionService.getRole() == UserRole.SUPER_ADMIN) {

            model.addAttribute("admin", "ADMIN");
            model.addAttribute("classlink", courseInfo.getClassLink());
            model.addAttribute("studentList", studentList);

        } else if (userSessionService.getRole() == UserRole.GUEST_USER) {
            model.addAttribute("guest", "GUEST");
        }

        return "CM0003_CourseDetails";
    }

    @PostMapping(value = { "/teacher/course-details/insert-class-link", "/admin/course-details/insert-class-link" })
    private String courseDetailsLink(@ModelAttribute("class-link") String classLink,
            @ModelAttribute("courseId") Long courseId, @ModelAttribute("roleLink") String roleLink, Model model) {
        CourseInfo courseInfo = courseInfoRepository.findById(courseId).get();
        courseInfo.setClassLink(classLink);
        courseInfoRepository.save(courseInfo);
        return "redirect:/" + roleLink + "/course-details/" + courseId;
    }

    // ### Temporarily disble to fix some changes in 'Test' entity class ###
    // @PostMapping("/admin/course-details/insert-test-link")
    // private String courseTestLink(@ModelAttribute("test-link") String testLink,
    //         @ModelAttribute("courseId") Long courseId, Model model) {
    //     CourseInfo courseInfo = courseInfoRepository.findById(courseId).get();
    //     List<Test> testList = courseInfo.getTest();
    //     logger.info("The size of test list is {}" + testList.size());
    //     Test test = new Test();
    //     test.setTestLink(testLink);
    //     test.setCourseInfo(courseInfo);
    //     testList.add(test);
    //     courseInfo.setTest(testList);
    //     courseInfoRepository.save(courseInfo);

    //     return "redirect:/admin/course-details/" + courseId;
    // }

    // ### Temporarily disble to fix some changes in 'Test' entity class ###

    // @GetMapping("/admin/hello")
    // private String helloWorld(){
    // Long myId = (long) 1;
    // CourseInfo courseInfo = courseInfoRepository.findById(myId).get();
    // System.out.println("BlaBla" + courseInfo.getTest().size());
    // return "hello";
    // }

    @DeleteMapping("/admin/course-details/delete/")
    public ResponseEntity<Object> deleteCourse(
            Model model,
            Long courseId,
            HttpServletRequest httpServletRequest) {
        logger.info("DELETE Request for course {}", courseId);
        try {
            // UserInfo userInfo = userService.getUserInfoByID(uid);
            CourseInfo courseInfo = courseInfoRepository.findById(courseId).get();
            if (courseInfo == null) {
                throw new CourseNotFoundException();
            }
            courseService.deleteCourseInfo(courseInfo);
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course Not Found");
        } catch (ObjectOptimisticLockingFailureException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }

        return ResponseEntity.status(HttpStatus.OK).body("operation success");

    }

    @PostMapping("/admin/course-details/verify/")
    public ResponseEntity<Object> verifyCourse(
            Model model,
            Long courseId,
            HttpServletRequest httpServletRequest) {
        logger.info("VERIFY Request for course {}", courseId);
        try {
            // UserInfo userInfo = userService.getUserInfoByID(uid);
            CourseInfo courseInfo = courseInfoRepository.findById(courseId).get();
            if (courseInfo == null) {
                throw new CourseNotFoundException();
            }
            courseService.verifyCourseInfo(courseInfo);
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course Not Found");
        } catch (ObjectOptimisticLockingFailureException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }

        return ResponseEntity.status(HttpStatus.OK).body("operation success");

    }

    @RequestMapping("/student/enroll/{courseId}/{userId}")

    public String enrollStudent(HttpServletRequest request, @PathVariable Long courseId, @PathVariable Long userId,
            Model model) {
        logger.info("Request"); 
        JoinCourseDTO joinCourseDTO = new JoinCourseDTO();
        joinCourseDTO.setUid(userId);
        joinCourseDTO.setCourseId(courseId);
        try {

            if (joinCourseService.enrollStudent(joinCourseDTO) == null) {
                logger.info("null user");
                return "redirect:/student/course-details/" + courseId + "/?error";
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return "redirect:/student/course-details/" + courseId + "/?error";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "redirect:/student/course-details/" + courseId + "/?error";
        }

        CourseInfo courseInfo = courseInfoRepository.findById(courseId).get();
        List<UserInfo> studentList = new ArrayList<>();
        for (JoinCourseUser joinCourseUser : courseInfo.getJoin()) {
            if (joinCourseUser.getUserInfo().getUserAccount().getRole().equals(UserRole.STUDENT.getValue()))
                studentList.add(joinCourseUser.getUserInfo());
        }
        
        Integer stuListSize = studentList.size();
        System.out.println("Student size is : "+ stuListSize);

        List<PaymentForTeacher> paymentList = paymentForTeacherService.getPaymentForTeacherByCourseId(courseId);
            
            for (PaymentForTeacher payment : paymentList) {

                
                if (payment.getCourseInfo().getStartDate() == null && payment.getStatus().equals("PENDING")) {
                    int studentFromDatabase = payment.getNoOfEnrollPerson();
                    int noOfStudent = studentFromDatabase + 1;
                    
                    LocalDate enrollDate = LocalDate.now();
                    
                    LocalDate firstDayOfMonth = enrollDate.withDayOfMonth(1);
                    Date firstDay = Date.from(firstDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    
                    LocalDate lastDayOfMonth = enrollDate.withDayOfMonth(enrollDate.lengthOfMonth());
                    Date lastDay = Date.from(lastDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    LocalDate paymentDate = enrollDate.plusMonths(1).withDayOfMonth(5);
                    if (paymentDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                        paymentDate = paymentDate.plusDays(2);
                    } else if (paymentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        paymentDate = paymentDate.plusDays(1);
                    }
                    

                    double courseFee = payment.getCourseInfo().getFees();
                    double totalAmount = courseFee * noOfStudent;
                    double totalAmountTenPercent = totalAmount * 0.90 ;

                    payment.setNoOfEnrollPerson(noOfStudent);
                    payment.setPaymentDate(paymentDate);
                    payment.setCalculateDateFrom(firstDay); 
                    payment.setCalculateDateTo(lastDay);
                    payment.setPaymentAmount(totalAmount);
                    payment.setPaymentAmountPercentage(totalAmountTenPercent);
                    paymentForTeacherService.savePaymentForTeacher(payment);

                    
                }
                else if (payment.getCourseInfo().getStartDate() == null && payment.getStatus().equals("COMPLETE")) {

                    PaymentForTeacher paymentForTeacher = new PaymentForTeacher();
                    paymentForTeacher.setCourseInfo(courseInfo);
                    paymentForTeacher.setNoOfEnrollPerson(1);
                    
                    
                    LocalDate enrollDate = LocalDate.now();
                    
                    LocalDate firstDayOfMonth = enrollDate.withDayOfMonth(1);
                    Date firstDay = Date.from(firstDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    
                    LocalDate lastDayOfMonth = enrollDate.withDayOfMonth(enrollDate.lengthOfMonth());
                    Date lastDay = Date.from(lastDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    LocalDate paymentDate = enrollDate.plusMonths(1).withDayOfMonth(5);
                    if (paymentDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                        paymentDate = paymentDate.plusDays(2);
                    } else if (paymentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        paymentDate = paymentDate.plusDays(1);
                    }

                    double courseFee = paymentForTeacher.getCourseInfo().getFees();
                    double totalAmount = courseFee * 1;
                    double totalAmountTenPercent = totalAmount * 0.90 ;
                    String paymentStatus = "PENDING";  
                    paymentForTeacher.setCalculateDateFrom(firstDay);
                    paymentForTeacher.setCalculateDateTo(lastDay);               
                    paymentForTeacher.setCourseFee(courseFee);
                    paymentForTeacher.setPaymentDate(paymentDate);
                    paymentForTeacher.setPaymentAmount(totalAmount);
                    paymentForTeacher.setPaymentAmountPercentage(totalAmountTenPercent);
                    paymentForTeacher.setStatus(paymentStatus);
                    paymentForTeacher.setPaymentVerify(false);
                    paymentForTeacherService.savePaymentForTeacher(paymentForTeacher);                     
                    
                    
                }

                else if (payment.getCalculateDateFrom() != null && payment.getStatus().equals("PENDING")) {
                    int noOfStudent = stuListSize;
                    double courseFee = payment.getCourseInfo().getFees();
                    double totalAmount = courseFee * noOfStudent;
                    double totalAmountTenPercent = totalAmount * 0.90 ;
                    payment.setNoOfEnrollPerson(noOfStudent); 
                    payment.setPaymentAmount(totalAmount);
                    payment.setPaymentAmountPercentage(totalAmountTenPercent);
                    paymentForTeacherService.savePaymentForTeacher(payment);



                }

            }

        new Thread(new Runnable() {
            public void run() {
                try {
                    UserInfo userInfo = userService.getUserInfoByID(userId);
                    CourseInfo courseInfo = courseInfoRepository.findById(courseId).get();
                    String appUrl = request.getServerName() + // "localhost"
                            ":" +
                            request.getServerPort(); // "8080"
                    // mailService.sendVerificationMail(
                    // savedUserInfo.getUserAccount(),
                    // appUrl);

                    mailService.SendAdminNewStudentEnroll(userInfo, courseId, courseInfo);
                    mailService.SendStudentEnrollCourse(userInfo, courseInfo);
                    mailService.SendTeacherNewStudentEnroll(userInfo, courseInfo);

                } catch (Exception e) {
                    logger.info(e.toString());
                }
            }
        }).start();

        return "redirect:/student/course-details/" + courseId;
    }

}