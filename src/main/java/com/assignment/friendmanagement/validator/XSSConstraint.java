package com.assignment.friendmanagement.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = XSSValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XSSConstraint {

    String message() default "Malicious text message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}


