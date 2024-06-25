package com.blissstock.mappingSite.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.blissstock.mappingSite.dto.PaymentInfoDTO;
import com.blissstock.mappingSite.entity.BankInfo;
import com.blissstock.mappingSite.entity.PaymentAccount;

import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.enums.AccountStatus;
import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.exceptions.UnauthorizedFileAccessException;
import com.blissstock.mappingSite.exceptions.UserNotFoundException;
import com.blissstock.mappingSite.model.FileInfo;
import com.blissstock.mappingSite.model.Message;
import com.blissstock.mappingSite.service.PaymentInfoService;
import com.blissstock.mappingSite.service.StorageService;
import com.blissstock.mappingSite.service.StorageServiceImpl;
import com.blissstock.mappingSite.service.UserAccountControlService;
import com.blissstock.mappingSite.service.UserService;
import com.blissstock.mappingSite.service.UserSessionService;
import com.blissstock.mappingSite.utils.CheckUploadFileType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.blissstock.mappingSite.service.MailService;
import com.blissstock.mappingSite.repository.PaymentRepository;
import com.blissstock.mappingSite.repository.UserRepository;

import com.blissstock.mappingSite.entity.JoinCourseUser;
import com.blissstock.mappingSite.service.JoinCourseUserService;
import com.blissstock.mappingSite.model.CourseDataWithPayment;

@Controller
public class ProfileController {

  private static Logger logger = LoggerFactory.getLogger(
      ProfileController.class);

  @Autowired
  MailService mailService;

  @Autowired
  StorageService storageService;

  @Autowired
  public ProfileController(StorageService storageService) {
    this.storageService = storageService;
  }

  @Autowired
  UserSessionService userSessionService;

  @Autowired
  UserAccountControlService userAccountControlService;

  @Autowired
  UserService userService;

  @Autowired
  PaymentInfoService paymentInfoService;

  @Autowired
  UserRepository userRepo;

  @Autowired
  CourseListController courseListController;

  @Autowired
  JoinCourseUserService joinCourseUserService;

  @Autowired
  PaymentRepository paymentRepo;

  @GetMapping(value = {
      "/student/profile/",
      "/teacher/profile/",
      "/admin/browse/profile/{id}",
      "/student/browse/profile/{id}",
      "/teacher/browse/profile/{id}",
  })
  private String viewProfile(
      Model model,
      @PathVariable(required = false) Long id,
      String message,
      String error) {
    logger.info("GET Requested");

    // Setting User Id
    long uid = getUid(id);
    model.addAttribute("id", uid);

    // Setting isEditable
    boolean isEditable = isEditable(id);
    model.addAttribute("isEditable", isEditable);

    // ##################################################//
    // Display Message//
    if (error != null) {
      logger.debug("Error Message: {}", error);
      Message messageInfo = new Message();
      messageInfo.setError(true);
      messageInfo.setText(Message.ERROR);
      model.addAttribute("message", messageInfo);
    } else if (message != null) {
      logger.debug("Message: {}", message);
      Message messageInfo = new Message();
      messageInfo.setError(false);
      if (message.equals("profileEdit")) {
        messageInfo.setText(Message.PROFILE_EDIT_SUCCESSFULLY);
      } else if (message.equals("paymentInfo")) {
        messageInfo.setText(Message.Payment_INFO_UPDATE_SUCCESSFULLY);
      }
      model.addAttribute("message", messageInfo);
    }

    // ############################################

    // ##################################################//
    // Load User Information//

    UserInfo userInfo;
    try {
      userInfo = userService.getUserInfoByID(uid);
    } catch (Exception e) {
      e.printStackTrace();
      logger.info("User {} does not exist", uid);
      return "redirect:/404";
    }
    // ##################################################//

    // ##################################################//

    // get Status
    AccountStatus status = AccountStatus.strToAccountStatus(userInfo.getUserAccount().getAccountStatus());
    logger.info("requested user status is {}", status);

    // Check Role
    UserRole role = UserRole.strToUserRole(userInfo.getUserAccount().getRole());
    logger.debug("requested user role is {}", role.getValue());

    if (role == UserRole.ADMIN || role == UserRole.SUPER_ADMIN) {
      logger.info("User does not have sufficient auth to access uuid:{}", uid);
      return "redirect:/404";
    }

    // ##################################################//

    // ##################################################//
    // Load Profile
    try {
      FileInfo profilePic = storageService.loadProfileAsFileInfo(userInfo);
      model.addAttribute("profilePic", profilePic);
    } catch (Exception e) {
      e.printStackTrace();
      logger.info("unable to get profile {}", uid);
    }

    // ##################################################//

    // ##################################################//
    // Load Certificate
    if (role == UserRole.TEACHER) {
      // load certificates
      List<FileInfo> certificateFiles = storageService.loadCertificatesAsFileInfo(uid);
      logger.info("User have certificates: {}", certificateFiles.size());
      model.addAttribute("certificateFiles", certificateFiles);
    }
    // ##################################################//

    // ##################################################//
    // Load Certificate
    if (isEditable) {
      // load Support Payment Info
      List<BankInfo> bankList = paymentInfoService.getSupportedPaymentMethods();
      // logger.debug("BankList {}",bankList);
      model.addAttribute("bankList", bankList);

      List<PaymentAccount> paymentAccounts = paymentInfoService.getPaymentInfo(
          userInfo);
      logger.debug("added payment accounts {}", paymentAccounts);
      model.addAttribute("paymentAccounts", paymentAccounts);

      boolean isPaymentEditable = isEditable;

      PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO();
      if (paymentAccounts.size() == 2) {
        paymentInfoDTO.setPrimaryAccount(paymentAccounts.get(0));
        paymentInfoDTO.setSecondaryAccount(paymentAccounts.get(1));
        if (userSessionService.getRole() == UserRole.TEACHER) {
          isPaymentEditable = false;
          /* The teacher can only edit payment information one */
        }
      }
      model.addAttribute("paymentInfoDTO", paymentInfoDTO);
      model.addAttribute("isPaymentEditable", isPaymentEditable);
      // model.addAttribute("isPaymentEditable", true);
    }
    // ##################################################//

    model.addAttribute("role", role.getValue());
    model.addAttribute("infoMap", userInfo.toMap(false));
    model.addAttribute("status", status.getValue());

    logger.debug("model attribute, isEditable: {}", isEditable(uid));

    // Load Information for student joined courses and payment status.

    List<JoinCourseUser> joinCourseUsers = joinCourseUserService.findByUserInfo(userInfo);
    if (joinCourseUsers == null) {
      joinCourseUsers = new ArrayList<>();

    }
    List<CourseDataWithPayment> courseDataList = joinCourseUsers.stream().map((e) -> {
      CourseDataWithPayment c = CourseDataWithPayment.construct(e.getCourseInfo(), e.getPaymentReceivesByList(),
          e.getUserInfo());
      FileInfo fileInfo = storageService.loadCoursePhoto(e.getCourseInfo());

      // if profile is not found set as place holder
      if (fileInfo == null) {
        c.setCoursePhoto(new FileInfo("https://via.placeholder.com/150",
            "https://via.placeholder.com/150"));
      } else {

        c.setCoursePhoto(fileInfo);
      }
      return c;
    }).collect(Collectors.toList());

    model.addAttribute("courseList", courseDataList);

    // ##################################################//

    return "CM0004_Profile";
  }

  @PostMapping(value = {
      "/teacher/profile/edit/payment",
      "/admin/browse/profile/{id}/edit/payment/"
  })
  public String editPayment(
      Model model,
      @ModelAttribute PaymentInfoDTO paymentInfoDTO,
      @PathVariable(required = false) Long id,
      HttpServletRequest httpServletRequest,
      BindingResult bindingResult) {
    logger.info("Post Requested");
    logger.info("Payment Edit Info {}", paymentInfoDTO);

    if (bindingResult.hasErrors()) {
      logger.debug("error: {}", bindingResult.getAllErrors());
    }
    Long uid = getUid(id);

    List<PaymentAccount> paymentAccounts = new ArrayList<>();
    paymentAccounts.add(paymentInfoDTO.getPrimaryAccount());
    paymentAccounts.add(paymentInfoDTO.getSecondaryAccount());

    String redirectAddress = "redirect:" +
        httpServletRequest.getRequestURI().replace("/edit/payment", "");

    logger.debug("Redirect Addresss {}", redirectAddress);

    try {
      paymentInfoService.updatePayment(paymentAccounts, uid);
      return redirectAddress + "?message=paymentInfo";
    } catch (UserNotFoundException e) {
      e.printStackTrace();
      return redirectAddress + "?error";
    }
  }

  @PostMapping(value = {
      "/student/profile/edit/profile-pic/",
      "/teacher/profile/edit/profile-pic/",
      "/admin/browse/profile/{id}/edit/profile-pic/",
  })
  public String editProfilePicture(
      Model model,
      @RequestParam("profilePic") MultipartFile photo,
      @PathVariable(required = false) Long id,
      HttpServletRequest httpServletRequest) {
    logger.info("Post Requested");
    logger.info("Payment Edit Info {}", photo);

    Long uid = getUid(id);

    String redirectAddress = "redirect:" +
        httpServletRequest.getRequestURI().replace("/edit/profile-pic", "");
    logger.debug("Redirect Addresss {}", redirectAddress);

    if (!photo.isEmpty() && CheckUploadFileType.checkType(photo)) {
      // get original photo name and generate a new file name
      String originalFileName = StringUtils.cleanPath(
          photo.getOriginalFilename());

      // upload photo
      try {

        storageService.store(uid, photo, StorageServiceImpl.PROFILE_PATH, true);
        UserInfo userInfo = userService.getUserInfoByID(uid);
        userInfo.setPhoto(originalFileName);
        userService.updateUserInfo(userInfo);
      } catch (UnauthorizedFileAccessException e) {
        e.printStackTrace();
      }

      logger.info("profile photo {} stored", originalFileName);
      return redirectAddress + "?message=paymentInfo";
    }

    return redirectAddress + "?error";
  }

  @DeleteMapping("/admin/profile/delete")
  public ResponseEntity<Object> deleteUser(
      Model model,
      Long uid,
      HttpServletRequest httpServletRequest) {
    logger.info("DELETE Request for user {}", uid);
    try {
      UserInfo userInfo = userService.getUserInfoByID(uid);
      if (userInfo == null) {
        throw new UserNotFoundException();
      }
      userAccountControlService.deleteUser(userInfo);
    } catch (UserNotFoundException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Found");
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }

    return ResponseEntity.status(HttpStatus.OK).body("operation success");
  }

  @GetMapping("/admin/profile/delete/{id}")

  public String deleteAdminAccount(@PathVariable(value = "id") long uid, Model model,
      HttpServletRequest httpServletRequest) {

    try {
      UserInfo userInfo = userService.getUserInfoByID(uid);
      if (userInfo == null) {
        throw new UserNotFoundException();
      }
      userAccountControlService.deleteUser(userInfo);
    } catch (UserNotFoundException e) {

    }
    return "redirect:/admin/admin-list";
  }

  @PostMapping("admin/profile/suspend")
  public ResponseEntity<Object> suspendUser(
      Model model,
      Long uid,
      HttpServletRequest httpServletRequest) {
    logger.info("Suspend request for user with id {}", uid);

    try {
      UserInfo userInfo = userService.getUserInfoByID(uid);
      if (userInfo == null) {
        throw new UserNotFoundException();
      }
      userAccountControlService.suspendUser(userInfo);
    } catch (UserNotFoundException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Found");
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }

    return ResponseEntity.status(HttpStatus.OK).body("operation success");

  }

  @PostMapping("admin/profile/reactivate")
  public ResponseEntity<Object> reactivateUser(
      Model model,
      Long uid,
      HttpServletRequest httpServletRequest) {
    logger.info("Reactivate request for user with id {}", uid);

    try {
      UserInfo userInfo = userService.getUserInfoByID(uid);
      if (userInfo == null) {
        throw new UserNotFoundException();
      }
      userAccountControlService.reactivateUser(userInfo);
    } catch (UserNotFoundException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Found");
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }

    return ResponseEntity.status(HttpStatus.OK).body("operation success");

  }

  @PostMapping("admin/profile/verify")
  public ResponseEntity<Object> verifyUser(
      Model model,
      Long uid,

      HttpServletRequest request) {
    logger.info("Verify request for user with id {}", uid);
    Long adminId = 0L;
    adminId = userSessionService.getUserAccount().getAccountId();
    UserInfo adminInfo = userRepo.findById(adminId).orElse(null);
    UserInfo teacherInfo = userService.getUserInfoByID(uid);
    try {
      UserInfo userInfo = userService.getUserInfoByID(uid);
      if (userInfo == null) {
        throw new UserNotFoundException();
      }
      userAccountControlService.verifyUser(userInfo);
    } catch (UserNotFoundException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Found");
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }

    new Thread(new Runnable() {
      public void run() {
        try {

          // UserInfo adminInfo = userService.getUserInfoByID(adminId);

          String appUrl = request.getServerName() + // "localhost"
              ":" +
              request.getServerPort(); // "8080"
          // mailService.sendVerificationMail(
          // savedUserInfo.getUserAccount(),
          // appUrl);

          mailService.VerifiedTeacherByAdmin(teacherInfo, adminInfo);
          mailService.VerifiedTeacherByAdminToTeacher(teacherInfo, adminInfo);

        } catch (Exception e) {
          logger.info(e.toString());
        }
      }
    }).start();

    return ResponseEntity.status(HttpStatus.OK).body("operation success");

  }

  private boolean isEditable(Long id) {
    UserRole role = userSessionService.getRole();
    if (role == UserRole.ADMIN || role == UserRole.SUPER_ADMIN) {
      return true;
    }
    if (role == UserRole.TEACHER || role == UserRole.STUDENT) {
      logger.debug(
          "request id {}, session id {}",
          id,
          userSessionService.getId());
      logger.debug(
          "userSessionService.getId() == id : {}",
          userSessionService.getId().equals(id));
      if (id != null) {
        return false;
      }
      return userSessionService.getId().equals(getUid(id));
    }
    return false;
  }

  private Long getUid(Long id) {
    Long uid = 0L;
    UserRole role = userSessionService.getRole();
    if (role == UserRole.ADMIN || role == UserRole.SUPER_ADMIN) {
      uid = id;
    } else if (id != null) {
      uid = id;
    } else if (role == UserRole.TEACHER || role == UserRole.STUDENT) {
      uid = userSessionService.getUserAccount().getAccountId();
    } else {
      throw new RuntimeException("user authetication fail");
    }
    return uid;
  }

}
