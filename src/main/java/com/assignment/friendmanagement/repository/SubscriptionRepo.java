package com.assignment.friendmanagement.repository;

import com.assignment.friendmanagement.model.Person;
import com.assignment.friendmanagement.model.Subscription;
import com.assignment.friendmanagement.model.SubscriptionKey;
import com.assignment.friendmanagement.model.SubscriptionStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepo extends CrudRepository<Subscription,SubscriptionKey> {

    List<Subscription> findByIdTargetAndStatus(Person target, SubscriptionStatus status);
    List<Subscription> findByIdAndStatus(SubscriptionKey id, SubscriptionStatus status);

}
