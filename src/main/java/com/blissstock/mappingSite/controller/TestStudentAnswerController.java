package com.blissstock.mappingSite.controller;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.blissstock.mappingSite.entity.TestQuestion;
import com.blissstock.mappingSite.entity.TestQuestionCorrectAnswer;
import com.blissstock.mappingSite.entity.TestStudentAnswer;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.exceptions.UnauthorizedFileAccessException;
import com.blissstock.mappingSite.repository.TestQuestionCorrectAnswerRepositoy;
import com.blissstock.mappingSite.repository.TestQuestionRepository;
import com.blissstock.mappingSite.repository.TestRepository;
import com.blissstock.mappingSite.repository.TestStudentAnswerRepository;
import com.blissstock.mappingSite.repository.UserInfoRepository;
import com.blissstock.mappingSite.service.StorageService;
import com.blissstock.mappingSite.service.StorageServiceImpl;
import com.blissstock.mappingSite.service.UserSessionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.util.StringUtils;

@Controller
public class TestStudentAnswerController {
    @Autowired
    TestStudentAnswerRepository testStudentAnswerRepository;

    @Autowired
    UserSessionService userSessionService;

    @Autowired
    TestQuestionCorrectAnswerRepositoy testQuestionCorrectAnswerRepositoy;

    @Autowired
    TestQuestionRepository testQuestionRepository;

    @Autowired
    TestRepository testRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    StorageService storageService;

    @Valid
    @PostMapping(value = { "/student/submit-answer" })
    private ResponseEntity submitAnswer(@RequestParam(value = "test_id") Long test_id,
            @RequestParam(value = "question_id") Long question_id,
            @RequestParam(value = "student_answer") String student_answer,
            @RequestParam(value = "answer_type") String answer_type,
            @RequestParam(value = "answer_material", required = false) MultipartFile answer_material)
            throws JsonMappingException, JsonProcessingException, UnauthorizedFileAccessException {
        Long student_id = userSessionService.getUserAccount().getAccountId();
        UserInfo student = userInfoRepository.findStudentById(student_id);
        String answerStatus = "FALSE";
        Integer acquiredmarks = 0;

        TestQuestion question = testQuestionRepository.getQuestionByID(question_id);

        if (!answer_type.equals("FREE_ANSWER")) {
            TestQuestionCorrectAnswer questionAndCorrectAnswer = testQuestionCorrectAnswerRepositoy
                    .getCorrectAnswerByQuestion(question_id);

            JSONArray studentAnswersArray = new JSONArray(student_answer);
            JSONArray correctAnswersArray = new JSONArray(questionAndCorrectAnswer.getCorrectAnswer());
            for (var i = 0; i < correctAnswersArray.length(); i++) {
                JSONObject correctAnswer = correctAnswersArray.getJSONObject(i);
                for (var j = 0; j < studentAnswersArray.length(); j++) {
                    JSONObject studentAnswer = studentAnswersArray.getJSONObject(j);
                    int correct_answer = correctAnswer.getInt("answer");
                    int student_choice = studentAnswer.getInt("student_choice");
                    if (correct_answer == student_choice) {
                        answerStatus = "TRUE";
                    }
                }
            }
            if (answerStatus == "TRUE") {
                acquiredmarks = question.getMaximum_mark();
            }
            TestStudentAnswer checkTestStudent = testStudentAnswerRepository
                    .getStudentAnswerByQuestionID(question.getId(), student_id);
            if (checkTestStudent == null) {
                TestStudentAnswer testStudentAnswer = new TestStudentAnswer(null, question.getTest(),
                        student, question,
                        student_answer, "", answerStatus, acquiredmarks, "MARKED");
                testStudentAnswerRepository.save(testStudentAnswer);
            }
        } else {
            String originalFileName = "";
            if (answer_material != null) {
                originalFileName = StringUtils.cleanPath(
                        answer_material.getOriginalFilename());
                long studentfileSeparator = Long.parseLong(test_id.toString()
                        + question_id.toString() + student_id.toString());
                storageService.storeQuestionMaterials(studentfileSeparator, answer_material,
                        StorageServiceImpl.ANSWER_MATERIAL_PATH,
                        true);
            }

            TestStudentAnswer checkTestStudent = testStudentAnswerRepository
                    .getStudentAnswerByQuestionID(question.getId(), student_id);
            if (checkTestStudent == null) {
                TestStudentAnswer testStudentAnswer = new TestStudentAnswer(null, question.getTest(),
                        student, question,
                        student_answer, originalFileName, "", 0, "MARKING");
                testStudentAnswerRepository.save(testStudentAnswer);
            }
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Valid
    @PostMapping(value = { "/teacher/mark-student-question", "/admin/mark-student-question" })
    private ResponseEntity updateStudentFreeQuestion(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "acquired_mark") Integer acquired_mark)
            throws UnauthorizedFileAccessException {
        TestStudentAnswer testStudentAnswer = testStudentAnswerRepository.getStudentAnswerByID(id);
        testStudentAnswer.setAcquired_mark(acquired_mark);
        testStudentAnswer.setMarked_status("MARKED");
        testStudentAnswerRepository.save(testStudentAnswer);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Long getUid() {
        Long uid = userSessionService.getUserAccount().getAccountId();
        return uid;
    }
}
