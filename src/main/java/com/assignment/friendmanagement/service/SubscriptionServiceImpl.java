package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.ErrorCode;
import com.assignment.friendmanagement.exception.ServiceException;
import com.assignment.friendmanagement.model.Email;
import com.assignment.friendmanagement.model.Subscription;
import com.assignment.friendmanagement.repository.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.assignment.friendmanagement.exception.ErrorCode.*;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Override
    public void subscribe(String requestor, String target) {
        subscribeWithStatus(requestor, target, 1);
    }

    @Override
    public void unSubscribe(String requestor, String target) {
        subscribeWithStatus(requestor, target, 0);
    }

    @Override
    public boolean isSubscribed(String requestor, String target) {
        return isSubscribedWithStatus(requestor, target, 1);
    }

    @Override
    public boolean isUnSubscribed(String requestor, String target) {
        return isSubscribedWithStatus(requestor, target, 0);
    }

    private boolean isSubscribedWithStatus(String requestor, String target, int status) {
        Email requestorEmail = emailService.findEmail(requestor);
        Email targetEmail = emailService.findEmail(target);
        validateEmails(requestorEmail, targetEmail);
        return subscriptionRepo.isSubscribed(requestorEmail, targetEmail, status) != null;
    }

    private void subscribeWithStatus(String requestor, String target, int status) {
        Email requestorEmail = emailService.findEmail(requestor);
        Email targetEmail = emailService.findEmail(target);
        validateEmails(requestorEmail, targetEmail);
        if (subscriptionRepo.isSubscribed(requestorEmail, targetEmail, status) != null) {
            ErrorCode errorCode = status == 0 ? ALREADY_UNSUBSCRIBED_FROM_UPDATES : ALREADY_SUBSCRIBED_FOR_UPDATES;
            throw new ServiceException(errorCode,
                    Arrays.asList(new String[]{requestorEmail.getEmail(), targetEmail.getEmail()}));
        }
        subscriptionRepo.save(new Subscription(requestorEmail, targetEmail, status));
    }

    private void validateEmails(Email requestorEmail, Email targetEmail) {
        if (requestorEmail == null || targetEmail == null) {
            throw new ServiceException(EMAIL_NOT_REGISTERED,
                    Arrays.asList(new String[]{requestorEmail.getEmail(), targetEmail.getEmail()}));
        }
        if (requestorEmail.equals(targetEmail)) {
            throw new ServiceException(BOTH_EMAILS_ARE_SAME,
                    Arrays.asList(new String[]{requestorEmail.getEmail(), targetEmail.getEmail()}));
        }

    }

}
