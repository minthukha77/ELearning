package com.blissstock.mappingSite.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import com.blissstock.mappingSite.dto.TeacherRegisterDTO;
import com.blissstock.mappingSite.dto.UserRegisterDTO;
import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.exceptions.UserAlreadyExistException;
import com.blissstock.mappingSite.repository.UserRepository;
import com.blissstock.mappingSite.service.MailService;
import com.blissstock.mappingSite.service.UserService;
import com.blissstock.mappingSite.service.UserSessionService;
import com.blissstock.mappingSite.validation.validators.EmailValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

  private static final Logger logger = LoggerFactory.getLogger(
      RegisterController.class);

  @Autowired
  UserService userService;
  @Autowired
  UserSessionService userSessionService;

  @Autowired
  MailService mailService;

  @Autowired
  UserRepository userRepo;

  @ExceptionHandler(value = ConstraintViolationException.class)
  public String exception(ConstraintViolationException exception) {
    System.out.println("excption occur");
    System.out.println(exception.getMessage());
    return "redirect:/email_check/register/";
  }

  // Redirect to email_check

  @GetMapping("/register")
  public String registerForm() {
    return "redirect:/email_check/register/";
  }

  @PostMapping(path = "/newadmin")
  public String AdminRegister(HttpServletRequest request,
      Model model,
      @ModelAttribute("email") String email, Long uid) {
    System.out.println("emai in newadmin is " + email);

    logger.info("new adim reigister");
    Long adminId = 0L;
    adminId = userSessionService.getUserAccount().getAccountId();
    UserInfo adminInfo = userRepo.findById(adminId).orElse(null);
    // UserInfo teacherInfo = userService.getUserInfoByID(uid);

    if (email != null) {
      UserRole userRole = userSessionService.getRole();
      if (userRole.equals(UserRole.SUPER_ADMIN)) {

        try {
          UserRegisterDTO user = new UserRegisterDTO();

          user.setEmail(email);
          user.setConfirmPassword("admin@test");
          user.setPassword("admin@test");
          user.setRole("admin");
          user.setIsMailVerified(true);
          // UserAccount userAccount = new UserAccount();
          // user.setMail(email);
          // user.setRole(UserRole.ADMIN.getValue());
          // user.setMailVerified(true);
          // user.setPassword("admin");
          // System.out.println(userAccount.toString());
          // System.out.println(userAccount.getMail());

          // userInfo = userAccount.getUserInfo();
          // userInfo.setUserName("Admin");
          // System.out.println(userInfo.getUserName());
          // UserRegisterDTO user = UserRegisterDTO.fromUserInfo(userInfo);

          // save new admin to db
          userService.addUser(user);
          String appUrl = request.getServerName() + // "localhost"
              ":" +
              request.getServerPort();

          // sleap for 1sec
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            logger.info(e.toString());
            Thread.currentThread().interrupt();
          }
          UserAccount userAccount = userService.getUserAccountByEmail(email);

          // Long adminId = 0L;
          // adminId = userSessionService.getUserAccount().getAccountId();
          // UserInfo adminInfo = userRepo.findById(adminId).orElse(null);
          // UserInfo teacherInfo = userService.getUserInfoByID(uid);

          mailService.sendResetPasswordMail(userAccount);
          mailService.SendAdminNewAdmin(userAccount, adminInfo, appUrl);
          mailService.SendSuperAdminNewAdmin(userAccount, adminInfo, appUrl);
          return "redirect:/admin/register/complete";
        } catch (Exception e) {
          logger.info("Register admin :{}", e.toString());
        }
      } else {
        return "redirect:/error/404";
      }

    }
    model.addAttribute("email" + "188tmz199@gmail.com");

    return "confirmation";
    // UserInfo userInfo=new UserInfo();
    // userInfo.setUserAccount(userAccount);
  }

  // Handle User GET Request
  @Valid
  @PreAuthorize("isAnonymous()")
  @GetMapping("/register/{role}/{email}")
  public String registerForm(

      @PathVariable(name = "role", required = false) String role,
      @PathVariable(name = "email") String email,
      Model model) {
    logger.info("GET Request");
    // if email is not validate throw ConstraintViolationException exception
    if (!new EmailValidator().validateEmail(email)) {
      throw new ConstraintViolationException("Invalid Email", null);
    }

    // set role to student unless it is equal to teacher
    if (role == null || !role.equals("teacher")) {
      role = "student";
    }

    UserRegisterDTO userInfo;
    // Initialize UserInfo
    userInfo = role.equals("student") ? new UserRegisterDTO() : new TeacherRegisterDTO();
    userInfo.setEmail(email);
    model.addAttribute("userInfo", userInfo);

    //

    model.addAttribute("task", "Register");
    model.addAttribute("role", role);
    model.addAttribute("postAction", "/register/" + role);

    return "ST0001_register.html";
  }

  @PostMapping(path = "/register/student/{dummyEmail}")
  public String studentRegisterConfirm(
      Model model,
      @Valid @ModelAttribute("userInfo") UserRegisterDTO userInfo,
      BindingResult bindingResult,
      @RequestParam(value = "action", required = true) String action,
      HttpServletRequest request,
      Errors errors) {
    // logger.info("POST Request, action: {}", action);

    // back action redirects to register form
    logger.info("Action value is {}", action);
    if (action.equals("Back")) {
      model.addAttribute("userInfo", userInfo);
      model.addAttribute("task", "Register");
      model.addAttribute("role", "student");
      model.addAttribute("postAction", "/register/" + "student");

      return "ST0001_register.html";
    }

    String role = "student";
    model.addAttribute("userInfo", userInfo);
    model.addAttribute("task", "Register");
    model.addAttribute("role", role);
    model.addAttribute("postAction", "/register/" + role);

    try {
      if (action.equals("submit")) {
        userInfo.setAcceptTerm(true);
        try {
          UserInfo savedUserInfo = userService.addUser(userInfo);

          new Thread(new Runnable() {
            public void run() {
              try {

                String appUrl = request.getServerName() + // "localhost"
                    ":" +
                    request.getServerPort(); // "8080"
                mailService.sendVerificationMail(
                    savedUserInfo.getUserAccount());

                mailService.SendAdminNewStudent(savedUserInfo);

              } catch (MessagingException e) {
                logger.info(e.toString());
              }
            }
          }).start();

          return "redirect:/studentAccount/register/complete";
        } catch (UserAlreadyExistException e) {
          e.printStackTrace();
          model.addAttribute("userExistError", true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    if (bindingResult.hasErrors()) {
      // logger.info("Validation Error: ", bindingResult.getFieldError());
      return "ST0001_register.html";
    }
    // Information For Randering Confirm
    model.addAttribute("infoMap", userInfo.toMap());
    return "ST0001_register.html";
  }

  @PostMapping(path = "/register/teacher/{dummyEmail}")
  public String teacherRegisterConfirm(
      Model model,
      @Valid @ModelAttribute("userInfo") TeacherRegisterDTO userInfo,
      BindingResult bindingResult,
      @RequestParam(value = "action", required = true) String action,
      HttpServletRequest request,
      Errors errors) {
    // back action redirects to register form
    logger.info("Action value is {}", action);
    if (action.equals("Back")) {

      model.addAttribute("userInfo", userInfo);
      model.addAttribute("task", "Register");
      model.addAttribute("role", "teacher");
      model.addAttribute("postAction", "/register/" + "teacher");
      return "ST0001_register.html";
    }
    String role = "teacher";
    model.addAttribute("userInfo", userInfo);
    model.addAttribute("task", "Register");
    model.addAttribute("role", role);
    model.addAttribute("postAction", "/register/" + role);

    try {
      if (action.equals("submit")) {
        userInfo.setAcceptTerm(true);
        try {
          UserInfo savedUserInfo = userService.addUser(userInfo);

          new Thread(new Runnable() {
            public void run() {
              try {

                String appUrl = request.getServerName() + // "localhost"
                    ":" +
                    request.getServerPort(); // "8080"
                mailService.sendVerificationMail(
                    savedUserInfo.getUserAccount());

                mailService.SendAdminNewTeacher(savedUserInfo);

              } catch (MessagingException e) {
                logger.info(e.toString());
              }
            }
          }).start();

          return "redirect:/teacherAccount/register/complete";
        } catch (UserAlreadyExistException e) {
          e.printStackTrace();
          model.addAttribute("userExistError", true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    if (bindingResult.hasErrors()) {
      return "ST0001_register.html";
    }
    // Information For Randering Confirm
    model.addAttribute("infoMap", userInfo.toMap());
    return "ST0001_register.html";
  }
}