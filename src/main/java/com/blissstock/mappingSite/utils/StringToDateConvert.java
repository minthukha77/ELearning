package com.blissstock.mappingSite.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class StringToDateConvert {
    public static Date stringToDate(String input){
        String pattern = "\\d{4}-\\d{2}-\\d{2}";
        boolean isFormat = false;
        
        try{
            isFormat = Pattern.matches(pattern, input);
        }catch(Exception e){
            return null;
        }
        
        if(isFormat){
            try{
                return new SimpleDateFormat("yyyy-MM-dd").parse(input);  
            }catch(Exception npe){
                return null;
            }
        }
        return null;
    }
}
