package com.blissstock.mappingSite.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.JoinCourseUser;
import com.blissstock.mappingSite.entity.Review;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.repository.CourseInfoRepository;
import com.blissstock.mappingSite.repository.UserRepository;
import com.blissstock.mappingSite.service.UserService;
import com.blissstock.mappingSite.service.UserSessionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewListController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewListController.class);

    @Autowired
    UserSessionService userSessionService;

    @Autowired
    UserService userService;

    @Autowired
    CourseInfoRepository courseInfoRepo;

    @Autowired
    UserRepository userRepo;

    // get student review
    @Valid
    @GetMapping(value = { "/teacher/review-list/course_id/{courseId}",
            "/student/review-list/course_id/{courseId}",
            "/admin/review-list/course_id/{courseId}/student_id/{id}"
    })
    private String getReviewList(@PathVariable Long courseId, @PathVariable(name = "id", required = false) Long id,
            Model model) {
        Long userId = id == null ? userSessionService.getUserAccount().getAccountId() : id;

        CourseInfo courseInfo = courseInfoRepo.findById(courseId).orElse(null);
        String trName = courseInfo.getUserInfo().getUserName();
        model.addAttribute("trName", trName);
        UserInfo user = userRepo.findById(userId).orElse(null);
        // Display course name
        String courseName = courseInfo.getCourseName();
        model.addAttribute("courseName", courseName);

        // Get review list and teacher name of course
        List<JoinCourseUser> joinList = courseInfo.getJoin();
        List<Review> reviewList = new ArrayList<Review>();
        for (JoinCourseUser join : joinList) {
            reviewList.addAll(join.getReview());
            UserInfo joinUser = join.getUserInfo();
            if (joinUser.getUserAccount().getAccountId().equals(userId)) {
                if (joinUser.getUserAccount().getRole().equals("ROLE_STUDENT")) {
                    model.addAttribute("stuRegistered", true);
                }
            }

        }

        // Display course reviews
        // List<Review> reviews=courseInfo.getReview();
        List<Review> courseReviewList = new ArrayList<Review>();
        for (Review courseReview : reviewList) {
            if (courseReview.getReviewType() == 0) {
                courseReviewList.add(courseReview);
                model.addAttribute("courseReviewList", courseReviewList);
            }
        }
        boolean courseReviewListEmpty = courseReviewList.isEmpty();
        boolean courseReveiwListNotEmpty = !courseReviewListEmpty;
        logger.info("The boolean of courseReviewListEmpty is {}", courseReviewListEmpty);
        model.addAttribute("courseReviewListEmpty", courseReviewListEmpty);
        model.addAttribute("courseReviewListNotEmpty", courseReveiwListNotEmpty);

        // calculate average star rating for the course
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

        // Display student reviews
        List<JoinCourseUser> joinUserList = user.getJoin();
        List<Review> stuReviews = new ArrayList<Review>();
        for (JoinCourseUser join : joinUserList) {
            if (join.getCourseInfo().getCourseId().equals(courseId)) {
                stuReviews.addAll(join.getReview());
            }
        }
        List<Review> studentReviewList = new ArrayList<Review>();
        for (Review studentReview : stuReviews) {
            if (studentReview.getStar() == 0) {
                studentReviewList.add(studentReview);
                model.addAttribute("stuReviews", studentReviewList);
            }
        }

        return "CM0009_ReviewList";
    }

    @GetMapping(value = "/guest/review-list/course_id/{courseId}")
    public String guestReviewList(@PathVariable Long courseId, Model model) {
        CourseInfo courseInfo = courseInfoRepo.findById(courseId).orElse(null);
        String trName = courseInfo.getUserInfo().getUserName();
        model.addAttribute("trName", trName);

        // Display course name
        String courseName = courseInfo.getCourseName();
        model.addAttribute("courseName", courseName);

        // Get review list and teacher name of course
        List<JoinCourseUser> joinList = courseInfo.getJoin();
        List<Review> reviewList = new ArrayList<Review>();
        for (JoinCourseUser join : joinList) {
            reviewList.addAll(join.getReview());
            UserInfo joinUser = join.getUserInfo();

        }

        // Display course reviews
        // List<Review> reviews=courseInfo.getReview();
        List<Review> courseReviewList = new ArrayList<Review>();
        for (Review courseReview : reviewList) {
            if (courseReview.getReviewType() == 0) {
                courseReviewList.add(courseReview);
                model.addAttribute("courseReviewList", courseReviewList);
            }
        }
        boolean courseReviewListEmpty = courseReviewList.isEmpty();
        boolean courseReveiwListNotEmpty = !courseReviewListEmpty;
        logger.info("The boolean of courseReviewListEmpty is {}", courseReviewListEmpty);
        model.addAttribute("courseReviewListEmpty", courseReviewListEmpty);
        model.addAttribute("courseReviewListNotEmpty", courseReveiwListNotEmpty);

        // calculate average star rating for the course
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

        // Display student reviews

        return "CM0009_ReviewList";
    }

}
