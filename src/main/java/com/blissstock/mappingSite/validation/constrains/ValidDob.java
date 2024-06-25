package com.blissstock.mappingSite.validation.constrains;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.blissstock.mappingSite.validation.ConstrainMessage;
import com.blissstock.mappingSite.validation.validators.DobValidator;
import com.blissstock.mappingSite.validation.validators.EmailValidator;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DobValidator.class)
@Documented
public @interface ValidDob {
    String message() default ConstrainMessage.DOB_CONSTRAIN_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}