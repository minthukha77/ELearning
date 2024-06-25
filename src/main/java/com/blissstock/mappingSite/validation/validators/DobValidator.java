package com.blissstock.mappingSite.validation.validators;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.blissstock.mappingSite.validation.constrains.ValidDob;

public class DobValidator implements ConstraintValidator<ValidDob, Date> {

  @Override
  public void initialize(ValidDob constraintAnnotation) {
  }

  @Override
  public boolean isValid(Date dob, ConstraintValidatorContext context) {
    GregorianCalendar gregorianCalendar = (GregorianCalendar) Calendar.getInstance();
    gregorianCalendar.setTime(dob);
    LocalDate formattedDob = gregorianCalendar.toZonedDateTime().toLocalDate();
    LocalDate today = LocalDate.now();
    return Period.between(formattedDob, today).getYears() >= 5;
  }

}
