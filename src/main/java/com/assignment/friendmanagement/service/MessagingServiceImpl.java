package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.ServiceException;
import com.assignment.friendmanagement.model.Person;
import com.assignment.friendmanagement.model.response.MessageRecipientsResponse;
import com.assignment.friendmanagement.utils.EmailUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.assignment.friendmanagement.model.SubscriptionStatus.SUBSCRIBED;
import static com.assignment.friendmanagement.model.SubscriptionStatus.UNSUBSCRIBED;

@Service
public class MessagingServiceImpl implements MessagingService {

    private Logger logger = LogManager.getLogger();

    @Autowired
    private PersonService personService;
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private EmailUtils emailUtils;

    @Override
    public MessageRecipientsResponse sendMessage(String senderEmail, String text) {
        Person sender = personService.findByEmail(senderEmail);
        List<Person> taggedEmails = getAllTaggedPersonInMessage(text);
        List<Person> subscribers = subscriptionService.getSubscriptions(senderEmail, SUBSCRIBED)
                .stream()
                .map(s -> s.getId().getRequestor())
                .collect(Collectors.toList());
        List<Person> unSubscribers = subscriptionService.getSubscriptions(senderEmail, UNSUBSCRIBED)
                .stream()
                .map(s -> s.getId().getRequestor())
                .collect(Collectors.toList());

        if (subscribers == null)
            subscribers = new ArrayList<>();
        if (unSubscribers == null)
            unSubscribers = new ArrayList<>();
        Collection<Person> combinedSubscribers = CollectionUtils.union(taggedEmails, subscribers);
        Collection<Person> allSubscribers = CollectionUtils.subtract(combinedSubscribers, unSubscribers);

        if (allSubscribers == null)
            allSubscribers = CollectionUtils.emptyCollection();
        List<String> subscribersEmails = allSubscribers.stream().map(e -> e.getEmail())
                .collect(Collectors.toList());
        MessageRecipientsResponse msgResponse = new MessageRecipientsResponse(true, subscribersEmails);
        logger.error("{} have {} recipients", text, msgResponse);
        return msgResponse;
    }

    private List<Person> getAllTaggedPersonInMessage(String text) {
        List<String> emailIds = emailUtils.retrieveEmailIdsFromText(text);
        List<Person> taggedPersons = new ArrayList<>();
        emailIds.forEach(e -> {
            try {
                Person email = personService.findByEmail(e);
                taggedPersons.add(email);
            } catch (ServiceException ex) {
                logger.error("email =>" + e + " is not registered");
            }
        });
        logger.debug("{} have {} valid people tagged", text, taggedPersons);
        return taggedPersons;
    }


}
