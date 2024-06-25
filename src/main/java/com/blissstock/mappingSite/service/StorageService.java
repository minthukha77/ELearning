package com.blissstock.mappingSite.service;

import com.blissstock.mappingSite.entity.PaymentReceive;
import com.blissstock.mappingSite.entity.UserInfo;
import com.blissstock.mappingSite.entity.CourseInfo;
import com.blissstock.mappingSite.entity.PaymentHistory;
import com.blissstock.mappingSite.exceptions.UnauthorizedFileAccessException;
import com.blissstock.mappingSite.model.FileInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
  void init();

  // Resource loadAsResource(String filename);

  public Resource load(Long uid, String filename, Path path)
    throws UnauthorizedFileAccessException;


  void store(Long uid, MultipartFile files, Path path, boolean deleteAllOldFiles)
    throws UnauthorizedFileAccessException;

  void storeQuestionMaterials(Long uid, MultipartFile files, Path path, boolean deleteAllOldFiles)
    throws UnauthorizedFileAccessException;

  Stream<Path> loadAllCertificates(Long uid)
    throws UnauthorizedFileAccessException;

  public List<FileInfo> loadCertificatesAsFileInfo(Long uid);

  public FileInfo loadProfileAsFileInfo(UserInfo userInfo);

  public void deleteCertificate(Long uid, String filename)
    throws IOException, UnauthorizedFileAccessException;

  public boolean checkAuthForTeacher(Long uid);

  public void storeSlip(MultipartFile file, String fileName);

  public FileInfo loadSlipAsFileInfo(PaymentReceive payment, Long userId);

  public FileInfo loadCoursePhoto(CourseInfo courseInfo);

  public FileInfo loadPaymentSlip(Long fileSeparator, PaymentHistory viewHistory) ;
 
  public FileInfo loadQuestionMaterials(Long fileSeparator, String fileName) ;

  public FileInfo loadAnswermaterials(Long fileSeparator, String fileName) ;
}
