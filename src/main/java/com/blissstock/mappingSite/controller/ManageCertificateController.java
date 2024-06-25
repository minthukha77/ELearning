package com.blissstock.mappingSite.controller;

import java.io.IOException;
import java.util.List;

import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.exceptions.NotImageFileException;
import com.blissstock.mappingSite.exceptions.UnauthorizedFileAccessException;
import com.blissstock.mappingSite.model.FileInfo;
import com.blissstock.mappingSite.service.StorageService;
import com.blissstock.mappingSite.service.StorageServiceImpl;
import com.blissstock.mappingSite.service.UserSessionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ManageCertificateController {

  private static final Logger logger = LoggerFactory.getLogger(
    ManageCertificateController.class
  );

  @Autowired
  StorageService storageService;

  @Autowired
  UserSessionService userSessionService;

  @GetMapping(
    value = { "/teacher/manage_certificate", "/admin/manage_certificate/{id}" }
  )
  public String manageCertificate(
    Model model,
    @PathVariable(name = "id", required = false) Long id
  ) {
    logger.info("GET Request, request id {}",id);

    Long uid = getUid(id);
    logger.info("User {}'s data is being processed ",uid);
    List<FileInfo> fileInfos = storageService.loadCertificatesAsFileInfo(uid);
    logger.info("{} photo has been retrieved",fileInfos.size());
    model.addAttribute("files", fileInfos);

    return "AT0007_manage_certificate";
  }

  @PostMapping(
    value = { "/teacher/manage_certificate", "/admin/manage_certificate/{id}" }
  )
  public String uploadFiles(
    @RequestParam("files") MultipartFile file,
    Model model,
    @PathVariable(name = "id", required = false) Long id
  ) {
    
    //log
    logger.info("POST mapping");
    logger.info("Files -> {}", file);
    

    // System.out.println(files.length);
    Long uid = getUid(id);
    logger.info("ID is {}", uid);
    try {
      // if (files.length > 0) {
        storageService.store(uid, file, StorageServiceImpl.CERTIFICATE_PATH,false);
      // } else {
      //   model.addAttribute(
      //     "fileUploadError",
      //     "Please Select at least one file"
      //   );
      // }
    } catch (NotImageFileException e) {
      e.printStackTrace();
      model.addAttribute(
        "fileUploadError",
        "Only Jpg, Jpeg and Png are allowed"
      );
    } catch (UnauthorizedFileAccessException e) {
      e.printStackTrace();
      model.addAttribute(
        "fileUploadError",
        "Unauthorized File Access"
      );
    } catch (Exception e) {
      e.printStackTrace();
    }

    List<FileInfo> fileInfos = storageService.loadCertificatesAsFileInfo(uid);
    model.addAttribute("files", fileInfos);

    return "AT0007_manage_certificate";
  }

  @DeleteMapping(
    value = { "/teacher/manage_certificate", "/admin/manage_certificate/{id}" }
  )
  public ResponseEntity<Object> deleteCertificate(
    String name,
    @PathVariable(name = "id", required = false) Long id
  ) {
    logger.info("DELETE Requested, file name {}",name);
    Long uid = getUid(id);
    //return ResponseEntity.badRequest().body("something went wrong");
    try {
      storageService.deleteCertificate(uid, name);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
    } catch (UnauthorizedFileAccessException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("forbidden");
    }

    return ResponseEntity.ok().body("delete successfully");
  }

  //To decide with user id to use
  //If user is teacher, use id from session
  //If user is admin, use id provide by the path value
  private Long getUid(Long id) {
    Long uid = 0L;
    UserRole role = userSessionService.getRole();
    if (role == UserRole.ADMIN || role == UserRole.SUPER_ADMIN) {
      uid = id;
    } else if (role == UserRole.TEACHER) {
      uid = userSessionService.getUserAccount().getAccountId();
    } else {
      throw new RuntimeException("user authentication fail");
    }
    return uid;
  }
}
