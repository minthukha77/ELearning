package com.blissstock.mappingSite.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.JoinCourseUser;
import com.blissstock.mappingSite.entity.Test;
import com.blissstock.mappingSite.entity.TestQuestion;
import com.blissstock.mappingSite.entity.TestStudent;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.model.TestStudentWithMarkedCountModel;
import com.blissstock.mappingSite.repository.JoinCourseUserRepository;
import com.blissstock.mappingSite.repository.TestQuestionRepository;
import com.blissstock.mappingSite.repository.TestRepository;
import com.blissstock.mappingSite.repository.TestStudentAnswerRepository;
import com.blissstock.mappingSite.repository.TestStudentRepository;
import com.blissstock.mappingSite.repository.UserAccountRepository;
import com.blissstock.mappingSite.repository.UserInfoRepository;
import com.blissstock.mappingSite.service.UserSessionService;

import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class TestStudentController {
    private static Logger logger = LoggerFactory.getLogger(
            TestStudentController.class);

    @Autowired
    UserSessionService userSessionService;

    @Autowired
    TestStudentRepository testStudentRepository;

    @Autowired
    TestStudentAnswerRepository testStudentAnswerRepository;

    @Autowired
    TestRepository testRepository;

    @Autowired
    TestQuestionRepository testQuestionRepository;

    @Autowired
    JoinCourseUserRepository joinCourseUserRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Valid
    @GetMapping(value = { "/teacher/exam/{test_id}/examinee", "/admin/exam/{test_id}/examinee" })
    private String getTestStudent(@PathVariable Long test_id, Model model,
            @RequestParam(required = false) String name)
            throws ParseException {
        List<TestStudent> testStudents = new ArrayList<>();
        List<TestStudentWithMarkedCountModel> testStudentList = new ArrayList<>();
        int checked_students = 0;
        int total_free_questions = 0;
        if (name == null) {
            testStudents = testStudentRepository.getStudentByTest(test_id);
        } else {
            testStudents = testStudentRepository.findByNameandTestId(name, test_id);
        }
        total_free_questions = testQuestionRepository.getFreeAnswerCount(test_id);
        for (TestStudent testStudent : testStudents) {
            Integer answerCount = testStudentAnswerRepository.getCountStudentAnswerListByTestAndStudent(test_id,
                    testStudent.getUserInfo().getUid());
            if (answerCount == 0) {
                TestStudentWithMarkedCountModel testStudentWithMarkedCountModel = new TestStudentWithMarkedCountModel(
                        testStudent.getId(), testStudent.getTest(), testStudent.getUserInfo(), total_free_questions,
                        0);
                testStudentList.add(testStudentWithMarkedCountModel);
            } else {
                int uncheck_free_questions = testStudentAnswerRepository.getUnCheckAnswerCountByTestAndStudent(
                        test_id,
                        testStudent.getUserInfo().getUid());
                TestStudentWithMarkedCountModel testStudentWithMarkedCountModel = new TestStudentWithMarkedCountModel(
                        testStudent.getId(), testStudent.getTest(), testStudent.getUserInfo(), total_free_questions,
                        total_free_questions - uncheck_free_questions);
                if (uncheck_free_questions == 0) {
                    checked_students++;
                }
                testStudentList.add(testStudentWithMarkedCountModel);
            }
        }
        model.addAttribute("user_role", userSessionService.getRole());
        model.addAttribute("test_id", test_id);
        model.addAttribute("test_students", testStudentList);
        model.addAttribute("total_students", testStudents.size());
        model.addAttribute("check_students", checked_students);
        return "AT0005_TestStudentList.html";
    }

    @Valid
    @GetMapping(value = { "/teacher/get-student", "/admin/get-student" })
    private ResponseEntity getCustomStudent(@RequestParam(value = "name") String name)
            throws ParseException {
        List<UserInfo> testStudents = userInfoRepository.findByName(name);
        return ResponseEntity.ok(testStudents);
    }

    @Valid
    @GetMapping(value = { "/teacher/get-student-exam", "/admin/get-student-exam" })
    private ResponseEntity getCustomStudentExam(@RequestParam(value = "name") String name,
            @RequestParam(value = "test_id") Long test_id)
            throws ParseException {
        List<UserInfo> testStudents = userInfoRepository.findByNameandTestId(name, test_id);
        return ResponseEntity.ok(testStudents);
    }

    @Valid
    @PostMapping(value = { "/teacher/set-enrolled-examinee", "/admin/set-enrolled-examinee" })
    private String setEnrolledStudents(@RequestBody String testid)
            throws ParseException {
        JSONObject jsonObject = new JSONObject(testid);
        Long test_id = jsonObject.getLong("test_id");
        Test test = testRepository.getTestByID(test_id);
        if (test.getExam_status().equals("Exam Created")) {
            CourseInfo course = test.getCourseInfo();
            List<JoinCourseUser> enrolledList = joinCourseUserRepository.findByStudentByCourseID(course.getCourseId());
            for (JoinCourseUser student : enrolledList) {
                TestStudent checkStudent = testStudentRepository.getStudentByID(student.getUserInfo().getUid(),
                        test_id);
                if (checkStudent == null) {
                    TestStudent testStudent = new TestStudent(null, test, student.getUserInfo(), null);
                    testStudentRepository.save(testStudent);
                }
            }
        }
        return "AT0005_TestStudentList.html";
    }

    @Valid
    @PostMapping(value = { "/teacher/set-examinee", "/admin/set-examinee" })
    private String setCustomStudents(@RequestBody String testid)
            throws ParseException {
        JSONObject jsonObject = new JSONObject(testid);
        Long test_id = jsonObject.getLong("test_id");
        Long student_id = jsonObject.getLong("student_id");
        Test test = testRepository.getTestByID(test_id);
        UserInfo user = userInfoRepository.findStudentById(student_id);
        if (test.getExam_status().equals("Exam Created")) {
            TestStudent existingStudent = testStudentRepository.getStudentByID(student_id, test_id);
            if (existingStudent == null) {
                TestStudent testStudent = new TestStudent(null, test, user, null);
                testStudentRepository.save(testStudent);
            }
        }
        return "AT0005_TestStudentList.html";
    }

    private Long getUid() {
        Long uid = userSessionService.getUserAccount().getAccountId();
        return uid;
    }
}