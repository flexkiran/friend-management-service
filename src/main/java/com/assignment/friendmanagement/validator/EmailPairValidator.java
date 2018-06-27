package com.assignment.friendmanagement.validator;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class EmailPairValidator implements ConstraintValidator<EmailPairConstraint, List<String>> {

    @Override
    public void initialize(EmailPairConstraint emailPairConstraint) {
    }

    @Override
    public boolean isValid(List<String> emails, ConstraintValidatorContext context) {
        if (emails == null || emails.size()!=2) {
            return false;
        }
        EmailValidator validator = new EmailValidator();
        for (String s : emails) {
            if (!validator.isValid(s, context)) {
                return false;
            }
        }
        if(emails.get(0).equals(emails.get(1))){
            return false;
        }
        return true;
    }

}
