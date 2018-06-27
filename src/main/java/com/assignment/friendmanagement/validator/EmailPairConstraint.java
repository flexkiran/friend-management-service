package com.assignment.friendmanagement.validator;

/**
 * Created by Kiran on 26/6/18.
 */
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EmailPairValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailPairConstraint {

    String message() default "Invalid email ids";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}