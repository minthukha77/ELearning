package com.blissstock.mappingSite.controller;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.blissstock.mappingSite.dto.PasswordDTO;
import com.blissstock.mappingSite.entity.Token;
import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.enums.PasswordResetType;
import com.blissstock.mappingSite.enums.TokenType;
import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.service.MailServiceImpl;
import com.blissstock.mappingSite.service.UserService;
import com.blissstock.mappingSite.service.UserSessionService;
import com.fasterxml.jackson.core.sym.Name;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.blissstock.mappingSite.repository.UserRepository;
import com.blissstock.mappingSite.entity.UserInfo;

@Controller
public class PasswordController {

  /**
   *
   */
  private static final String REDIRECT_LOGIN_RESET_SUCCESS_TRUE = "redirect:/login?resetSuccess=true";

  private static final Logger logger = LoggerFactory.getLogger(
      PasswordController.class);

  @Autowired
  HttpServletRequest httpServletRequest;
  @Autowired
  UserService userService;
  @Autowired
  UserSessionService userSessionService;
  @Autowired
  MailServiceImpl mailService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  UserRepository userRepo;

  @GetMapping("/password/encrypt")
  public String encrypt(Model model, String pass) {
    String password = passwordEncoder.encode(pass);
    System.out.println(password);
    return "redirect:/";
  }

  /*
   * @GetMapping("/token")
   * public String createToken(Model model) {
   * String token = UUID.randomUUID().toString();
   * UserAccount userAccount = userService.getUserAccountByEmail(
   * "lycuzmarki@gmail.com"
   * );
   * ////system.out.println(userAccount);
   * userService.createToken(userAccount, token, TokenType.VERIFICATION);
   * return "redirect:/";
   * }
   */
  @GetMapping("resetPassword")
  public String verifyPassword(Model model, @RequestParam(value = "token", required = true) String token) {
    logger.info("GET requested");
    logger.info("Reset password by token called");
    model.addAttribute("title", "Change");
    if (token == null) {
      model.addAttribute("error", "The password reset mail is invalid! Please check the mail again.");
      return "CM006_reset_password_screen";

    }
    UserAccount userAccount = userService.getUserAccountByToken(token,
        TokenType.PASSWORD_RESET.getValue());
    if (userAccount == null) {
      System.out.println("Invalid token");
      String header3 = "Invalid token";
      String header5 = "";
      String paragraph = "The password reset mail is invalid! Please check the mail again.";
      model.addAttribute("status", "invalid");
      model.addAttribute("header3", header3);
      model.addAttribute("header5", header5);
      model.addAttribute("paragraph", paragraph);
      return "MailVerify.html";
    } else {
      PasswordDTO passwordDTO = new PasswordDTO();
      model.addAttribute("passwordDTO", passwordDTO);
      passwordDTO.setOldPassword(token);
      model.addAttribute("token", token);
      return "CM006_reset_password_screen";

    }

    // Token savedToken = userService.getToken(token, TokenType.VERIFICATION);
    // if (savedToken == null) {
    // // //System.out.println("invalid token");
    // return "redirect:/login?tokenError";
    // } else {
    // UserAccount savedUserAccount = savedToken.getUserAccount();
    // savedUserAccount.setMailVerified(true);
    // userService.updateUserAccount(savedUserAccount);
    // savedToken.setUsed(true);
    // userService.updateToken(savedToken);
    // }

    // //System.out.println(token);
    // return "redirect:/home";
  }

  @PostMapping("resetPassword")
  public String changePassword(Model model, @Valid @ModelAttribute("passwordDTO") PasswordDTO passwordDTO,
      @ModelAttribute("token") String token,
      BindingResult bindingResult) {
    logger.info("Post Method");
    model.addAttribute("title", "Change");

    model.addAttribute("passwordDTO", passwordDTO);
    // System.out.println(passwordDTO.toString());
    if (bindingResult.hasErrors()) {
      logger.warn("validation error, {}", bindingResult.getFieldError());

      model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
      return "CM006_reset_password_screen";
    }
    logger.info("password binding is success");

    if (!passwordDTO.getPassword().equals(passwordDTO.getConfirmPassword())) {
      logger.info("Password and conform password do not match");

      model.addAttribute("error", "password and confirm password should match");
      return "CM006_reset_password_screen";
    } else {
      UserAccount userAccount = userService.getUserAccountByToken(token, TokenType.PASSWORD_RESET.getValue());
      if (userAccount == null) {

        model.addAttribute("error", "The password reset mail is invalid! Please check the mail again.");
        return "CM006_reset_password_screen";

      }
      userAccount.setPassword(passwordEncoder.encode(passwordDTO.getPassword()));
      userService.updateUserAccount(userAccount);
      userService.setAsUsedToken(token);
      //
      try {
        httpServletRequest.logout();
        return "redirect:/login?changeSuccess=true";
      } 
      
      catch (ServletException e) {

        return "CM0006_change_password_screen";
      }
    }

  }

  @RequestMapping("password/reset_password")
  public String resetPassword(
      HttpServletRequest request,
      @RequestParam("email") String userEmail) {
    logger.info("POST requested, email {}", userEmail);
    UserAccount user = userService.getUserAccountByEmail(userEmail);
    
    if (user == null) {
      return ("redirect:/check_email/reset_password?email=" +
          userEmail +
          "&error=true");
    }
    
    String resetaccountrole = user.getRole();

    /* String token = UUID.randomUUID().toString(); */
    // String appUrl = request.getServerName() + // "localhost"
    //     ":" +
    //     request.getServerPort();
    /* userService.createToken(user, token, TokenType.PASSWORD_RESET); */
    
   
        try {
          if (resetaccountrole.equals("ROLE_ADMIN")) { 
            
            mailService.AdminResetPassword(user);             

          }

          if (resetaccountrole.equals("ROLE_TEACHER")) { 
            
            mailService.TeacherResetPassword(user);   

            }

            if (resetaccountrole.equals("ROLE_STUDENT")) {  
              
             mailService.StudentResetPassword(user);

            }
          
        } catch (Exception e) {
          logger.info(e.toString());
        }
       
    
    // try {
      
    //   mailService.sendResetPasswordMail(user, appUrl);
    // } catch (MessagingException e) {
    //   logger.info(e.toString());
    // }
    return REDIRECT_LOGIN_RESET_SUCCESS_TRUE;
  }
  

  @GetMapping(path = { "{role}/change_password" })
  public String changePasswordView(
      Model model,
      @PathVariable(name = "role", required = true) String role,
      String token) {
    // //System.out.println("change password called");
    // Role being null meaning user is trying to reset password
    logger.info("GET requested, role {}", role);
    if (role != null &&
        !(role.equals("student") || role.equals("teacher") || role.equals("admin"))) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "entity not found");
    }

    PasswordDTO passwordDTO = new PasswordDTO();
    String title = "";

    if (role == null) {
      // This is the password reset case by token
      passwordDTO.setType(PasswordResetType.TOKEN.name());
      title = "Reset Password";
      boolean isTokenValid = true;

      if (token == null) {
        isTokenValid = false;
      } else {
        Token savedToken = userService.getToken(
            token,
            TokenType.PASSWORD_RESET);
        if (savedToken == null) {
          isTokenValid = false;
        } else {
          // If token is valid, set it as old password for further use.
          passwordDTO.setOldPassword(token);
        }
      }

      if (!isTokenValid) {
        model.addAttribute("error", "Invalid Token");
      }
    } else {
      // This is the password reset case by old password
      passwordDTO.setType(PasswordResetType.OLD_PASSWORD.name());
      title = "Change Password";
    }

    model.addAttribute("title", title);
    model.addAttribute("passwordDTO", passwordDTO);
    logger.trace("Title {}", model.getAttribute("title"));

    return "CM0006_change_password_screen";
  }

  @PostMapping("{role}/change_password")
  public String changePasswordPost(
      Model model,
      @Valid @ModelAttribute("passwordDTO") PasswordDTO passwordDTO,
      BindingResult bindingResult,@PathVariable(name = "role", required = true) String role,
      String token,HttpServletRequest request) {

    String title = "Change Password";
    model.addAttribute("type", "OLD_PASSWORD");
    model.addAttribute("title", title);
    model.addAttribute("passwordDTO", passwordDTO);

    if (bindingResult.hasErrors()) {
      logger.warn("validation error, {}", bindingResult.getFieldError());

      model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
      return "CM0006_change_password_screen";
    }
    logger.info("password binding is success");

    // //System.out.println(passwordDTO.getPassword());
    // //System.out.println(passwordDTO.getConfirmPassword());
    // //System.out.println(passwordDTO.getOldPassword());

    if (!passwordDTO.getPassword().equals(passwordDTO.getConfirmPassword())) {
      logger.info("Password and conform password do not match");

      model.addAttribute("error", "password and confirm password should match");
      return "CM0006_change_password_screen";
    }
    // get user email

    UserAccount userAccount = userSessionService.getUserAccount();
    Long userId = userSessionService.getUserAccount().getAccountId();
    UserInfo userInfo = userRepo.findById(userId).orElse(null);              

    if (passwordEncoder.matches(passwordDTO.getOldPassword(), userAccount.getPassword())) {

      userAccount.setPassword(passwordEncoder.encode(passwordDTO.getPassword()));
      userService.updateUserAccount(userAccount);

      model.addAttribute("success", "Password Change Success");
      model.addAttribute("message", "Please login with new password to continue");

      new Thread(new Runnable() {
        public void run() {
          try {
            if (role.equals("student")) { 
              
              String appUrl = request.getServerName() + // "localhost"
              ":" +
              request.getServerPort(); // "8080"
              
              mailService.StudentChangedPassword(userInfo, userAccount);              
              
            }

            if (role.equals("teacher")) { 
              
              String appUrl = request.getServerName() + // "localhost"
              ":" +
              request.getServerPort(); // "8080" 
              
              mailService.TeacherChangedPassword(userInfo, userAccount);

              }

              if (role.equals("admin")) {  
               
                String appUrl = request.getServerName() + // "localhost"
              ":" +
              request.getServerPort(); // "8080" 
              
              mailService.AdminChangedPassword(userInfo, userAccount);

              }
            
          } catch (Exception e) {
            logger.info(e.toString());
          }
        }
      }).start();

      // todo navigate to complete screen;
      // model.addAttribute("title", "Login");
      try {
        httpServletRequest.logout();
        return "redirect:/login?changeSuccess=true";
      } catch (ServletException e) {

        return "CM0006_change_password_screen";
      }

      // return "CM0005_login.html";
    } else {
      logger.info(" Password and stored password do not match");

      model.addAttribute("error", "Please enter the correct old password");
      return "CM0006_change_password_screen";

    }

  }
}
