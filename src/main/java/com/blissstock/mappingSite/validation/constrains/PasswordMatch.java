package com.blissstock.mappingSite.validation.constrains;

import javax.validation.Payload;

import com.blissstock.mappingSite.validation.ConstrainMessage;
import com.blissstock.mappingSite.validation.validators.PasswordMatchValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
@Documented
public @interface PasswordMatch
{
    String message() default ConstrainMessage.PASSWORD_MATCH_CONSTRAIN_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String target() default "confirmPassword";

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List
    {
        PasswordMatch[] value();
    }
}