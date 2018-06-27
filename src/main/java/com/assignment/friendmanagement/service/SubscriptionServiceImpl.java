package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.ErrorCode;
import com.assignment.friendmanagement.exception.ServiceException;
import com.assignment.friendmanagement.model.Person;
import com.assignment.friendmanagement.model.Subscription;
import com.assignment.friendmanagement.model.SubscriptionKey;
import com.assignment.friendmanagement.model.SubscriptionStatus;
import com.assignment.friendmanagement.repository.SubscriptionRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.assignment.friendmanagement.exception.ErrorCode.ALREADY_SUBSCRIBED_FOR_UPDATES;
import static com.assignment.friendmanagement.exception.ErrorCode.ALREADY_UNSUBSCRIBED_FROM_UPDATES;
import static com.assignment.friendmanagement.model.SubscriptionStatus.UNSUBSCRIBED;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private Logger logger = LogManager.getLogger();

    @Autowired
    private PersonService personService;
    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Override
    public void subscribe(String requestorEmail, String targetEmail, SubscriptionStatus status) {
        Person requestor = personService.findByEmail(requestorEmail);
        Person target = personService.findByEmail(targetEmail);
        validateSubscription(status, requestor, target);
        Subscription subscription = subscriptionRepo.save(new Subscription(new SubscriptionKey(requestor, target), status));
        logger.info("Added new {}", subscription);
    }

    @Override
    public boolean isSubscribed(String requestorEmail, String targetEmail, SubscriptionStatus status) {
        Person requestor = personService.findByEmail(requestorEmail);
        Person target = personService.findByEmail(targetEmail);
        List<Subscription> subscriptions = subscriptionRepo.findByIdAndStatus(new SubscriptionKey(requestor, target), status);
        logger.info(" requestor {} ,target {} , status {} have subscribers{}",
                requestorEmail, targetEmail, status, subscriptions);
        return subscriptions != null && subscriptions.size() > 0;
    }

    @Override
    public List<Subscription> getSubscriptions(String targetEmail, SubscriptionStatus status) {
        Person target = personService.findByEmail(targetEmail);
        List<Subscription> subscriptions = subscriptionRepo.findByIdTargetAndStatus(target, status);
        logger.info(" target {} , status {} have subscribers{}",
                targetEmail, status, subscriptions);
        return subscriptions;
    }

    private void validateSubscription(SubscriptionStatus status, Person requestor, Person target) {
        List<Subscription> subscriptions = subscriptionRepo.findByIdAndStatus(new SubscriptionKey(requestor, target), status);
        if (subscriptions != null && subscriptions.size() > 0) {
            logger.warn(" requestor{} , status {} don't have valid subscription",
                    requestor, status);
            ErrorCode errorCode = status == UNSUBSCRIBED ? ALREADY_UNSUBSCRIBED_FROM_UPDATES : ALREADY_SUBSCRIBED_FOR_UPDATES;
            throw new ServiceException(errorCode,
                    Arrays.asList(new String[]{requestor.getEmail(), target.getEmail()}));
        }
    }

}
