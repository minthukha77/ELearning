package com.blissstock.mappingSite.controller;

// import java.sql.Date;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.model.FileInfo;

import com.blissstock.mappingSite.model.PaymentLists;
import com.blissstock.mappingSite.model.PaymentRemarkList;
import com.blissstock.mappingSite.model.StudentUnpaidLists;
import com.blissstock.mappingSite.model.TeacherPaymentLists;
import com.blissstock.mappingSite.model.TeacherPaymentHistoryLists;
import com.blissstock.mappingSite.entity.PaymentReceive;
import com.blissstock.mappingSite.entity.PaymentRemark;
import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.entity.JoinCourseUser;
import com.blissstock.mappingSite.entity.PaymentForTeacher;
import com.blissstock.mappingSite.entity.PaymentHistory;
import com.blissstock.mappingSite.repository.PaymentRepository;
import com.blissstock.mappingSite.service.PaymentForTeacherService;
import com.blissstock.mappingSite.service.PaymentHistoryService;
import com.blissstock.mappingSite.service.PaymentRemarkService;
import com.blissstock.mappingSite.service.StorageService;
import com.blissstock.mappingSite.service.StorageServiceImpl;

import com.blissstock.mappingSite.service.UserSessionServiceImpl;
import com.blissstock.mappingSite.utils.CheckUploadFileType;
import com.blissstock.mappingSite.repository.JoinCourseUserRepository;
import com.blissstock.mappingSite.repository.PaymentHistoryRepository;
import com.blissstock.mappingSite.repository.PaymentRemarkRepository;
import com.blissstock.mappingSite.repository.PaymentForTeacherRepository;
import com.blissstock.mappingSite.enums.PaymentStatus;
import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.exceptions.UnauthorizedFileAccessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// import com.blissstock.mappingSite.entity.PaymentReceive;
// import com.blissstock.mappingSite.repository.PaymentRepository;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

// import java.util.List;

@Controller
public class PaymentListController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentListController.class);

    @Autowired
    PaymentRepository paymentRepo;

    @Autowired
    JoinCourseUserRepository joinCourseUserRepo;

    @Autowired
    private PaymentForTeacherRepository paymentForTeacherRepo;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepo;

    @Autowired
    private PaymentHistoryService paymentHistoryService;

    @Autowired
    private UserSessionServiceImpl userSessionService;

    @Autowired
    private JoinCourseUserRepository joinCourseUserRepository;

    @Autowired
    private PaymentRemarkRepository paymentRemarkRepo;

    @Autowired
    private PaymentForTeacherService paymentForTeacherService;

    @Autowired
    private PaymentRemarkService paymentRemarkService;

    @Autowired
    StorageService storageService;

    @RequestMapping("/admin/student-payment-list")
    public String PaymentList(Model model) {
        List<PaymentReceive> viewPayment = paymentRepo.findAll();

        logger.info("Payment Receive List {}", viewPayment);

        // List<String> breadcrumbList = new ArrayList<>();
        // breadcrumbList.add("Top");
        // breadcrumbList.add("Payment List");
        // model.addAttribute("breadcrumbList",breadcrumbList);
        String nav_type = "fragments/adminnav";
        model.addAttribute("nav_type", nav_type);

        List<PaymentLists> payUserList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // List<AllPaymentLists> allPayment = new AllPaymentLists();
        for (PaymentReceive paymentReceive : viewPayment) {
            String paymentDate = format.format(paymentReceive.getPaymentReceiveDate());
            String paymentStatus = paymentReceive.getPaymentStatus();

            JoinCourseUser joinCourseUser = paymentReceive.getJoin();

            UserInfo payUserInfo = joinCourseUser.getUserInfo();
            String userName = payUserInfo.getUserName();
            Long userId = payUserInfo.getUid();
            CourseInfo payCouresInfo = joinCourseUser.getCourseInfo();
            String courseName = payCouresInfo.getCourseName();
            String courseStartDate;
            String courseEndDate;
            Calendar cal = Calendar.getInstance();
            try {
                courseStartDate = format.format(payCouresInfo.getStartDate());
                courseEndDate = format.format(payCouresInfo.getEndDate());
            } catch (Exception e) {
                courseStartDate = format.format(cal.getTime());
                courseEndDate = format.format(cal.getTime());
            }
            String teacherName = payCouresInfo.getUserInfo().getUserName();
            Long courseId = payCouresInfo.getCourseId();
            int courseFees = payCouresInfo.getFees();
            payUserList.add(new PaymentLists(paymentDate, courseStartDate, courseEndDate, paymentStatus, userName,
                    teacherName, courseName, courseFees, userId, courseId));
        }

        logger.info("Payment Receive List including user's information {}", payUserList);

        // System.out.println("All Payments"+payUserList);
        model.addAttribute("allPaymentList", payUserList);

        return "AD0003_StudentPaymentListScreen";
    }

    @RequestMapping("/admin/student-unpaid-list")
    public String StudentUnpaidList(Model model) {

        List<JoinCourseUser> unpaidList = joinCourseUserRepository.findUnpaidList();

        logger.info("Unpaid List {}", unpaidList);

        // List<String> breadcrumbList = new ArrayList<>();
        // breadcrumbList.add("Top");
        // breadcrumbList.add("Payment List");
        // model.addAttribute("breadcrumbList",breadcrumbList);
        String nav_type = "fragments/adminnav";
        model.addAttribute("nav_type", nav_type);

        List<StudentUnpaidLists> studentUnpaidList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // List<AllPaymentLists> allPayment = new AllPaymentLists();
        for (JoinCourseUser unpaidData : unpaidList) {
            // String paymentDate = format.format(paymentReceive.getPaymentReceiveDate())
            String userRole = unpaidData.getUserInfo().getUserAccount().getRole();
            if (!userRole.equals("ROLE_TEACHER")) {
                UserInfo payUserInfo = unpaidData.getUserInfo();
                String userName = payUserInfo.getUserName();
                Long userId = payUserInfo.getUid();
                CourseInfo payCouresInfo = unpaidData.getCourseInfo();
                String courseName = payCouresInfo.getCourseName();
                String courseType = payCouresInfo.getClassType();
                String courseStartDate;
                String courseEndDate;
                Calendar cal = Calendar.getInstance();
                try {
                    courseStartDate = format.format(payCouresInfo.getStartDate());
                    courseEndDate = format.format(payCouresInfo.getEndDate());
                } catch (Exception e) {
                    courseStartDate = format.format(cal.getTime());
                    courseEndDate = format.format(cal.getTime());
                }
                String teacherName = payCouresInfo.getUserInfo().getUserName();
                Long courseId = payCouresInfo.getCourseId();
                Long teacherId = payCouresInfo.getUserInfo().getUid();
                int courseFees = payCouresInfo.getFees();
                studentUnpaidList
                        .add(new StudentUnpaidLists(userName, courseName, teacherName, courseType, courseStartDate,
                                courseEndDate, courseFees, userId, teacherId, courseId, "Pending"));
            }

        }

        logger.info("Payment Receive List including user's information {}", studentUnpaidList);

        // System.out.println("All Payments"+studentUnpaidList);
        model.addAttribute("allStuUnpaidList", studentUnpaidList);

        return "AD0005_StudentUnpaidListScreeen";
    }

    @RequestMapping("/admin/teacher-payment-list")
    public String TeacherPaymentList(Model model) {
        // List<PaymentReceive> viewPayment = paymentRepo.findAll();
        List<PaymentForTeacher> viewPayment = paymentForTeacherRepo.findAll();

        // logger.info("Payment Receive List {}", viewPayment);

        // List<String> breadcrumbList = new ArrayList<>();
        // breadcrumbList.add("Top");
        // breadcrumbList.add("Payment List");
        // model.addAttribute("breadcrumbList",breadcrumbList);
        String nav_type = "fragments/adminnav";
        model.addAttribute("nav_type", nav_type);

        List<TeacherPaymentLists> teacherPayList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // List<AllPaymentLists> allPayment = new AllPaymentLists();
        for (PaymentForTeacher paymentReceive : viewPayment) {
            LocalDate paymentDate = paymentReceive.getPaymentDate();
            String paymentStatus = paymentReceive.getStatus();

            List<UserInfo> studentList = new ArrayList<>();
            for (JoinCourseUser joinCourseUser : paymentReceive.getCourseInfo().getJoin()) {
                if (joinCourseUser.getUserInfo().getUserAccount().getRole().equals(UserRole.STUDENT.getValue()))
                    studentList.add(joinCourseUser.getUserInfo());
            }

            Integer numberOfStudents = studentList.size();
            Long paymentForTeacherId = paymentReceive.getPaymentForTeacherId();

            String courseName = paymentReceive.getCourseInfo().getCourseName();
            String courseStartDate;
            String courseEndDate;
            Calendar cal = Calendar.getInstance();
            try {
                courseStartDate = format.format(paymentReceive.getCourseInfo().getStartDate());
                courseEndDate = format.format(paymentReceive.getCourseInfo().getEndDate());
            } catch (Exception e) {
                courseStartDate = null; /* format.format(cal.getTime()); */
                courseEndDate = null; /* format.format(cal.getTime()); */

            }
            // Long duration = payCouresInfo.getEndDate().getTime() -
            // payCouresInfo.getStartDate().getTime();
            if (paymentReceive.getCourseInfo().getStartDate() != null) {
                LocalDate startLocalDate = paymentReceive.getCourseInfo().getStartDate().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate endLocalDate = paymentReceive.getCourseInfo().getEndDate().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate();
                Period period = Period.between(startLocalDate, endLocalDate);
                String duration = period.toString().substring(1);
                String teacherName = paymentReceive.getCourseInfo().getUserInfo().getUserName();
                Long courseId = paymentReceive.getCourseInfo().getCourseId();
                Long teacherId = paymentReceive.getCourseInfo().getUserInfo().getUid();
                Date calculateFrom = paymentReceive.getCalculateDateFrom();
                Date calculateTo = paymentReceive.getCalculateDateTo();
                int courseFees = (int) paymentReceive.getCourseFee();
                int totalAmount = (int) paymentReceive.getPaymentAmount();
                int payAmount = (int) paymentReceive.getPaymentAmountPercentage();

                boolean varifyState = paymentReceive.getPaymentVerify();
                String varifyStateString = Boolean.toString(varifyState);

                teacherPayList.add(new TeacherPaymentLists(paymentForTeacherId, teacherName, courseName,
                        courseStartDate, courseEndDate, duration, paymentDate, calculateFrom, calculateTo, courseFees,
                        totalAmount, payAmount, paymentStatus, teacherId, courseId, varifyStateString));

            } else {

                String duration = null;

                if (paymentReceive.getCalculateDateFrom() != null && paymentReceive.getCalculateDateTo() != null) {
                    Date calculateFrom = paymentReceive.getCalculateDateFrom();
                    Date calculateTo = paymentReceive.getCalculateDateTo();
                    String teacherName = paymentReceive.getCourseInfo().getUserInfo().getUserName();
                    Long courseId = paymentReceive.getCourseInfo().getCourseId();
                    Long teacherId = paymentReceive.getCourseInfo().getUserInfo().getUid();
                    int courseFees = (int) paymentReceive.getCourseFee();
                    int totalAmount = (int) paymentReceive.getPaymentAmount();
                    int payAmount = (int) paymentReceive.getPaymentAmountPercentage();

                    boolean varifyState = paymentReceive.getPaymentVerify();
                    String varifyStateString = Boolean.toString(varifyState);

                    teacherPayList.add(new TeacherPaymentLists(paymentForTeacherId, teacherName, courseName,
                            courseStartDate, courseEndDate, duration, paymentDate, calculateFrom, calculateTo,
                            courseFees, totalAmount, payAmount, paymentStatus, teacherId, courseId, varifyStateString));

                } else {

                    Date calculateFrom = null;
                    Date calculateTo = null;
                    String teacherName = paymentReceive.getCourseInfo().getUserInfo().getUserName();
                    Long courseId = paymentReceive.getCourseInfo().getCourseId();
                    Long teacherId = paymentReceive.getCourseInfo().getUserInfo().getUid();
                    int courseFees = (int) paymentReceive.getCourseFee();
                    int totalAmount = (int) paymentReceive.getPaymentAmount();
                    int payAmount = (int) paymentReceive.getPaymentAmountPercentage();

                    boolean varifyState = paymentReceive.getPaymentVerify();
                    String varifyStateString = Boolean.toString(varifyState);

                    teacherPayList.add(new TeacherPaymentLists(paymentForTeacherId, teacherName, courseName,
                            courseStartDate, courseEndDate, duration, paymentDate, calculateFrom, calculateTo,
                            courseFees, totalAmount, payAmount, paymentStatus, teacherId, courseId, varifyStateString));
                }

            }

        }

        // logger.info("Payment Receive List including user's information {}",
        // teacherPayList);

        model.addAttribute("TeacherPaymentList", teacherPayList);

        return "AD0006_TeacherPaymentListScreen";
    }

    @PostMapping("/admin/teacher-payment-list/verifyPayment")
    @ResponseBody
    public ResponseEntity<String> verifyPayment(@RequestParam("paymentId") Long paymentId) {
        PaymentForTeacher payment = paymentForTeacherRepo.getById(paymentId);
        if (payment != null && payment.getStatus().equals("COMPLETE")) {
            // String paystatus = "COMPLETE";
            // payment.setStatus(paystatus);
            payment.setPaymentVerify(true);
            paymentForTeacherService.savePaymentForTeacher(payment);
            return ResponseEntity.ok("Payment verified");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Payment not verified. Please submit payment receipt photo and remark first.");
        }
    }

    @PostMapping("/admin/PaymentHistoryRegistration/RemarkDelete/")
    @ResponseBody
    public ResponseEntity<String> deletePaymentRemark(@RequestParam("paymentRemarkId") Long paymentRemarkId) {
        try {
            PaymentRemark paymentRemark = paymentRemarkRepo.getById(paymentRemarkId);
            if (paymentRemark != null) {

                paymentRemark.setRemarkStatus(false);
                paymentRemarkRepo.save(paymentRemark);
                return ResponseEntity.ok("Remark deleted");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Payment remark not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting payment remark: " + e.getMessage());
        }
    }

    @GetMapping("/admin/PaymentHistoryRegistration/{paymentForTeacherId}")
    public String getPaymentHistory(@PathVariable Long paymentForTeacherId, Model model) {
        String nav_type = "fragments/adminnav";
        model.addAttribute("nav_type", nav_type);

        PaymentForTeacher paymentForTeacher = paymentForTeacherRepo.getById(paymentForTeacherId);

        List<PaymentHistory> viewHistory = paymentHistoryRepo.findAll();
        if (!viewHistory.isEmpty()) {
            int idStatus = 0;
            for (PaymentHistory viewHistoryTable : viewHistory) {
                Long payTeacherId = viewHistoryTable.getPaymentForTeacher().getPaymentForTeacherId();
                if (payTeacherId.equals(paymentForTeacherId)) {
                    idStatus = idStatus + 1;
                    Long findPaymentHistoryId = viewHistoryTable.getPaymentHistoryId();

                    long fileSeparator = 100000L + findPaymentHistoryId;
                    System.out.println("file separator" + fileSeparator);

                    try {
                        FileInfo profilePic = storageService.loadPaymentSlip(fileSeparator, viewHistoryTable);
                        model.addAttribute("profilePic", profilePic);
                        model.addAttribute("slip", profilePic.getUrl());
                        System.out.println("Inside Profile Pic: " + profilePic);
                        System.out.println("Inside viewHistory: " + viewHistoryTable.getSlipImg());
                    } catch (Exception e) {
                        e.printStackTrace();
                        model.addAttribute("profilePic", null);
                        model.addAttribute("slip", null);
                        logger.info("unable to get profile {}", fileSeparator);
                    }

                    List<PaymentRemarkList> paymentRemarkLists = new ArrayList<>();
                    List<PaymentRemark> viewRemark = paymentRemarkRepo.findAll();

                    for (PaymentRemark paymentRemark : viewRemark) {

                        Long payHistoryIdFromRemarkTable = paymentRemark.getPaymentHistory().getPaymentHistoryId();
                        boolean remarkStatus = paymentRemark.getRemarkStatus();

                        if (payHistoryIdFromRemarkTable.equals(findPaymentHistoryId) && remarkStatus == true) {
                            String adminEmail = paymentRemark.getUserInfo().getUserAccount().getMail();
                            String remarkText = paymentRemark.getRemark();
                            Date remarkDate = paymentRemark.getRemarkDate();
                            Long paymentRemarkId = paymentRemark.getId();
                            paymentRemarkLists
                                    .add(new PaymentRemarkList(adminEmail, remarkText, remarkDate, paymentRemarkId));
                            model.addAttribute("RemarkList", paymentRemarkLists);

                        }

                    }

                }
            }
            // if (idStatus == 0) {
            // List<PaymentRemarkList> paymentRemarkLists = new ArrayList<>();
            // String adminEmail = null;
            // String remarkText = "No one has remarked yet!";
            // Date remarkDate = null;
            // Long paymentRemarkId = null;
            // paymentRemarkLists.add(new PaymentRemarkList(adminEmail, remarkText,
            // remarkDate, paymentRemarkId));
            // model.addAttribute("RemarkList", paymentRemarkLists);

            // }
        }

        // else {
        // List<PaymentRemarkList> paymentRemarkLists = new ArrayList<>();
        // String adminEmail = null;
        // String remarkText = "No one has remarked yet!";
        // Date remarkDate = null;
        // Long paymentRemarkId = null;
        // paymentRemarkLists.add(new PaymentRemarkList(adminEmail, remarkText,
        // remarkDate, paymentRemarkId));
        // model.addAttribute("RemarkList", paymentRemarkLists);
        // System.out.println("Course ID from PaymentHistory database is not found. Thus
        // system skipped processing"
        // + paymentRemarkLists);
        // }

        // List<PaymentHistoryRegistrationList> paymentHistoryRegistrationLists = new
        // ArrayList<>();

        String teacherName = paymentForTeacher.getCourseInfo().getUserInfo().getUserName();
        String courseName = paymentForTeacher.getCourseInfo().getCourseName();
        Date paymentFrom = paymentForTeacher.getCalculateDateFrom();
        Date paymentTo = paymentForTeacher.getCalculateDateTo();
        int payAmount = (int) paymentForTeacher.getPaymentAmountPercentage();
        LocalDate paymentDate = paymentForTeacher.getPaymentDate();
        Long paymentTeacherId = paymentForTeacher.getPaymentForTeacherId();
        Long courseId = paymentForTeacher.getCourseInfo().getCourseId();
        boolean varifyState = paymentForTeacher.getPaymentVerify();
        String varifyStateString = Boolean.toString(varifyState);

        // paymentHistoryRegistrationLists.add(new
        // PaymentHistoryRegistrationList(teacherName, courseName, paymentFrom,
        // paymentTo, payAmount, paymentDate));
        model.addAttribute("teacherName", teacherName);
        model.addAttribute("courseName", courseName);
        model.addAttribute("paymentFrom", paymentFrom);
        model.addAttribute("paymentTo", paymentTo);
        model.addAttribute("payAmount", payAmount);
        model.addAttribute("paymentDate", paymentDate);
        model.addAttribute("paymentTeacherId", paymentTeacherId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("varifyStateString", varifyStateString);

        return "AD0007_PaymentHistoryRegistration";
    }

    @PostMapping("/admin/PaymentHistoryRegistration/registrationForm")
    public String submitForm(@RequestParam("paymentTeacherId") Long paymentTeacherId,
            @RequestParam("courseId") Long courseId,
            @RequestParam("teacherName") String teacherName,
            @RequestParam("courseName") String courseName,
            @RequestParam("courseStartTime") String courseStartTime,
            @RequestParam("courseEndTime") String courseEndTime,
            @RequestParam("amountPaid") Long amountPaid,
            @RequestParam("amountDate") java.sql.Date amountDate,
            @RequestParam(value = "comments", required = false) String comments,
            @RequestParam(value = "photo", required = false) MultipartFile photo) {

        PaymentForTeacher paymentForTeacher = paymentForTeacherRepo.getById(paymentTeacherId);
        LocalDate paidDate = amountDate.toLocalDate();
        paymentForTeacher.setPaymentAmountPercentage(amountPaid);
        paymentForTeacher.setPaymentDate(paidDate);
        paymentForTeacher.setStatus("COMPLETE");
        paymentForTeacherService.savePaymentForTeacher(paymentForTeacher);

        List<PaymentHistory> paymentHistory = paymentHistoryRepo.findAll();

        if (paymentHistory.isEmpty()) {
            PaymentHistory payHistory = new PaymentHistory();
            payHistory.setCourseId(paymentForTeacher.getCourseInfo().getCourseId());
            payHistory.setPaymentAmount(amountPaid);
            payHistory.setPaymentDate(amountDate);
            payHistory.setPaymentForTeacher(paymentForTeacher);
            paymentHistoryService.savePaymentHistory(payHistory);
            PaymentForTeacher paymentTeacher = paymentForTeacherRepo.getById(paymentTeacherId);
            Long courseIdForPaymentHistory = paymentTeacher.getCourseInfo().getCourseId();
            PaymentHistory viewHistory = paymentHistoryRepo.findByCourseId(courseIdForPaymentHistory);
            Long paymentHistoryId = viewHistory.getPaymentHistoryId();

            System.out.println("Newly created PayHistoryID: " + paymentHistoryId);

            PaymentHistory history = paymentHistoryRepo.getById(paymentHistoryId);

            Long uid = history.getPaymentHistoryId();
            System.out.println("Assigned Payment History ID: " + uid);

            long fileSeparator = 100000L + uid;
            if (photo != null && CheckUploadFileType.checkType(photo)) {

                String originalFileName = StringUtils.cleanPath(
                        photo.getOriginalFilename());

                try {

                    storageService.store(fileSeparator, photo, StorageServiceImpl.PROFILE_PATH, true);
                    PaymentHistory paySlipHistory = paymentHistoryRepo.getById(uid);
                    paySlipHistory.setSlipImg(originalFileName);
                    paymentHistoryService.savePaymentHistory(paySlipHistory);
                } catch (UnauthorizedFileAccessException e) {
                    e.printStackTrace();
                }

                logger.info("Payment Slip photo {} stored", originalFileName);
            }
            if (comments != null) {
                List<PaymentRemark> paymentRemarks = paymentRemarkRepo.findAll();
                if (paymentRemarks.isEmpty()) {
                    PaymentRemark paymentRemark = new PaymentRemark();
                    java.sql.Date remarkDate = new java.sql.Date(System.currentTimeMillis());
                    UserAccount userAccount = userSessionService.getUserAccount();

                    paymentRemark.setRemark(comments);
                    paymentRemark.setRemarkDate(remarkDate);
                    paymentRemark.setRemarkStatus(true);
                    paymentRemark.setPaymentHistory(history);
                    paymentRemark.setUserInfo(userAccount.getUserInfo());
                    paymentRemarkService.savePaymentRemark(paymentRemark);
                }
            }
        } else {
            Long findPaymentHistoryId = 0L;
            for (PaymentHistory payHistory : paymentHistory) {
                Long paymentForTeacherID = payHistory.getPaymentForTeacher().getPaymentForTeacherId();

                if (paymentForTeacherID.equals(paymentTeacherId)) {
                    findPaymentHistoryId = payHistory.getPaymentHistoryId();

                }

            }

            if (findPaymentHistoryId != 0) {
                PaymentHistory existingHistory = paymentHistoryRepo.getById(findPaymentHistoryId);
                existingHistory.setPaymentAmount(amountPaid);
                existingHistory.setPaymentDate(amountDate);
                paymentHistoryService.savePaymentHistory(existingHistory);
                Long uid = findPaymentHistoryId;

                long fileSeparator = 100000L + uid;
                if (photo != null && CheckUploadFileType.checkType(photo)) {

                    String originalFileName = StringUtils.cleanPath(
                            photo.getOriginalFilename());

                    try {

                        storageService.store(fileSeparator, photo, StorageServiceImpl.PROFILE_PATH, true);
                        PaymentHistory paySlipHistory = paymentHistoryRepo.getById(uid);
                        paySlipHistory.setSlipImg(originalFileName);
                        paymentHistoryService.savePaymentHistory(paySlipHistory);
                    } catch (UnauthorizedFileAccessException e) {
                        e.printStackTrace();
                    }

                    logger.info("Payment Slip photo {} stored", originalFileName);

                }
                if (comments != null) {
                    PaymentRemark paymentRemark = new PaymentRemark();
                    java.sql.Date remarkDate = new java.sql.Date(System.currentTimeMillis());
                    UserAccount userAccount = userSessionService.getUserAccount();

                    paymentRemark.setRemark(comments);
                    paymentRemark.setRemarkDate(remarkDate);
                    paymentRemark.setRemarkStatus(true);
                    paymentRemark.setPaymentHistory(existingHistory);
                    paymentRemark.setUserInfo(userAccount.getUserInfo());
                    paymentRemarkService.savePaymentRemark(paymentRemark);
                }
            } else if (findPaymentHistoryId == 0) {
                PaymentHistory newPaymentHistory = new PaymentHistory();
                newPaymentHistory.setCourseId(paymentForTeacher.getCourseInfo().getCourseId());
                newPaymentHistory.setPaymentAmount(amountPaid);
                newPaymentHistory.setPaymentDate(amountDate);
                newPaymentHistory.setPaymentForTeacher(paymentForTeacher);
                paymentHistoryService.savePaymentHistory(newPaymentHistory);

                // PaymentForTeacher paymentTeacher =
                // paymentForTeacherRepo.getById(paymentTeacherId);
                // Long courseIdForPaymentHistory =
                // paymentTeacher.getCourseInfo().getCourseId();

                List<PaymentHistory> viewHistory = paymentHistoryRepo.findAll();

                for (PaymentHistory checkHistoryTable : viewHistory) {
                    Long paymentForTeacherID = checkHistoryTable.getPaymentForTeacher().getPaymentForTeacherId();

                    if (paymentForTeacherID.equals(paymentTeacherId)) {

                        findPaymentHistoryId = checkHistoryTable.getPaymentHistoryId();
                        System.out.println("Additional created PayHistoryID: " + findPaymentHistoryId);
                        PaymentHistory history = paymentHistoryRepo.getById(findPaymentHistoryId);
                        Long uid = findPaymentHistoryId;
                        System.out.println("Assigned Payment History ID: " + uid);
                        long fileSeparator = 100000L + uid;

                        if (!photo.isEmpty() && CheckUploadFileType.checkType(photo)) {

                            String originalFileName = StringUtils.cleanPath(
                                    photo.getOriginalFilename());

                            try {

                                storageService.store(fileSeparator, photo, StorageServiceImpl.PROFILE_PATH, true);
                                PaymentHistory paySlipHistory = paymentHistoryRepo.getById(uid);
                                paySlipHistory.setSlipImg(originalFileName);
                                paymentHistoryService.savePaymentHistory(paySlipHistory);
                            } catch (UnauthorizedFileAccessException e) {
                                e.printStackTrace();
                            }

                            logger.info("Payment Slip photo {} stored", originalFileName);

                        }
                        if (comments != null) {
                            PaymentRemark paymentRemark = new PaymentRemark();
                            java.sql.Date remarkDate = new java.sql.Date(System.currentTimeMillis());
                            UserAccount userAccount = userSessionService.getUserAccount();

                            paymentRemark.setRemark(comments);
                            paymentRemark.setRemarkDate(remarkDate);
                            paymentRemark.setRemarkStatus(true);
                            paymentRemark.setPaymentHistory(history);
                            paymentRemark.setUserInfo(userAccount.getUserInfo());
                            paymentRemarkService.savePaymentRemark(paymentRemark);
                        }
                    }

                }

            }

        }

        return "redirect:/admin/teacher-payment-list";

    }

    @RequestMapping("/admin/teacher-payment-history-list")
    public String TeacherPaymentHistoryList(Model model) {

        // List<PaymentReceive> viewPayment =
        // paymentRepo.findByPaymentStatus(PaymentStatus.COMPLETED.toString());
        List<PaymentHistory> viewPayment = paymentHistoryRepo.findAll();
        // List<PaymentReceive> viewPayment = paymentRepo.findAll();

        // logger.info("Payment Receive List {}", viewPayment);

        // List<String> breadcrumbList = new ArrayList<>();
        // breadcrumbList.add("Top");
        // breadcrumbList.add("Payment List");
        // model.addAttribute("breadcrumbList",breadcrumbList);
        String nav_type = "fragments/adminnav";
        model.addAttribute("nav_type", nav_type);

        List<TeacherPaymentHistoryLists> teacherPayHistoryList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // List<AllPaymentLists> allPayment = new AllPaymentLists();
        for (PaymentHistory paymentReceive : viewPayment) {
            // String paymentDate = format.format(paymentReceive.getPaymentReceiveDate());
            // String paymentStatus = paymentReceive.getPaymentStatus();

            // JoinCourseUser joinCourseUser = paymentReceive.getJoin();

            // // UserInfo payUserInfo = joinCourseUser.getUserInfo();
            // // String userName = payUserInfo.getUserName();
            // // Long userId = payUserInfo.getUid();
            // CourseInfo payCouresInfo = joinCourseUser.getCourseInfo();
            // String courseName = payCouresInfo.getCourseName();
            // String courseStartDate;
            // String courseEndDate;
            // Calendar cal = Calendar.getInstance();
            // try {
            // courseStartDate = format.format(payCouresInfo.getStartDate());
            // courseEndDate = format.format(payCouresInfo.getEndDate());
            // } catch (Exception e) {
            // courseStartDate = format.format(cal.getTime());
            // courseEndDate = format.format(cal.getTime());

            // }
            // // Long duration = payCouresInfo.getEndDate().getTime() -
            // payCouresInfo.getStartDate().getTime();
            // LocalDate startLocalDate =
            // payCouresInfo.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // LocalDate endLocalDate =
            // payCouresInfo.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Period period = Period.between(startLocalDate, endLocalDate);
            // String duration = period.toString().substring(1);

            // String teacherName = payCouresInfo.getUserInfo().getUserName();
            // // Long courseId = payCouresInfo.getCourseId();
            // Long teacherId = payCouresInfo.getUserInfo().getUid();
            // int courseFees = payCouresInfo.getFees();
            // Double teacherPayment = courseFees - (courseFees * 0.1);
            // int payAmount = teacherPayment.intValue();

            String courseName = paymentReceive.getPaymentForTeacher().getCourseInfo().getCourseName();
            String teacherName = paymentReceive.getPaymentForTeacher().getCourseInfo().getUserInfo().getUserName();
            Long teacherId = paymentReceive.getPaymentForTeacher().getCourseInfo().getUserInfo().getUid();
            Date paymentDate = paymentReceive.getPaymentDate();
            int courseFees = (int) paymentReceive.getPaymentAmount();
            Long courseId = paymentReceive.getCourseId();
            Long paymentForTeacherId = paymentReceive.getPaymentForTeacher().getPaymentForTeacherId();

            teacherPayHistoryList.add(new TeacherPaymentHistoryLists(courseName, teacherName, teacherId, paymentDate,
                    courseFees, courseId, paymentForTeacherId));
        }

        logger.info("Payment Receive List including user's information {}", teacherPayHistoryList);

        model.addAttribute("allteacherPayHistoryList", teacherPayHistoryList);

        return "AD0008_TeacherPaymentHistoryList";
    }

}