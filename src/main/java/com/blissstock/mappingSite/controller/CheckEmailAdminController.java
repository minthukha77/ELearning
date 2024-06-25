// package com.blissstock.mappingSite.controller;

// import org.slf4j.LoggerFactory;

// import javax.validation.Valid;

// import com.blissstock.mappingSite.dto.EmailCheckRegisterDTO;
// import com.blissstock.mappingSite.enums.UserRole;
// import com.blissstock.mappingSite.service.UserService;
// import com.blissstock.mappingSite.service.UserSessionService;

// import org.slf4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.logging.LogLevel;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

// @Controller
// public class CheckEmailAdminController {

// private static final Logger logger =
// LoggerFactory.getLogger(CheckEmailController.class);
// @Autowired
// UserSessionService userSessionService;
// @Autowired
// UserService userService;

// /// A Get Method For Email Check Before Register
// // todo check get mapping path
// @GetMapping(path = { "/check_email/admin/register" })
// public String registerForm(Model model) {
// logger.info("Get Method");
// try {
// UserRole userRole = userSessionService.getRole();
// if (userRole.equals(UserRole.SUPER_ADMIN)) {

// logger.info("User is super admin");
// System.out.println("user is super admin");
// // Initialize Form
// model.addAttribute("action", "register");

// // For Post Method Action
// model.addAttribute("postAction", "/check_email/register");
// logger.info("Get Method");
// EmailCheckRegisterDTO emailCheckRegisterDTO = new EmailCheckRegisterDTO();
// emailCheckRegisterDTO.setEmail("test@gmai.com");
// emailCheckRegisterDTO.setRole("admin");
// model.addAttribute("emailCheck", emailCheckRegisterDTO);
// return "ST0000_check_email";
// }
// } catch (Exception e) {
// logger.debug(e.toString());
// }
// // todo check
// return "403";

// }

// // todo change mappings
// @PostMapping("/check_email/register")
// public String register(
// Model model,
// @Valid @ModelAttribute("emailCheck") EmailCheckRegisterDTO emailRegister,
// BindingResult bindingResult) {
// if (bindingResult.hasErrors()) {
// model.addAttribute("action", "register");

// return "ST0000_check_email.html";
// }
// if (userService.getUserAccountByEmail(emailRegister.getEmail()) != null) {
// // User already exists
// model.addAttribute("action", "register");

// model.addAttribute("error", true);

// return "ST0000_check_email.html";
// }
// UserRole userRole = userSessionService.getRole();
// if (userRole.equals(UserRole.SUPER_ADMIN)) {
// model.addAttribute("email", emailRegister.getEmail());
// model.addAttribute("postAction", "/newAdminRegister");
// model.addAttribute("message", "Do you want to register new admin with mail: "
// + emailRegister.getEmail());
// System.out.println("emailis " + emailRegister.getEmail());
// return "";
// }
// return "403";

// }
// }
