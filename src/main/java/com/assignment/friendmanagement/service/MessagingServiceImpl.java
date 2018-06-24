package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.model.Email;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagingServiceImpl implements MessagingService {

    @Override
    public List<Email> sendMessage(String sender, String text) {
        return null;
    }

}
