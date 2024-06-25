package com.blissstock.mappingSite.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class CheckUploadFileType {
	private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif","image/jpg");

	public static boolean checkType(MultipartFile file) {
	    String fileContentType = file.getContentType();
	    if(contentTypes.contains(fileContentType)) {
	        return true;
	    } else {
	        // Handle error of not correct extension
	    	return false;
	    }
	}
}
