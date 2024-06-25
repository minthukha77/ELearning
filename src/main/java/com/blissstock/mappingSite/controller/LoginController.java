package com.blissstock.mappingSite.controller;

import com.blissstock.mappingSite.dto.LoginDTO;
import com.blissstock.mappingSite.service.MailService;
import com.blissstock.mappingSite.service.UserService;
import com.blissstock.mappingSite.service.UserSessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  @Autowired
  UserSessionService userSessionService;

  @Autowired
  UserService userService;

  @Autowired
  MailService mailService;

  @GetMapping("/login")
  public String loginView(
      Model model,
      String error,
      String logout,
      String message,
      String changeSuccess,
      String resetSuccess,
      String tokenError) {
    /*
     * if(userSessionService.isAuthenticated()){
     * return "redirect:/home";
     * }
     */
    if (resetSuccess != null) {
      message = "A password reset link has been sent to your email. Please check your email to continue.";
    }
    if (changeSuccess != null) {
      message = "Your password has been changed successfully please login with new password to continue.";
    }
    if (tokenError != null) {
      error = "invalid token";
    }
    if (error != null) {
      if (error.isBlank()) {
        model.addAttribute("error", "Your email and password is invalid.");
      } else if (error.equals("suspended")) {
        model.addAttribute("error", "Your account has been suspended.");
      } else if (error.equals("non-email")) {
        model.addAttribute("error",
            "Please verify the email first! We have sent a verification mail. Please kindly check your inbox.");
      } else {
        model.addAttribute("error", error);
      }
    }
    if (logout != null) {
      model.addAttribute("message", "You have been logged out successfully");
    }
    if (message != null) {
      model.addAttribute("message", message);
    }
    model.addAttribute("userInfo", new LoginDTO());
    return "CM0005_login.html";
  }
  /*
   * @PostMapping(value = "/login/reset_password")
   * public ResponseEntity<Object> resetPassword(
   * String email,
   * HttpServletRequest request
   * ) {
   * System.out.println("Login called");
   * System.out.println(email);
   * 
   * UserAccount userAccount = userService.getUserAccountByEmail(email);
   * if (userAccount == null) {
   * return ResponseEntity
   * .status(HttpStatus.BAD_REQUEST)
   * .body("The email address is not registered in the system.");
   * }
   * 
   * String appUrl =
   * request.getServerName() + // "localhost"
   * ":" +
   * request.getServerPort(); // 8080
   * 
   * try {
   * mailService.sendResetPasswordMail(userAccount, appUrl);
   * } catch (Exception e) {
   * e.printStackTrace();
   * return ResponseEntity
   * .status(HttpStatus.INTERNAL_SERVER_ERROR)
   * .body("Something went wrong");
   * }
   * 
   * return ResponseEntity
   * .status(HttpStatus.OK)
   * .body(
   * "A password reset link has been sent to your email. Please check your email to continue."
   * );
   * }
   */
}
