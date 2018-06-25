package com.assignment.friendmanagement.repository;

import com.assignment.friendmanagement.model.Email;
import com.assignment.friendmanagement.model.Subscription;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SubscriptionRepo {

    List<Subscription> subscriptions = new ArrayList<>();

    public Subscription save(Subscription subscription){
        subscription.setId(subscriptions.size()+1);
        subscriptions.add(subscription);
        return subscription;
    }

    public List<Email> getAllSubscribers(Email email){
        return subscriptions.stream().filter( s -> s.getRequestor().equals(email)
        && s.getStatus() == 1).map( s -> s.getTarget()).collect(Collectors.toList());
    }

    public Subscription isSubscribed(Email requestor, Email target ,int status){
        return subscriptions.stream().filter( s -> s.getRequestor().equals(requestor)
                && s.getTarget().equals(target) && s.getStatus() == status)
                .findFirst()
                .orElse(null);
    }
}
