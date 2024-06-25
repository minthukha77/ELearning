package com.blissstock.mappingSite.service;

import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.blissstock.mappingSite.controller.FileController;
import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.PaymentHistory;
import com.blissstock.mappingSite.entity.PaymentReceive;
import com.blissstock.mappingSite.entity.UserAccount;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.enums.UserRole;
import com.blissstock.mappingSite.exceptions.FileStorageException;
import com.blissstock.mappingSite.exceptions.NotImageFileException;
import com.blissstock.mappingSite.exceptions.UnauthorizedFileAccessException;
import com.blissstock.mappingSite.model.FileInfo;
import com.blissstock.mappingSite.validation.validators.ImageFileValidator;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Service
public class StorageServiceImpl implements StorageService {

  private static final Logger logger = LoggerFactory.getLogger(
      StorageServiceImpl.class);

  private static final Path root = Paths
      .get(System.getProperty("user.home") + File.separator + "uploads");
  public static final Path CERTIFICATE_PATH = Paths.get(
      root + File.separator + "certificates");
  public static final Path QUESTION_MATERIAL_PATH = Paths.get(
      root + File.separator + "question_materials");
  public static final Path ANSWER_MATERIAL_PATH = Paths.get(
      root + File.separator + "answer_materials");
  public static final Path PROFILE_PATH = Paths.get(
      root + File.separator + "profiles");
  public static final Path PAYSLIP_PATH = Paths.get(
      root + File.separator + "slipimg");     
  public final static Path SLIP_PATH = Paths.get(
      root + File.separator + "slip");
  public final static Path COURSE_PATH = Paths.get(
      root + File.separator + "cphotos");

  @Autowired
  UserSessionService userSessionService;

  @Override
  public void init() {
    logger.info("Initiating {}", root.toAbsolutePath().toString());
    try {
      if (!Files.exists(root)) {
        Files.createDirectory(root);
      }
      if (!Files.exists(CERTIFICATE_PATH)) {
        Files.createDirectory(CERTIFICATE_PATH);
      }
      if (!Files.exists(PROFILE_PATH)) {
        Files.createDirectory(PROFILE_PATH);
      }
      if (!Files.exists(PAYSLIP_PATH)) {
        Files.createDirectory(PAYSLIP_PATH);
      }
      if (!Files.exists(SLIP_PATH)) {
        Files.createDirectory(SLIP_PATH);
      }
      if (!Files.exists(COURSE_PATH)) {
        Files.createDirectory(COURSE_PATH);
      }
      if (!Files.exists(QUESTION_MATERIAL_PATH)) {
        Files.createDirectory(QUESTION_MATERIAL_PATH);
      }
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  // @Override
  // public void storeProfile(MultipartFile file, String fileName, Long uid) {
  // Path path = PROFILE_PATH + File.separator + uid;
  // try {
  // if (file.isEmpty()) {
  // throw new StorageException("Failed to store empty file " + fileName);
  // }
  // if (fileName.contains("..")) {
  // // This is a security check
  // throw new StorageException(
  // "Cannot store file with relative path outside current directory " +
  // fileName
  // );
  // }
  // try (InputStream inputStream = file.getInputStream()) {
  // Files.copy(
  // inputStream,
  // PROFILE_PATH.resolve(fileName),
  // StandardCopyOption.REPLACE_EXISTING
  // );
  // }
  // } catch (IOException e) {
  // throw new StorageException("Failed to store file " + fileName, e);
  // }
  // }

  // @Override
  // public Resource loadAsResource(String filename) {
  // try {
  // Path file = loadProfile(filename);
  // Resource resource = new UrlResource(file.toUri());
  // if (resource.exists() || resource.isReadable()) {
  // return resource;
  // } else {
  // throw new StorageFileNotFoundException(
  // "Could not read file: " + filename
  // );
  // }
  // } catch (MalformedURLException e) {
  // throw new StorageFileNotFoundException(
  // "Could not read file: " + filename,
  // e
  // );
  // }
  // }
  @Override
  public Resource load(Long uid, String filename, Path path)
      throws UnauthorizedFileAccessException {
    // if (!checkAuthForTeacher(uid)) {
    // throw new UnauthorizedFileAccessException();
    // }
    Path storeLocation = Paths.get(path + File.separator + uid);
    try {
      Path file = storeLocation.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void store(Long uid, MultipartFile file, Path path, boolean deleteAllOldFiles)
      throws UnauthorizedFileAccessException {
    // Checking Content Type
    // logger.info("files with size: {} is being stored",files.length);

    if (path.equals(CERTIFICATE_PATH) && !checkAuthForTeacher(uid)) {
      logger.info("User " + uid + " is uploding certificates");
      logger.error("unauthorize file access");
      throw new UnauthorizedFileAccessException();
    }
    ImageFileValidator fileValidator = new ImageFileValidator();
    // for (MultipartFile file : files) {
    if (!fileValidator.isSupportedContentType(file.getContentType())) {
      logger.error("not file exception, {}", file.getName());
      throw new NotImageFileException();
    }
    // }
    Path storeLocation = Paths.get(path + File.separator + uid);

    // Create Directory if it does not exists
    if (!Files.exists(storeLocation)) {
      try {
        Files.createDirectories(storeLocation);
      } catch (IOException e) {
        e.printStackTrace();
        logger.error("Could not initialize folder for upload!");
        throw new RuntimeException("Could not initialize folder for upload!");
      }
    }

    if (deleteAllOldFiles) {
      logger.info("Deleting all photos of Uid: {}", uid);
      try {
        FileUtils.cleanDirectory(storeLocation.toFile());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // for (MultipartFile file : files) {
    try {
      try {
        Files.copy(
            file.getInputStream(),
            storeLocation.resolve(file.getOriginalFilename()));
      } catch (Exception e) {
        throw new RuntimeException(
            "Could not store the file. Error: " + e.getMessage());
      }
    } catch (Exception e) {
      e.printStackTrace();

      throw new FileStorageException(
          "Could not store file " +
              file.getOriginalFilename() +
              ". Please try again!");
    }
    // logger.info("User {} has stored {} files",uid,files.length);
  }

  @Override
  public void storeQuestionMaterials(Long uid, MultipartFile file, Path path, boolean deleteAllOldFiles)
      throws UnauthorizedFileAccessException {
    // Checking Content Type
    // logger.info("files with size: {} is being stored",files.length);

    if (path.equals(CERTIFICATE_PATH) && !checkAuthForTeacher(uid)) {
      logger.info("User " + uid + " is uploding certificates");
      logger.error("unauthorize file access");
      throw new UnauthorizedFileAccessException();
    }
    // ImageFileValidator fileValidator = new ImageFileValidator();
    // // for (MultipartFile file : files) {
    // if (!fileValidator.isSupportedContentType(file.getContentType())) {
    // logger.error("not file exception, {}", file.getName());
    // throw new NotImageFileException();
    // }
    // }
    Path storeLocation = Paths.get(path + File.separator + uid);

    // Create Directory if it does not exists
    if (!Files.exists(storeLocation)) {
      try {
        Files.createDirectories(storeLocation);
      } catch (IOException e) {
        e.printStackTrace();
        logger.error("Could not initialize folder for upload!");
        throw new RuntimeException("Could not initialize folder for upload!");
      }
    }

    if (deleteAllOldFiles) {
      logger.info("Deleting all photos of Uid: {}", uid);
      try {
        FileUtils.cleanDirectory(storeLocation.toFile());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // for (MultipartFile file : files) {
    try {
      try {
        Files.copy(
            file.getInputStream(),
            storeLocation.resolve(file.getOriginalFilename()));
      } catch (Exception e) {
        throw new RuntimeException(
            "Could not store the file. Error: " + e.getMessage());
      }
    } catch (Exception e) {
      e.printStackTrace();

      throw new FileStorageException(
          "Could not store file " +
              file.getOriginalFilename() +
              ". Please try again!");
    }
    // logger.info("User {} has stored {} files",uid,files.length);
  }
  // }

  @Override
  public Stream<Path> loadAllCertificates(Long uid)
      throws UnauthorizedFileAccessException {
    // if (!checkAuthForTeacher(uid)) {
    // throw new UnauthorizedFileAccessException();
    // }
    logger.info("Certificates of user {} has been requested", uid);
    Path storeLocation = Paths.get(CERTIFICATE_PATH + File.separator + uid);
    try {
      return Files
          .walk(storeLocation, 1)
          .filter(path -> !path.equals(storeLocation))
          .map(storeLocation::relativize);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Could not load the files!");
    }

  }

  public void deleteCertificate(Long uid, String filename)
      throws IOException, UnauthorizedFileAccessException {
    if (!checkAuthForTeacher(uid)) {
      throw new UnauthorizedFileAccessException();
    }
    logger.info("Certificates of {} has been requested to delete", uid);
    Path storeLocation = Paths.get(CERTIFICATE_PATH + File.separator + uid);
    Path file = storeLocation.resolve(filename);
    Files.delete(file);
    logger.info("User {} deleted file {}", uid, filename);
  }

  @Override
  public boolean checkAuthForTeacher(Long uid) {
    UserAccount userAccount = userSessionService.getUserAccount();
    UserRole userRole = UserRole.strToUserRole(userAccount.getRole());
    logger.debug("user Role {}", userRole == UserRole.TEACHER);
    if (userRole != UserRole.ADMIN &&
        userRole != UserRole.SUPER_ADMIN &&
        userRole != UserRole.TEACHER) {
      // if user is not one of the following, it will return false;
      // Teacher, Admin, Super Admin
      //
      logger.debug("user role is not correct");
      return false;
    }
    if (userRole == UserRole.TEACHER && !userAccount.getAccountId().equals(uid)) {
      // This condition is to make sure, teacher is not accessing other teacher
      // resources.
      logger.debug("id is not correct {}", userAccount.getAccountId() != uid);
      return false;
    }
    return true;
  }

  @Override
  public List<FileInfo> loadCertificatesAsFileInfo(Long uid) {
    try {
      return loadAllCertificates(uid)
          .map(
              path -> {
                String name = path.getFileName().toString();
                String url = MvcUriComponentsBuilder
                    .fromMethodName(
                        FileController.class,
                        "getResource",
                        "certificates",
                        uid,
                        path.getFileName().toString())
                    .build()
                    .toString();
                return new FileInfo(name, url);
              })
          .collect(Collectors.toList());
    } catch (Exception e) {
      return new ArrayList<>();
    }

  }

  @Override
  public FileInfo loadProfileAsFileInfo(UserInfo userInfo) {

    String name = userInfo.getPhoto();
    if (name == null || name.isEmpty()) {
      return new FileInfo("default", "/images/profile.png");
    }
    String url = MvcUriComponentsBuilder
        .fromMethodName(
            FileController.class,
            "getResource",
            "profiles",
            userInfo.getUid(),
            name)
        .build()
        .toString();
    logger.info("Get Data as Resource name: {}, url: {}", name, url);
    return new FileInfo(name, url);
  }

  @Override
  public FileInfo loadCoursePhoto(CourseInfo courseInfo) {

    String name = courseInfo.getCoursePhoto();
    if (name == null || name.isEmpty()) {
      return new FileInfo("default", "/images/course.png");
    }
    String url = MvcUriComponentsBuilder
        .fromMethodName(
            FileController.class,
            "getResource",
            "cphotos",
            courseInfo.getCourseId(),
            name)
        .build()
        .toString();
    logger.info("Get Data as Resource name: {}, url: {}", name, url);
    return new FileInfo(name, url);
  }

  @Override
  public void storeSlip(MultipartFile file, String fileName) {
    try {
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file " + fileName);
      }
      if (fileName.contains("..")) {
        // This is a security check
        throw new StorageException(
            "Cannot store file with relative path outside current directory " +
                fileName);
      }
      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(
            inputStream,
            SLIP_PATH.resolve(fileName),
            StandardCopyOption.REPLACE_EXISTING);
      }
    } catch (IOException e) {
      throw new StorageException("Failed to store file " + fileName, e);
    }
  }

  @Override
  public FileInfo loadSlipAsFileInfo(PaymentReceive payment, Long userId) {

    String name = payment.getSlip();
    if (name == null || name.isEmpty()) {
      return new FileInfo("default", "/images/80x80.png");
    }
    String url = MvcUriComponentsBuilder
        .fromMethodName(
            FileController.class,
            "getResource",
            "slip",
            userId,
            name)
        .build()
        .toString();
    logger.info("Get Data as Resource name: {}, url: {}", name, url);
    System.out.println(name);
    return new FileInfo(name, url);
  }

  @Override
  public FileInfo loadQuestionMaterials(Long fileSeparator, String fileName) {

    if (fileName == null || fileName.isEmpty()) {
      return new FileInfo("default", null);
    }

    String url = MvcUriComponentsBuilder
        .fromMethodName(
            FileController.class,
            "getResource",
            // "slipimg",
            "question_materials",
            // payHistory.getPaymentHistoryId(),
            fileSeparator,
            fileName)
        .build()
        .toString();

    logger.info("Get Data as Resource name: {}, url: {}", fileName, url);
    return new FileInfo(fileName, url);
  }

  @Override
  public FileInfo loadAnswermaterials(Long fileSeparator, String fileName) {

    if (fileName == null || fileName.isEmpty()) {
      return new FileInfo("default", null);
    }

    String url = MvcUriComponentsBuilder
        .fromMethodName(
            FileController.class,
            "getResource",
            // "slipimg",
            "answer_materials",
            // payHistory.getPaymentHistoryId(),
            fileSeparator,
            fileName)
        .build()
        .toString();

    logger.info("Get Data as Resource name: {}, url: {}", fileName, url);
    return new FileInfo(fileName, url);
  }
  @Override  
  public FileInfo loadPaymentSlip(Long fileSeparator, PaymentHistory viewHistory) {
     
    String fileName = viewHistory.getSlipImg();
      if (fileName == null || fileName.isEmpty()) {
          return new FileInfo("default", null);
      }

      String url = MvcUriComponentsBuilder
              .fromMethodName(
                      FileController.class,
                      "getResource",
                      //"slipimg",
                      "profiles",
                      //payHistory.getPaymentHistoryId(),
                      fileSeparator,
                      fileName)
              .build()
              .toString();

      logger.info("Get Data as Resource name: {}, url: {}", fileName, url);
      return new FileInfo(fileName, url);
  }


}
