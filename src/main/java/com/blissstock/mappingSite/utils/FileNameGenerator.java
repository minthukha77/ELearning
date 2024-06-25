package com.blissstock.mappingSite.utils;

public class FileNameGenerator {
	private static String getFileExtension(String fileName) {
		try {
			int index = fileName.lastIndexOf(".");
			return index== -1?"":fileName.substring(index);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			return "";
		}		
		

	}
	
	public static String getRandomFileName(String name) {
		return System.currentTimeMillis()+getFileExtension(name);
	}

	public static String renameFileName(String originalName, String newName) {
		return newName+getFileExtension(originalName);
	}
}
