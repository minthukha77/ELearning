package com.blissstock.mappingSite.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.exceptions.UnauthorizedFileAccessException;
import com.blissstock.mappingSite.model.FileInfo;
import com.blissstock.mappingSite.repository.UserAccountRepository;
import com.blissstock.mappingSite.repository.UserRepository;
import com.blissstock.mappingSite.service.StorageService;
import com.blissstock.mappingSite.service.StorageServiceImpl;
import com.blissstock.mappingSite.service.UserSessionService;
import com.blissstock.mappingSite.utils.CheckUploadFileType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminTopController {

  private static Logger logger = LoggerFactory.getLogger(
      AdminTopController.class);

  @Autowired
  StorageService storageService;

  @Autowired
  public AdminTopController(StorageService storageService) {
    this.storageService = storageService;
  }

  @Autowired
  UserSessionService userSessionService;

  @Autowired
  UserRepository userRepo;

  @Autowired
  UserAccountRepository userAccRepo;

  @GetMapping(value = "/admin/top/")
  private String getAdminTopScreen(Model model) {
    Long userId = userSessionService.getUserAccount().getAccountId();
    UserInfo userInfo = userRepo.findById(userId).orElse(null);
    UserAccount userAcc = userInfo.getUserAccount();

    logger.info("GET Requested");
    logger.info("Getting admininfo: {} from database", userId);
    Optional<UserInfo> optionalUserInfo = userRepo.findById(userId);
    // If request id does not exist
    if (!optionalUserInfo.isPresent()) {
      logger.info("User {} does not exist", userId);
      return "redirect:/404";
    }
    // load profile picture

    FileInfo profile = storageService.loadProfileAsFileInfo(userInfo);
    model.addAttribute("profile", profile);

    model.addAttribute("userInfo", userInfo);
    model.addAttribute("userAcc", userAcc);

    // model.addAttribute("trInfo", userInfo.toMapTeacher());
    // List<FileInfo> fileInfos = loadImages();
    // model.addAttribute("files", fileInfos);
    // model.addAttribute("postAction","/admin/top/update");
    return "AD0001_AdminTop";

  }

  @PostMapping(value = "/admin/top/edit/")
  public String editProfilePicture(
      Model model,
      @RequestParam("profile_pic") MultipartFile photo,
      @Valid @ModelAttribute("userAcc") UserAccount mailEdit,
      @PathVariable(required = false) Long id,
      @RequestParam(value = "action", required = true) String action,
      HttpServletRequest httpServletRequest) {
    logger.info("Post Requested");
    logger.info("Payment Edit Info {}", photo);

    Long uid = userSessionService.getUserAccount().getAccountId();
    UserInfo userInfo = userRepo.findById(uid).orElse(null);
    UserAccount userAcc = userAccRepo.findById(uid).orElse(null);

    String redirectAddress = "redirect:" +
        httpServletRequest.getRequestURI().replace("/edit/", "");
    logger.debug("Redirect Addresss {}", redirectAddress);

    if (action.equals("pic-edit")) {
      if (!photo.isEmpty() && CheckUploadFileType.checkType(photo)) {
        // get original photo name and generate a new file name
        String originalFileName = StringUtils.cleanPath(
            photo.getOriginalFilename());
        // String saveFileName = FileNameGenerator.renameFileName(
        // originalFileName,

        // upload photo
        try {
          storageService.store(uid, photo, StorageServiceImpl.PROFILE_PATH, true);
        } catch (UnauthorizedFileAccessException e) {
          e.printStackTrace();
        }
        // insert photo
        userInfo.setPhoto(originalFileName);
        userRepo.save(userInfo);

        logger.info("profile photo {} stored", originalFileName);
        return redirectAddress + "/";
      }
    }

    return "redirect:/admin/top/";
  }

}