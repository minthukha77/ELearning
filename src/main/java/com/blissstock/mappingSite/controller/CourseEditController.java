package com.blissstock.mappingSite.controller;

import java.util.ArrayList;
import java.util.List;

import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.CourseTime;
import com.blissstock.mappingSite.enums.Days_of_the_week;
import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.exceptions.UnauthorizedFileAccessException;
import com.blissstock.mappingSite.model.FileInfo;
import com.blissstock.mappingSite.repository.CourseInfoRepository;
// import com.blissstock.mappingSite.repository.CourseRepository;
import com.blissstock.mappingSite.repository.CourseTimeRepository;
import com.blissstock.mappingSite.repository.UserInfoRepository;
import com.blissstock.mappingSite.service.CourseService;
import com.blissstock.mappingSite.service.StorageService;
import com.blissstock.mappingSite.service.StorageServiceImpl;
import com.blissstock.mappingSite.service.UserSessionServiceImpl;
import com.blissstock.mappingSite.utils.CheckUploadFileType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

// Test for git rules
@Controller

public class CourseEditController {
    private static final Logger logger = LoggerFactory.getLogger(CourseEditController.class);

    // @Autowired
    // private CourseRepository courseRepo;

    @Autowired
    private CourseInfoRepository courseInfoRepo;

    @Autowired
    private CourseTimeRepository courseTimeRepo;

    @Autowired
    private UserSessionServiceImpl userSessionService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    StorageService storageService;

    @Autowired
    private CourseService courseService;

    @Autowired
    public CourseEditController(StorageService storageService) {
        this.storageService = storageService;
    }

    private List<CourseTime> ctList = new ArrayList<>();

    @RequestMapping(value = { "/teacher/edit/{courseId}", "/admin/edit/course/{courseId}/{userId}" })
    private String editCourse(
            Model model,
            @PathVariable(required = false) Long userId,
            @PathVariable Long courseId) {
        logger.info("GET Requested");

        UserRole currentRole = userSessionService.getRole();
        if (currentRole == UserRole.TEACHER) {
            userId = userSessionService.getId();
        }

        CourseInfo course = courseInfoRepo.findByCourseIDUID(courseId, userId);
        logger.info("Get Requested {}", course);

        if (course != null) {
            // List<CourseTime> courseTimeList = new ArrayList<>();
            if (course.getClassType().toUpperCase().equals("LIVE")) {

                model.addAttribute("classActiveLive", true);

                List<CourseTime> courseTimeList = courseTimeRepo.searchTimeByCourseID(courseId);

                // fill the days from database
                List<CourseTime> sorted_list = new ArrayList<CourseTime>();
                for (int i = 0; i <= 6; i++) {

                    sorted_list.add(i, new CourseTime());
                }

                for (CourseTime courseTime : courseTimeList) {
                    if (courseTime.getCourseDays().equals(Days_of_the_week.Monday.getValue())) {
                        sorted_list.set(0, courseTime);
                    }
                    if (courseTime.getCourseDays().equals(Days_of_the_week.Tuesday.getValue())) {
                        sorted_list.set(1, courseTime);
                    }
                    if (courseTime.getCourseDays().equals(Days_of_the_week.Wednesday.getValue())) {
                        sorted_list.set(2, courseTime);
                    }
                    if (courseTime.getCourseDays().equals(Days_of_the_week.Thursday.getValue())) {
                        sorted_list.set(3, courseTime);
                    }
                    if (courseTime.getCourseDays().equals(Days_of_the_week.Friday.getValue())) {
                        sorted_list.set(4, courseTime);
                    }
                    if (courseTime.getCourseDays().equals(Days_of_the_week.Saturday.getValue())) {
                        sorted_list.set(5, courseTime);
                    }
                    if (courseTime.getCourseDays().equals(Days_of_the_week.Sunday.getValue())) {
                        sorted_list.set(6, courseTime);
                    }
                }

                model.addAttribute("CourseListHtml", courseService.getAllCourseCategory());
                model.addAttribute("courseTimeList", sorted_list);
                ctList = courseTimeList;
                // System.out.println("Heehee" + ctList);

            } else {
                List<CourseTime> sorted_list = new ArrayList<CourseTime>();
                for (int i = 0; i <= 6; i++) {

                    sorted_list.add(i, new CourseTime());
                }
                model.addAttribute("courseTimeList", sorted_list);
                model.addAttribute("classActiveVideo", true);
            }
            
            FileInfo cphoto = storageService.loadCoursePhoto(course);
            model.addAttribute("cphoto", cphoto);
            System.out.println("Request course photo "+ cphoto);
            model.addAttribute("course", course);

            UserRole role = userSessionService.getRole();
            if (role == UserRole.ADMIN || role == UserRole.SUPER_ADMIN) {
                // long uid = id;
                course.setUid(userId);
                logger.info("Teacher ID for update course {}", userId);
                // // model.addAttribute("teacherID", uid);
                // System.out.print("Admin registration : "+uid);
                List<String> breadcrumbList = new ArrayList<>();
                breadcrumbList.add("Top");
                breadcrumbList.add("Teacher List");
                breadcrumbList.add("Update Course");
                model.addAttribute("breadcrumbList", breadcrumbList);
                String nav_type = "fragments/adminnav";
                model.addAttribute("nav_type", nav_type);
            } else {
                long uid = userSessionService.getId();
                course.setUid(uid);
                logger.info("Teacher ID for update course {}", uid);
                // model.addAttribute("teacherID", uid);
                System.out.print("Teacher Registration" + uid);
                List<String> breadcrumbList = new ArrayList<>();
                breadcrumbList.add("My Course");
                breadcrumbList.add("Update Course");
                model.addAttribute("breadcrumbList", breadcrumbList);
                String nav_type = "fragments/teacher-nav";
                model.addAttribute("nav_type", nav_type);
            }

            if (role == UserRole.TEACHER) {
                model.addAttribute("postAction", "/teacher/edit/course/"+courseId+"/confirm");
                //model.addAttribute("photoPostAction", "/teacher/edit/"+courseId+"/photo-upload");
            } else {
                model.addAttribute("postAction", "/admin/edit/course/"+courseId +"/" +userId + "/confirm");
                //model.addAttribute("photoPostAction", "/admin/edit/"+courseId+"/photo-upload");
            }

            return "AT0001_EditCourse";
        } else {
            return "redirect:/";
        }

    }
    @PostMapping(value =  { "/teacher/edit/course/{courseId}/confirm", "/admin/edit/course/{courseId}/{userId}/confirm" }, params = "photoSubmit")
    private String uploadCoursePhoto( @PathVariable Long courseId,@RequestParam("course_pic") MultipartFile cphoto) {
        // course.setUserInfo(userInfoRepository.findById(userSessionService.getId()).get());
        CourseInfo course=courseInfoRepo.findById(courseId).orElse(null);

        logger.info("Post Requested");

        CourseInfo updateCourse = courseInfoRepo.findById(course.getCourseId()).get();
        logger.info("Get Requested {}", updateCourse);
        if (!cphoto.isEmpty() && CheckUploadFileType.checkType(cphoto)) {
            // get original photo name and generate a new file name
            String originalFileName = StringUtils.cleanPath(
                cphoto.getOriginalFilename());

            try {
              storageService.store(updateCourse.getCourseId(), cphoto, StorageServiceImpl.COURSE_PATH, true);
            } catch (UnauthorizedFileAccessException e) {
              e.printStackTrace();
            }
            // insert photo
            updateCourse.setCoursePhoto(originalFileName);
            courseInfoRepo.save(updateCourse);
    
            logger.info("profile photo {} stored", originalFileName);
            //return  "redirect:/teacher/course-registration";
          }
        
        courseInfoRepo.save(updateCourse);
        logger.info("Update Course for CourseID {}", updateCourse.getCourseId());

        UserRole role = userSessionService.getRole();

        if (role == UserRole.TEACHER) {
            return "redirect:/teacher/edit/"+courseId;
        } else {
            return "redirect:/admin/edit/course/"+courseId+"/"+userSessionService.getId();
        }
        // return "takealeave";
    }

    @PostMapping(value = {"/teacher/edit/course/{courseId}/confirm", "/admin/edit/course/{courseId}/{userId}/confirm" }, params = "submit")
    private String editCourseConfirm(@ModelAttribute("course") CourseInfo course,
            @RequestParam("course_pic") MultipartFile photo,
            @ModelAttribute("day0") String day0,
            @ModelAttribute("startTime0") String startTime0,
            @ModelAttribute("endTime0") String endTime0,
            @ModelAttribute("day1") String day1,
            @ModelAttribute("startTime1") String startTime1,
            @ModelAttribute("endTime1") String endTime1,
            @ModelAttribute("day2") String day2,
            @ModelAttribute("startTime2") String startTime2,
            @ModelAttribute("endTime2") String endTime2,
            @ModelAttribute("day3") String day3,
            @ModelAttribute("startTime3") String startTime3,
            @ModelAttribute("endTime3") String endTime3,
            @ModelAttribute("day4") String day4,
            @ModelAttribute("startTime4") String startTime4,
            @ModelAttribute("endTime4") String endTime4,
            @ModelAttribute("day5") String day5,
            @ModelAttribute("startTime5") String startTime5,
            @ModelAttribute("endTime5") String endTime5,
            @ModelAttribute("day6") String day6,
            @ModelAttribute("startTime6") String startTime6,
            @ModelAttribute("endTime6") String endTime6,
            @PathVariable(required = false) Long courseId,
            @PathVariable(required = false) Long userId,
            Model model) {
        logger.info("POST requested");

        System.out.print("Current Course ID : " + course.getCoursePhoto());

        List<CourseTime> courseTimeList = new ArrayList<>();
        CourseTime courseTime0 = new CourseTime();
        CourseTime courseTime1 = new CourseTime();
        CourseTime courseTime2 = new CourseTime();
        CourseTime courseTime3 = new CourseTime();
        CourseTime courseTime4 = new CourseTime();
        CourseTime courseTime5 = new CourseTime();
        CourseTime courseTime6 = new CourseTime();
        if (course.getClassType().toUpperCase().equals("LIVE")) {

            model.addAttribute("classActiveLive", true);

            if (!day0.equals("")) {
                courseTime0.setCourseDays(day0);
                courseTime0.setCourseStartTime(startTime0);
                courseTime0.setCourseEndTime(endTime0);
                courseTimeList.add(courseTime0);
            }
            if (!day1.equals("")) {
                courseTime1.setCourseDays(day1);
                courseTime1.setCourseStartTime(startTime1);
                courseTime1.setCourseEndTime(endTime1);
                courseTimeList.add(courseTime1);
            }
            if (!day2.equals("")) {
                courseTime2.setCourseDays(day2);
                courseTime2.setCourseStartTime(startTime2);
                courseTime2.setCourseEndTime(endTime2);
                courseTimeList.add(courseTime2);
            }
            if (!day3.equals("")) {
                courseTime3.setCourseDays(day3);
                courseTime3.setCourseStartTime(startTime3);
                courseTime3.setCourseEndTime(endTime3);
                courseTimeList.add(courseTime3);
            }
            if (!day4.equals("")) {
                courseTime4.setCourseDays(day4);
                courseTime4.setCourseStartTime(startTime4);
                courseTime4.setCourseEndTime(endTime4);
                courseTimeList.add(courseTime4);
            }
            if (!day5.equals("")) {
                courseTime5.setCourseDays(day5);
                courseTime5.setCourseStartTime(startTime5);
                courseTime5.setCourseEndTime(endTime5);
                courseTimeList.add(courseTime5);
            }
            if (!day6.equals("")) {
                courseTime6.setCourseDays(day6);
                courseTime6.setCourseStartTime(startTime6);
                courseTime6.setCourseEndTime(endTime6);
                courseTimeList.add(courseTime6);
            }

            model.addAttribute("courseTimeList", courseTimeList);
            ctList = courseTimeList;

        } else {
            model.addAttribute("classActiveVideo", true);
        }

        FileInfo cphoto = storageService.loadCoursePhoto(course);
        model.addAttribute("cphoto", cphoto);

        model.addAttribute("course", course);
        
        UserRole role = userSessionService.getRole();

        if (role == UserRole.TEACHER) {
            model.addAttribute("postAction", "/teacher/edit/complete");
            List<String> breadcrumbList = new ArrayList<>();
            breadcrumbList.add("My Course");
            breadcrumbList.add("Update Course");
            breadcrumbList.add("Confirm");
            model.addAttribute("breadcrumbList", breadcrumbList);
            String nav_type = "fragments/teacher-nav";
            model.addAttribute("nav_type", nav_type);
        } else {
            model.addAttribute("postAction", "/admin/edit/course/complete");
            List<String> breadcrumbList = new ArrayList<>();
            breadcrumbList.add("Top");
            breadcrumbList.add("Teacher List");
            breadcrumbList.add("Update Course");
            breadcrumbList.add("Confirm");
            model.addAttribute("breadcrumbList", breadcrumbList);
            String nav_type = "fragments/adminnav";
            model.addAttribute("nav_type", nav_type);
        }
        return "AT0002_EditCourseConfirm";
    }

    @PostMapping(value = { "/teacher/edit/complete", "/admin/edit/course/complete"})
    private String editCourseComplete(@ModelAttribute("course") CourseInfo course,@RequestParam("course_pic") MultipartFile cphoto) {
        // course.setUserInfo(userInfoRepository.findById(userSessionService.getId()).get());

        course.setUserInfo(userInfoRepository.findById(course.getUid()).get());
        System.out.print("Teacher id:" + course.getUid());
        System.out.print("Course photo" + cphoto);

        logger.info("Post Requested");

        // course.setIsCourseApproved(true); //was string "true"

        String classType = course.getClassType();

        // long cid = 44;
        // CourseInfo test = courseRepo.findById(cid).get();
        // System.out.print("Test Info:" +test);

        CourseInfo updateCourse = courseInfoRepo.findById(course.getCourseId()).get();
        logger.info("Get Requested {}", updateCourse);
          
        if (classType.toUpperCase().equals("LIVE")) {

            updateCourse.setMaxStu(course.getMaxStu());
            updateCourse.setStartDate(course.getStartDate());
            updateCourse.setEndDate(course.getEndDate());

            Long currentCourseID = updateCourse.getCourseId();

            System.out.print("Current ID " + course.getCourseId());

            List<CourseTime> courseTimeList = courseTimeRepo.searchTimeByCourseID(currentCourseID);
            // System.out.print("Time List:"+courseTimeList);
            // courseTimeRepo.searchTimeByCourseID(currentCourseID)!=null
            if (courseTimeList != null) {
                System.out.print("Course Time Not Null");
                // course.getCourseTime().remove(this);
                for (CourseTime delCourseTime : courseTimeList) {
                    courseTimeRepo.delete(delCourseTime);
                    logger.info("Delete Previous Course Time List for CourseID {}", currentCourseID);
                }
                // courseTimeRepo.deleteByCourseID(currentCourseID);
                // System.out.print("All Course Time have been deleted");
                updateCourse.setCourseTime(course.getCourseTime());
            } else {
                System.out.print("Course Time is null");
            }
        }

        updateCourse.setClassType(course.getClassType().toUpperCase());
        updateCourse.setCourseName(course.getCourseName());
        updateCourse.setCategory(course.getCategory());
        updateCourse.setLevel(course.getLevel());
        updateCourse.setFees(course.getFees());
        updateCourse.setAboutCourse(course.getAboutCourse());
        updateCourse.setPrerequisite(course.getPrerequisite());
        updateCourse.setClassLink(course.getClassLink());
        updateCourse.setIsCourseApproved(true);
        updateCourse.setUserInfo(course.getUserInfo());

        System.out.print(course.getClassType());

        // System.out.print("Previous Course Info:"+updateCourse);
        // // System.out.print("Before Update Course ID: "+courseId);

        // System.out.print("Updated Course Info:"+updateCourse);
        // System.out.print(course.getCourseId());

        // courseInfoRepo.updateVideoCourse(course.getCourseId(),course.getCourseName(),
        // course.getCategory(), course.getClassLink(), course.getLevel(),
        // course.getAboutCourse(), course.getPrerequisite(), course.getFees(),
        // course.isCourseApproved());
        // System.out.print(course);
        courseInfoRepo.save(updateCourse);
        logger.info("Update Course for CourseID {}", updateCourse.getCourseId());
        // System.out.println("HoeHoe" + ctList);
        for (CourseTime courseTime : ctList) {
            courseTime.setCourseInfo(updateCourse);
            // System.out.print("Course Time List :"+courseTime);
            courseTimeRepo.save(courseTime);
            logger.info("Update Course Time List for CourseID {}", updateCourse.getCourseId());
        }

        UserRole role = userSessionService.getRole();

        if (role == UserRole.TEACHER) {
            return "redirect:/teacher/course-edit/complete";
        } else {
            return "redirect:/admin/course-edit/complete";
        }
        // return "takealeave";
    }

    // @RequestMapping("/admin/course")
    // public String courseTest()
    // {
    // return "card";
    // }

}