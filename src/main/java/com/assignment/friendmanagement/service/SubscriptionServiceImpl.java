package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.model.Email;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public List<Email> subscibe(String requestor, String target) {
        return null;
    }

    @Override
    public List<Email> unSubscribe(String requestor, String target) {
        return null;
    }

    @Override
    public boolean isSubscribed(String requestor, String target) {
        return false;
    }

    @Override
    public boolean isUnSubscribed(String requestor, String target) {
        return false;
    }
}
