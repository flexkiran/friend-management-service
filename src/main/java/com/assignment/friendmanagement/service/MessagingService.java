package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.model.Email;

import java.util.List;

public interface MessagingService {

    List<Email> sendMessage(String sender,String text);
}
