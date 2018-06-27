package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.exception.ErrorCode;
import com.assignment.friendmanagement.exception.ServiceException;
import com.assignment.friendmanagement.model.Friends;
import com.assignment.friendmanagement.model.Person;
import com.assignment.friendmanagement.repository.FriendsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.assignment.friendmanagement.model.SubscriptionStatus.SUBSCRIBED;
import static com.assignment.friendmanagement.model.SubscriptionStatus.UNSUBSCRIBED;
import static org.apache.commons.collections4.CollectionUtils.intersection;

@Service
public class FriendsServiceImpl implements FriendsService {

    private Logger logger = LogManager.getLogger();

    @Autowired
    private FriendsRepository friendsRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Override
    public boolean makeFriends(String emailAddress1, String emailAddress2) {
        Person person1 = personService.findByEmail(emailAddress1);
        Person person2 = personService.findByEmail(emailAddress2);
        validateFriendship(emailAddress1, emailAddress2, person1, person2);
        validateBlockedRelation(emailAddress1, emailAddress2);
        subscribeForUpdates(emailAddress1, emailAddress2);
        Friends friends = friendsRepository.save(new Friends(person1, person2));
        logger.info("{} and {} are {} now", emailAddress1, emailAddress2, friends);
        return friends != null ? true : false;
    }

    @Override
    public List<String> getFriends(String emailAddress) {
        Person person = personService.findByEmail(emailAddress);
        List<Friends> allFriends = friendsRepository.getAllFriends(person);
        logger.info("{} have friends", emailAddress, allFriends);
        return getAllFriends(allFriends, emailAddress);
    }

    @Override
    public List<String> getCommonFriends(String emailAddress1, String emailAddress2) {

        Person person1 = personService.findByEmail(emailAddress1);
        Person person2 = personService.findByEmail(emailAddress2);
        List<String> person1Friends = getAllFriends(friendsRepository.getAllFriends(person1), emailAddress1);
        List<String> person2Friends = getAllFriends(friendsRepository.getAllFriends(person2), emailAddress2);
        ArrayList<String> commonFriends = new ArrayList<>(intersection(person1Friends, person2Friends));
        logger.info("{} and {} have common friends {}", emailAddress1, emailAddress2, commonFriends);
        return commonFriends;

    }

    private List<String> getAllFriends(List<Friends> friends, String email) {
        if (friends != null && friends.size() > 0) {
            return friends.stream()
                    .map(f -> f.getPerson1().getEmail().equals(email) ?
                            f.getPerson2().getEmail() : f.getPerson1().getEmail())
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private void validateBlockedRelation(String emailAddress1, String emailAddress2) {
        if (subscriptionService.isSubscribed(emailAddress1, emailAddress2, UNSUBSCRIBED)
                || subscriptionService.isSubscribed(emailAddress2, emailAddress1, UNSUBSCRIBED)) {
            throw new ServiceException(ErrorCode.EMAILS_BLOCKED_FOR_UPDATES,
                    Arrays.asList(new String[]{emailAddress1, emailAddress2}));
        }
    }

    private void validateFriendship(String emailAddress1, String emailAddress2, Person person1, Person person2) {
        List<Friends> friendship = friendsRepository.getFriendship(person1, person2);
        if (friendship != null && friendship.size() > 0) {
            throw new ServiceException(ErrorCode.FRIENDS_CONNECTION_ALREADY_REGISTERED,
                    Arrays.asList(new String[]{emailAddress1, emailAddress2}));
        }
    }

    private void subscribeForUpdates(String email1, String email2) {
        //After adding friend subscriber for each others updates
        try {
            subscriptionService.subscribe(email1, email2, SUBSCRIBED);
        } catch (ServiceException ex) {
            logger.info("{} - {} are already subscribed", email1, email2);
        }
        try {
            subscriptionService.subscribe(email2, email1, SUBSCRIBED);
        } catch (ServiceException ex) {
            logger.info("{} - {} are already subscribed", email2, email1);
        }
    }


}
