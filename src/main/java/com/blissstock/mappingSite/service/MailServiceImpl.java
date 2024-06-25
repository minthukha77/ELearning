package com.blissstock.mappingSite.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.blissstock.mappingSite.config.GmailConfig;

import com.blissstock.mappingSite.controller.CourseDetailsController;
import com.blissstock.mappingSite.entity.AddAdmin;
import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.JoinCourseUser;
import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.enums.TokenType;

import com.blissstock.mappingSite.enums.UserRole;

// import org.postgresql.translation.messages_bg;

import com.fasterxml.jackson.core.sym.Name;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.springframework.beans.factory.annotation.Value;



@Service
public class MailServiceImpl implements MailService {

  private static final Logger logger = LoggerFactory.getLogger(
      MailServiceImpl.class);

  @Autowired
  @Qualifier("gmail")
  private JavaMailSender mailSender;

  @Autowired
  private UserService userService;

  @Autowired
  private SpringTemplateEngine templateEngine;

  @Autowired
  GmailConfig gmailConfig;
  /*
   * @Autowired
   * private MessageSource messages;
   */

  @Value("${com.blissstock.mapping-site.ENVIRONMENT}")
  String ENVIRONMENT;
  @Value("${com.blissstock.mapping-site.TESTING_DOMAIN}")
  String TESTING_DOMAIN;
  @Value("${com.blissstock.mapping-site.PRODUCTION_DOMAIN}")
  String PRODUCTION_DOMAIN;

  private String email = "sys@pyinnyar-subuu.com";
  private String fromName = "PyinnyarSubuu";

  public void sendMail(
      String subject,
      String toAddresses,
      String ccAddresses,
      String bccAddresses,
      String body) {
    MimeMessagePreparator preparator = mimeMessage -> {
      InternetAddress fromAddress = new InternetAddress(email, fromName);
      MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
      message.setTo(toAddresses.split("[,;]"));
      message.setFrom(fromAddress);
      message.setSubject(subject);
      if (!ccAddresses.isBlank())
        message.setCc(ccAddresses.split("[;,]"));
      if (!bccAddresses.isBlank())
        message.setBcc(bccAddresses.split("[;,]"));
      message.setText(body, false);
    };
    mailSender.send(preparator);
    logger.info(
        "Email sent successfully To {},{} with Subject {}",
        toAddresses,
        ccAddresses,
        subject);
  }

  public void sendVerificationMail(UserAccount userAccount) throws MessagingException {
    String appUrl = getServerAddress();
    String token = UUID.randomUUID().toString();
    userService.createToken(userAccount, token, TokenType.VERIFICATION);

    String recipientAddress = userAccount.getMail();
    String subject = "Registration Confirmation";

    String confirmationUrl = appUrl + "/verify_password?token=" + token;

    final Context ctx = new Context();
    ctx.setVariable("confirmationUrl", confirmationUrl);
    ctx.setVariable("Date", new Date());
    ctx.setVariable("token", token);
    ctx.setVariable("appUrl", appUrl);

    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("sampleCss", ctx);
    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);
  }

  public void SendAdminNewAdmin(UserAccount userAccount, UserInfo adminInfo, String appUrl) throws MessagingException {

    String superAdminEmail = "syspyinnyarsubuu.supadm@gmail.com";

    String recipientAddress = userAccount.getMail();

    String subject = "【Pyinnyar Subuu】Admin Account Registration Successfully  Completed!";

    appUrl = appUrl + "/admin/admin-list";

    final Context ctx = new Context();
    ctx.setVariable("Date", new Date());
    ctx.setVariable("appUrl", appUrl);
    ctx.setVariable("superAdminEmail", superAdminEmail);
    ctx.setVariable("adminEmail", userAccount.getMail());


    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("SendAdminNewAdminMail", ctx);
    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);
  }

  public void SendSuperAdminNewAdmin(UserAccount userAccount, UserInfo adminInfo, String appUrl) throws MessagingException {

    String recipientAddress = "syspyinnyarsubuu.supadm@gmail.com";
    String superAdminName = "Pyinnyarsubuu Superadmin";
    String subject = "【Pyinnyar Subuu】Admin Account Registration Successfully  Completed!";

    appUrl = appUrl + "/admin/admin-list";

    final Context ctx = new Context();
    // UserAccount adminAccount = adminInfo.getUserAccount();
    ctx.setVariable("Date", new Date());
    ctx.setVariable("appUrl", appUrl);
    ctx.setVariable("superAdminName", superAdminName);
    ctx.setVariable("adminEmail", userAccount.getMail());



    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("SendSuperAdminNewAdminMail", ctx);
    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);
  }

  public void SendAdminNewTeacher(UserInfo userInfo) throws MessagingException {

    String appUrl = getServerAddress();
    String recipientAddress = "sys@pyinnyar-subuu.com";
    String subject = "New Teacher Has Registered";

    appUrl = appUrl + "/admin/teacher-list";

    final Context ctx = new Context();
    ctx.setVariable("confirmationUrl", "");
    ctx.setVariable("Date", new Date());

    ctx.setVariable("appUrl", appUrl);

    UserAccount userAccount = userInfo.getUserAccount();
    ctx.setVariable("name", userInfo.getUserName());
    ctx.setVariable("email", userAccount.getMail());
    ctx.setVariable("phone", userInfo.getPhoneNo());
    ctx.setVariable("education", userInfo.getEducation());
    ctx.setVariable("description", userInfo.getSelfDescription());
    ctx.setVariable("status", userAccount.getAccountStatus());
    ctx.setVariable("registerTime", userAccount.getRegisteredDate());

    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("NewTeacherRegisteMail", ctx);
    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);
  }

  public void SendAdminNewStudent(UserInfo userInfo) throws MessagingException {
    String appUrl = getServerAddress();
    String recipientAddress = "sys@pyinnyar-subuu.com";
    String subject = "New Student Has Registered";

    appUrl = appUrl + "/admin/student-list";

    final Context ctx = new Context();
    ctx.setVariable("confirmationUrl", "");
    ctx.setVariable("Date", new Date());

    ctx.setVariable("appUrl", appUrl);

    UserAccount userAccount = userInfo.getUserAccount();
    ctx.setVariable("name", userInfo.getUserName());
    ctx.setVariable("email", userAccount.getMail());
    ctx.setVariable("phone", userInfo.getPhoneNo());
    ctx.setVariable("registerTime", userAccount.getRegisteredDate());

    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("NewStudentRegisteMail", ctx);
    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);
  }

  public void SendAdminNewCourseByTeacher(CourseInfo courseInfo) throws MessagingException {
    String appUrl = getServerAddress();
    String recipientAddress = "sys@pyinnyar-subuu.com";
    String subject = "【Pyinnyar Subuu】Course Registration by a teacher Successfully Completed!";

    appUrl = appUrl + "/admin/course-info";

    final Context ctx = new Context();
    ctx.setVariable("confirmationUrl", "");
    ctx.setVariable("Date", new Date());

    ctx.setVariable("appUrl", appUrl);
    ctx.setVariable("teacherEmail", courseInfo.getUserInfo().getUserAccount().getMail());
    ctx.setVariable("courseName", courseInfo.getCourseName());
    ctx.setVariable("coursePrice", courseInfo.getFees());
    ctx.setVariable("startDate", courseInfo.getStartDate());
    ctx.setVariable("endDate", courseInfo.getEndDate());


    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("SendAdminNewCourseByTeacher", ctx);
    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);
  }

  public void SendTeacherNewCourseByTeacher(CourseInfo courseInfo) throws MessagingException {
    String appUrl = getServerAddress();
    String recipientAddress = courseInfo.getUserInfo().getUserAccount().getMail();
    String subject = "【Pyinnyar Subuu】Course Registration by Teacher Successfully Completed!";

    appUrl = appUrl + "/admin/course-info";

    final Context ctx = new Context();
    ctx.setVariable("confirmationUrl", "");
    ctx.setVariable("Date", new Date());
    ctx.setVariable("appUrl", appUrl);
    ctx.setVariable("teacherName", courseInfo.getUserInfo().getUserName());
    ctx.setVariable("courseName", courseInfo.getCourseName());
    ctx.setVariable("coursePrice", courseInfo.getFees());
    ctx.setVariable("startDate", courseInfo.getStartDate());
    ctx.setVariable("endDate", courseInfo.getEndDate());



    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("SendTeacherNewCourseByTeacher", ctx);
    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);
  }

  public void SendAdminNewCourseByAdmin(UserAccount userAccount, CourseInfo courseInfo, String appUrl) throws MessagingException {

    String recipientAddress = userAccount.getMail();
    String subject = "【Pyinnyar Subuu】Course Registration by admin Successfully Completed!";

    appUrl = appUrl + "/admin/course-info";

    final Context ctx = new Context();
    ctx.setVariable("confirmationUrl", "");
    ctx.setVariable("Date", new Date());

    ctx.setVariable("appUrl", appUrl);
    ctx.setVariable("teacherName", courseInfo.getUserInfo().getUserName());
    ctx.setVariable("teacherEmail", courseInfo.getUserInfo().getUserAccount().getMail());
    ctx.setVariable("courseName", courseInfo.getCourseName());
    ctx.setVariable("startDate", courseInfo.getStartDate());
    ctx.setVariable("endDate", courseInfo.getEndDate());


    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("SendAdminNewCourseByAdmin", ctx);
    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);
  }

  public void SendTeacherNewCourseByAdmin(CourseInfo courseInfo, String appUrl) throws MessagingException {

    String recipientAddress = courseInfo.getUserInfo().getUserAccount().getMail();
    String subject = "【Pyinnyar Subuu】Course Registration by admin Successfully Completed!";

    appUrl = appUrl + "/admin/course-info";

    final Context ctx = new Context();
    ctx.setVariable("confirmationUrl", "");
    ctx.setVariable("Date", new Date());
    ctx.setVariable("appUrl", appUrl);
    // ctx.setVariable("adminName", courseInfo.getUserInfo().getUserName());
    ctx.setVariable("teacherName", courseInfo.getUserInfo().getUserName());
    ctx.setVariable("courseName", courseInfo.getCourseName());
    ctx.setVariable("startDate", courseInfo.getStartDate());
    ctx.setVariable("endDate", courseInfo.getEndDate());



    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("SendTeacherNewCourseByAdmin", ctx);
    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);
  }


  @Override
  public void SendAdminNewStudentEnroll(UserInfo userInfo, long courseId, CourseInfo courseInfo) throws MessagingException {
    String appUrl = getServerAddress();
    String recipientAddress = "sys@pyinnyar-subuu.com";
    String ccAddress1 = "sys1pyinnyarsubuu@gmail.com";
    String ccAddress2 = "sys2pyinnyarsubuu@gmail.com";

    String subject = "【Pyinnyar Subuu】A student has successfully enrolled in a course.";

    appUrl = appUrl + "/guest/course-detail/" + courseId;

    final Context ctx = new Context();
    ctx.setVariable("confirmationUrl", "");
    ctx.setVariable("Date", new Date());

    ctx.setVariable("appUrl", appUrl);

    UserAccount userAccount = userInfo.getUserAccount();
    logger.warn("new Student mail is " + userAccount.getMail());
    ctx.setVariable("courseName", courseInfo.getCourseName());
    ctx.setVariable("coursePrice", courseInfo.getFees());
    ctx.setVariable("teacherName", courseInfo.getUserInfo().getUserName());
    ctx.setVariable("studentName", userInfo.getUserName());
    ctx.setVariable("startDate", courseInfo.getStartDate());
    ctx.setVariable("endDate", courseInfo.getEndDate());

    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);
    message.addCc(ccAddress1);
    message.addCc(ccAddress2);

    final String htmlContent = templateEngine.process("EnrollCourseMailForAdmin", ctx);
    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);
  }

  @Override
  public void SendStudentEnrollCourse(UserInfo userInfo, CourseInfo courseInfo) throws MessagingException {
    String appUrl = getServerAddress();
    String recipientAddress = userInfo.getUserAccount().getMail();
    String subject =  "【Pyinnyar Subuu】You have successfully enrolled in a course.";

    appUrl = appUrl + "/guest/course-detail/" + courseInfo.getCourseId();

    final Context ctx = new Context();
    ctx.setVariable("confirmationUrl", "");
    ctx.setVariable("Date", new Date());

    ctx.setVariable("appUrl", appUrl);

    UserAccount userAccount = userInfo.getUserAccount();
    logger.warn("new Student mail is " + userAccount.getMail());
    ctx.setVariable("studentName", userInfo.getUserName());
    ctx.setVariable("courseName", courseInfo.getCourseName());
    ctx.setVariable("coursePrice", courseInfo.getFees());
    ctx.setVariable("teacherName", courseInfo.getUserInfo().getUserName());
    ctx.setVariable("startDate", courseInfo.getStartDate());
    ctx.setVariable("endDate", courseInfo.getEndDate());

    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("EnrollCourseMailForStudent", ctx);
    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);
  }

  @Override
  public void SendTeacherNewStudentEnroll(UserInfo userInfo, CourseInfo courseInfo) throws MessagingException {
    String appUrl = getServerAddress();
    String recipientAddress = courseInfo.getUserInfo().getUserAccount().getMail();
    String subject =  "【Pyinnyar Subuu】A student has successfully enrolled in a course.";

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

    appUrl = appUrl + "/guest/course-detail/" + courseInfo.getCourseId();

    final Context ctx = new Context();
    ctx.setVariable("confirmationUrl", "");
    ctx.setVariable("Date", new Date());

    ctx.setVariable("appUrl", appUrl);

    UserAccount userAccount = userInfo.getUserAccount();
    logger.warn("new Student mail is " + userAccount.getMail());
    ctx.setVariable("courseName", courseInfo.getCourseName());
    ctx.setVariable("coursePrice", courseInfo.getFees());
    ctx.setVariable("teacherName", courseInfo.getUserInfo().getUserName());
    ctx.setVariable("studentName", userInfo.getUserName());
    ctx.setVariable("numSeatsLeft", availableStuList);


    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("EnrollCourseMailForTeacher", ctx);
    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);
  }
  


  @Override
  public void sendResetPasswordMail(UserAccount userAccount) throws MessagingException {
    String appUrl = getServerAddress();
    String token = UUID.randomUUID().toString();
    userService.createToken(userAccount, token, TokenType.PASSWORD_RESET);
    logger.info("Password  resst requesst from :" + userAccount.getMail());
    String recipientAddress = userAccount.getMail();
    String subject = "Reset Password";
    String confirmationUrl = appUrl + "/resetPassword?token=" + token;
    final Context ctx = new Context();
    ctx.setVariable("confirmationUrl", confirmationUrl);
    ctx.setVariable("Date", new Date());
    ctx.setVariable("token", token);
    ctx.setVariable("appUrl", appUrl);
    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("PasswordResetMail", ctx);

    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);

  }
  
  @Override
  public void PaymentByStudent(UserInfo userInfo, long courseId, CourseInfo courseInfo) throws MessagingException {

    
    String subject = "【Pyinnyar Subuu】Course payment by a student successfully completed!";

    final Context ctx = new Context();
    ctx.setVariable("Date", new Date());

    UserAccount userAccount = userInfo.getUserAccount();
    ctx.setVariable("studentname", userInfo.getUserName());
    String recipientAddress = userAccount.getMail();
    ctx.setVariable("email", userAccount.getMail());
    ctx.setVariable("teachername", courseInfo.getUserInfo().getUserName());
    ctx.setVariable("coursename", courseInfo.getCourseName());
    ctx.setVariable("coursefees", courseInfo.getFees());
    ctx.setVariable("startdate", courseInfo.getStartDate());
    ctx.setVariable("enddate", courseInfo.getEndDate());
    

    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("PaymentByStudentCss", ctx);
    message.setText(htmlContent, true);
    
    this.mailSender.send(mimeMessage);


  }

  @Override
  public void PaymentReceivedByAdmin(UserInfo userInfo, long courseId, CourseInfo courseInfo) throws MessagingException {
    String appUrl = getServerAddress();
    String recipientAddress = "sys@pyinnyar-subuu.com";
    String subject = "【Pyinnyar Subuu】Please check student payment, course payment by a student is done successfully.";
     
    final Context ctx = new Context();
    ctx.setVariable("studentname", userInfo.getUserName());
    ctx.setVariable("teachername", courseInfo.getUserInfo().getUserName());
    ctx.setVariable("coursename", courseInfo.getCourseName());
    ctx.setVariable("coursefees", courseInfo.getFees());
    ctx.setVariable("startdate", courseInfo.getStartDate());
    ctx.setVariable("enddate", courseInfo.getEndDate());
    ctx.setVariable("Date", new Date());
    ctx.setVariable("appUrl", appUrl);

    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("PaymentReceivedByAdmin", ctx);
    message.setText(htmlContent, true);
    
    this.mailSender.send(mimeMessage);


  }

  @Override
  public void VerifiedTeacherByAdmin(UserInfo teacherInfo, UserInfo adminInfo) throws MessagingException {
    String appUrl = getServerAddress();
    
    String subject = "【Pyinnyar Subuu】Teacher Account Verification Successfully Completed! ";

    final Context ctx = new Context();
    UserAccount teacherAccount = teacherInfo.getUserAccount();
    UserAccount adminAccount = adminInfo.getUserAccount();
    String recipientAddress = adminAccount.getMail();
    
    
    ctx.setVariable("teachername", teacherInfo.getUserName());
    ctx.setVariable("teacheremail", teacherAccount.getMail());
    
   
    ctx.setVariable("Date", new Date());
    ctx.setVariable("appUrl", appUrl);

    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("VerifiedTeacherByAdmin", ctx);
    message.setText(htmlContent, true);
    
    this.mailSender.send(mimeMessage);


  }

  @Override
  public void VerifiedTeacherByAdminToTeacher(UserInfo teacherInfo, UserInfo adminInfo) throws MessagingException {
    String appUrl = getServerAddress();
    
    String subject = "【Pyinnyar Subuu】Teacher Account Verification Successfully Completed! ";

    final Context ctx = new Context();
    UserAccount adminAccount = adminInfo.getUserAccount();
    UserAccount teacherAccount = teacherInfo.getUserAccount();
    String recipientAddress = teacherAccount.getMail();
    
    ctx.setVariable("teachername", teacherInfo.getUserName());
    ctx.setVariable("adminemail", adminAccount.getMail());
    

   
    ctx.setVariable("Date", new Date());
    ctx.setVariable("appUrl", appUrl);

    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("VerifiedTeacherByAdminToTeacher", ctx);
    message.setText(htmlContent, true);
    
    this.mailSender.send(mimeMessage);


  }

  @Override
  public void StudentChangedPassword(UserInfo userInfo, UserAccount userAccount) throws MessagingException {
    String appUrl = getServerAddress();
    
    String subject = "【Pyinnyar Subuu】You have successfully change your password.";

    final Context ctx = new Context();
    String recipientAddress = userAccount.getMail();
    
    ctx.setVariable("studentname", userInfo.getUserName());   
    ctx.setVariable("Date", new Date());
    ctx.setVariable("appUrl", appUrl);

    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);   
    
    final String htmlContent = templateEngine.process("StudentChangedPassword", ctx);
    message.setText(htmlContent, true);
    
    this.mailSender.send(mimeMessage);


  }

  @Override
  public void TeacherChangedPassword(UserInfo userInfo, UserAccount userAccount) throws MessagingException {
    String appUrl = getServerAddress();
    
    String subject = "【Pyinnyar Subuu】You have successfully changed your password.";

    final Context ctx = new Context();
    String recipientAddress = userAccount.getMail();
    
    ctx.setVariable("teachername", userInfo.getUserName());   
    ctx.setVariable("Date", new Date());
    ctx.setVariable("appUrl", appUrl);

    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("TeacherChangedPassword", ctx);
    message.setText(htmlContent, true);
    
    this.mailSender.send(mimeMessage);


  }

  @Override
  public void AdminChangedPassword(UserInfo userInfo, UserAccount userAccount) throws MessagingException {
    String appUrl = getServerAddress();
    
    String subject = "【Pyinnyar Subuu】You have successfully change your password.";

    final Context ctx = new Context();
    String recipientAddress = userAccount.getMail();
    
    // ctx.setVariable("adminname", userInfo.getUserName());   
    ctx.setVariable("Date", new Date());
    ctx.setVariable("appUrl", appUrl);

    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("AdminChangedPassword", ctx);
    message.setText(htmlContent, true);
    
    this.mailSender.send(mimeMessage);

  }

  @Override
  public void AdminResetPassword(UserAccount user) throws MessagingException {
    String appUrl = getServerAddress();
    String token = UUID.randomUUID().toString();
    userService.createToken(user, token, TokenType.PASSWORD_RESET);
    logger.info("Password  reset request from :" + user.getMail());
    String recipientAddress = user.getMail();
    String subject = "【Pyinnyar Subuu】You have requested to change your password.";
    String confirmationUrl = appUrl + "/resetPassword?token=" + token;
    final Context ctx = new Context();
    ctx.setVariable("confirmationUrl", confirmationUrl);
    ctx.setVariable("username", user.getUserInfo().getUserName());
    ctx.setVariable("Date", new Date());
    ctx.setVariable("token", token);
    ctx.setVariable("appUrl", appUrl);
    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("AdminResetPassword", ctx);

    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);

  }

  @Override
  public void TeacherResetPassword(UserAccount user) throws MessagingException {
    String appUrl = getServerAddress();
    String token = UUID.randomUUID().toString();
    userService.createToken(user, token, TokenType.PASSWORD_RESET);
    logger.info("Password  reset request from :" + user.getMail());
    String recipientAddress = user.getMail();
    String subject = "【Pyinnyar Subuu】You have requested to change your password.";
    String confirmationUrl = appUrl + "/resetPassword?token=" + token;
    final Context ctx = new Context();
    ctx.setVariable("confirmationUrl", confirmationUrl);
    ctx.setVariable("username", user.getUserInfo().getUserName());
    ctx.setVariable("Date", new Date());
    ctx.setVariable("token", token);
    ctx.setVariable("appUrl", appUrl);
    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("TeacherResetPassword", ctx);

    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);

  }

  @Override
  public void StudentResetPassword(UserAccount user) throws MessagingException {
    String appUrl = getServerAddress();
    String token = UUID.randomUUID().toString();
    userService.createToken(user, token, TokenType.PASSWORD_RESET);
    logger.info("Password  reset request from :" + user.getMail());
    String recipientAddress = user.getMail();
    String subject = "【Pyinnyar Subuu】You have requested to change your password.";
    String confirmationUrl = appUrl + "/resetPassword?token=" + token;
    final Context ctx = new Context();
    ctx.setVariable("confirmationUrl", confirmationUrl);
    ctx.setVariable("username", user.getUserInfo().getUserName());
    ctx.setVariable("Date", new Date());
    ctx.setVariable("token", token);
    ctx.setVariable("appUrl", appUrl);
    final MimeMessage mimeMessage = mailSender.createMimeMessage();
    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
    message.setSubject(subject);
    message.setFrom("sys@pyinnyar-subuu.com");
    message.setTo(recipientAddress);

    final String htmlContent = templateEngine.process("StudentResetPassword", ctx);

    message.setText(htmlContent, true); // true = isHtml

    this.mailSender.send(mimeMessage);

  }

  @Override
  public String getServerAddress() {
    String appUrl = "Empty";
    // logger.warn(ENVIRONMENT);
    // logger.warn((ENVIRONMENT.equals("production")) + "");
    if (ENVIRONMENT.equals("production")) {
      appUrl = PRODUCTION_DOMAIN;

    }
    if (ENVIRONMENT.equals("testing")) {
      appUrl = TESTING_DOMAIN;

    }
    if (ENVIRONMENT.equals("local")) {
      appUrl = "localhost:8080";

    }
    return appUrl;

  }

  // @Override
  // public void SendAdminNewStudent(UserInfo userInfo, String appUrl) throws
  // MessagingException {
  // // TODO Auto-generated method stub

    // }

}
