package com.blissstock.mappingSite.utils;


import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileUtil {

  public static String toBase64(MultipartFile file) {
    StringBuilder sb = new StringBuilder();
    sb.append("data:image/png;base64,");
    try {
        sb.append(
          StringUtils.newStringUtf8(Base64.encodeBase64(file.getBytes(),false))
        );
    } catch (IOException e) {
        e.printStackTrace();
    }
    return sb.toString();
  }
}
