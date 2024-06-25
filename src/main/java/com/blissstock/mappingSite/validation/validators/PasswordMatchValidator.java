package com.blissstock.mappingSite.validation.validators;

import com.blissstock.mappingSite.validation.constrains.PasswordData;
import com.blissstock.mappingSite.validation.constrains.PasswordMatch;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator
  implements ConstraintValidator<PasswordMatch, Object> {

  private String message;
  private String target;

  @Override
  public void initialize(final PasswordMatch constraintAnnotation) {
    target = constraintAnnotation.target();
    message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(
    final Object value,
    final ConstraintValidatorContext context
  ) {
    boolean valid = true;
    try {
      //Only Check Validaation for Password Data Object

      if (value instanceof PasswordData) {
        PasswordData data = (PasswordData) value;
        //Check if password and confirmPassword are equal.
        //Ignore if any of the field is null

        if (data.getPassword() != null && data.getConfirmPassword() != null) {
          /*  System.out.println(data.getPassword());
                System.out.println(data.getConfirmPassword()); */
          valid = data.getPassword().equals(data.getConfirmPassword());
        }
      }
    } catch (final Exception ignore) {
      // ignore
    }

    if (!valid) {
      context
        .buildConstraintViolationWithTemplate(message)
        .addPropertyNode(target)
        .addConstraintViolation()
        .disableDefaultConstraintViolation();
    }

    return valid;
  }
}
