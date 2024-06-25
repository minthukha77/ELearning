package com.blissstock.mappingSite.controller;

import com.blissstock.mappingSite.dto.EmailCheckRegisterDTO;
import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.service.UserService;
import com.blissstock.mappingSite.service.UserSessionService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckEmailController {

  private static final Logger logger = LoggerFactory.getLogger(CheckEmailController.class);

  @Autowired
  UserService userService;

  @Autowired
  UserSessionService userSessionService;

  /// A Get Method For Email Check Before Register
  @GetMapping(path = { "/check_email/register/{role}" })
  public String registerForm(
      @PathVariable(name = "role", required = false) String role,
      Model model,
      String email) {
    logger.info("Role is {}, email is {}", role, email);
    // Tell Thymeleaf to render as Reister
    model.addAttribute("action", "register");

    // For Post Method Action
    model.addAttribute("postAction", "/check_email/register/");

    /*
     * if (email != null && !email.isBlank()) {
     * EmailValidator emailValidator = new EmailValidator();
     * boolean isValidEmail = emailValidator.validateEmail(email);
     * if (isValidEmail) {
     * model.addAttribute("email", email);
     * }
     * }
     */

    // Initialize Form

    EmailCheckRegisterDTO emailCheckRegisterDTO = new EmailCheckRegisterDTO();

    if (role.equals("admin")) {
      UserRole userRole = userSessionService.getRole();
      if (userRole.equals(UserRole.SUPER_ADMIN)) {

        logger.info("User is super admin");
        System.out.println("user is super admin");
        // Initialize Form

        logger.info("Get Method");

        emailCheckRegisterDTO.setEmail(email);
        emailCheckRegisterDTO.setRole("admin");
        model.addAttribute("emailCheck", emailCheckRegisterDTO);
        return "ST0000_check_email";

      } else {
        return "error/404";
      }
    }
    // if (role == null || !role.equals("teacher") || !role.equals("admin")) {
    //   role = "student";
    // }

    logger.debug("Role is {}", role);

    emailCheckRegisterDTO.setEmail(email);
    emailCheckRegisterDTO.setRole(role);

    model.addAttribute("emailCheck", emailCheckRegisterDTO);

    // render
    return "ST0000_check_email";
  }

  // A Post Method for email check before Register
  @PostMapping("/check_email/register")
  public String register(
      Model model,
      @Valid @ModelAttribute("emailCheck") EmailCheckRegisterDTO emailRegister,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      logger.warn(
          "Invalid Form Field error {},  error count:{}",
          bindingResult.getFieldError(),
          bindingResult.getFieldErrorCount());
      model.addAttribute("action", "register");
      /*
       * // For Rendering the title
       * model.addAttribute("role", emailRegister.getRole());
       */
      // render
      return "ST0000_check_email.html";
    }
    try {
      UserAccount userAccount = userService.getUserAccountByEmail(
          emailRegister.getEmail());
      logger.debug("userAccount: ", userAccount);
      if (userService.getUserAccountByEmail(emailRegister.getEmail()) != null) {
        logger.warn(
            "user with {} email already exists",
            emailRegister.getEmail());
        // User already exists
        model.addAttribute("action", "register");
        /*
         * // For Rendering the title
         * model.addAttribute("role", emailRegister.getRole());
         */
        model.addAttribute("error", true);
        // render
        return "ST0000_check_email.html";
        // For displaying error message

      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Redirect to Register
    // Two Valid Address:
    // 1. /register/student/email@gmail.com
    // 2. /register/teacher/email@gmail.com
    // todo check admin register
    System.out.println("email is {},role is {}" + emailRegister.getRole());
    if (emailRegister.getRole().equals("admin")) {
      UserRole userRole = userSessionService.getRole();
      if (userRole.equals(UserRole.SUPER_ADMIN)) {
        model.addAttribute("email", emailRegister.getEmail());
        model.addAttribute("postAction", "/newadmin");
        model.addAttribute("message", "Do you want to register new admin with mail: "
            + emailRegister.getEmail());
        System.out.println("emailis " + emailRegister.getEmail());
      } else {
        return "redirect:/error/404";
      }
      return ("confirmation");
    }
    return ("redirect:/register/" +
        emailRegister.getRole() +
        "/" +
        emailRegister.getEmail() +
        "/");
  }

  /// A Get Method For Email Check Before Register
  @GetMapping(path = { "/check_email/reset_password" })
  public String passwordResetForm(Model model, String error, String email) {
    // Tell Thymeleaf to render as Reister
    model.addAttribute("action", "verify_password");
    EmailCheckRegisterDTO dto = new EmailCheckRegisterDTO();
    if (email != null) {
      dto.setEmail(email);
    }
    // For Post Method Action
    model.addAttribute("postAction", "/password/reset_password/");
    model.addAttribute("emailCheck", dto);

    model.addAttribute("passwordResetError", error);

    // render
    return "ST0000_check_email";
  }

  @PostMapping(path = { "/check_email/reset_password" })
  public String passwordReset(Model model, BindingResult bindingResult) {
    // Tell Thymeleaf to render as Reister
    if (bindingResult.hasErrors()) {
      model.addAttribute("action", "verify_password");

      // For Post Method Action
      model.addAttribute("postAction", "/check_email/reset_password/");
      model.addAttribute("emailCheck", new EmailCheckRegisterDTO());

      // render
      return "ST0000_check_email";
    }
    return "redirect:/password/reset_password";
  }
}
