package com.blissstock.mappingSite.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

  public static String format(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat();
    formatter.applyPattern("MMM d, yyyy");
    return formatter.format(date);
  }
}
