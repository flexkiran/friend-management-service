package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.model.response.MessageRecipientsResponse;

public interface MessagingService {

    MessageRecipientsResponse sendMessage(String sender, String text);
}
