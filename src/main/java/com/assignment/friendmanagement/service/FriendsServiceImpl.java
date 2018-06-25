package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.ErrorCode;
import com.assignment.friendmanagement.exception.ServiceException;
import com.assignment.friendmanagement.model.Email;
import com.assignment.friendmanagement.model.Subscription;
import com.assignment.friendmanagement.repository.FriendsRepository;
import com.assignment.friendmanagement.repository.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.*;

@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private FriendsRepository friendsRepository;

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Override
    public boolean makeNewFriend(String emailAddress1, String emailAddress2) {
        Email email1 = emailService.findEmail(emailAddress1);
        Email email2 = emailService.findEmail(emailAddress2);
        if (email1 == null || email2 == null) {
            throw new ServiceException(ErrorCode.EMAIL_NOT_REGISTERED,
                    Arrays.asList(new String[]{emailAddress1, emailAddress2}));
        }
        if (email1.equals(email2)) {
            throw new ServiceException(ErrorCode.BOTH_EMAILS_ARE_SAME,
                    Arrays.asList(new String[]{emailAddress1, emailAddress2}));
        }
        if (friendsRepository.isExists(email1, email2) != null) {
            throw new ServiceException(ErrorCode.FRIENDS_CONNECTION_ALREADY_REGISTERED,
                    Arrays.asList(new String[]{emailAddress1, emailAddress2}));
        }
        if (subscriptionRepo.isSubscribed(email1, email2, 0) != null
                || subscriptionRepo.isSubscribed(email2, email1, 0) != null) {
            throw new ServiceException(ErrorCode.EMAILS_BLOCKED_FOR_UPDATES,
                    Arrays.asList(new String[]{emailAddress1, emailAddress2}));
        }
        subscribeForUpdates(email1, email2);
        return friendsRepository.save(email1, email2) != null ? true : false;
    }

    private void subscribeForUpdates(Email email1, Email email2) {
        //After adding friend subscriber for each others updates
        if (subscriptionRepo.isSubscribed(email1, email2, 1) == null) {
            subscriptionRepo.save(new Subscription(email1, email2, 1));
        }
        if (subscriptionRepo.isSubscribed(email2, email1, 1) == null) {
            subscriptionRepo.save(new Subscription(email2, email1, 1));
        }
    }

    @Override
    public List<String> getAllFriends(String emailAddress) {

        Email email = emailService.findEmail(emailAddress);
        if (email == null) {
            throw new ServiceException(ErrorCode.EMAIL_NOT_REGISTERED,
                    Arrays.asList(new String[]{emailAddress}));
        }
        List<String> emails = new ArrayList<>();
        List<Email> allEmails = friendsRepository.getAllFriends(email);
        if (allEmails != null && allEmails.size() > 0) {
            emails = allEmails.stream()
                    .map(e -> e.getEmail()).collect(Collectors.toList());
        }
        return emails;

    }

    @Override
    public List<String> getAllCommonFriends(String emailAddress1, String emailAddress2) {

        Email email1 = emailService.findEmail(emailAddress1);
        Email email2 = emailService.findEmail(emailAddress2);
        if (email1 == null || email2 == null) {
            throw new ServiceException(ErrorCode.EMAIL_NOT_REGISTERED,
                    Arrays.asList(new String[]{emailAddress1, emailAddress2}));
        }
        if (email1.equals(email2)) {
            throw new ServiceException(ErrorCode.BOTH_EMAILS_ARE_SAME,
                    Arrays.asList(new String[]{emailAddress1, emailAddress2}));
        }
        List<Email> email1Friends = friendsRepository.getAllFriends(email1);
        List<Email> email2Friends = friendsRepository.getAllFriends(email2);
        Collection<Email> commonFriends = intersection(email1Friends, email2Friends);
        List<String> emails = new ArrayList<>();
        if (commonFriends != null && commonFriends.size() > 0) {
            emails = commonFriends.stream()
                    .map(e -> e.getEmail()).collect(Collectors.toList());
        }
        return emails;

    }


}
