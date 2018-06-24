package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.model.Email;

import java.util.List;

/**
 * Created by Kiran on 24/6/18.
 */
public interface FriendshipService {

    List<Email> makeNewFriend(String emailAddress1, String emailAddress2);

    boolean friendshipExist(String emailAddress1, String emailAddress2);

    List<Email> getAllFriends(String emailAddress);

    List<Email> getAllCommonFriends(String emailAddress1, String emailAddress2);

}
