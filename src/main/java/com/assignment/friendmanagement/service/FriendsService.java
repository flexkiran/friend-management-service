package com.assignment.friendmanagement.service;

import java.util.List;

/**
 * Created by Kiran on 24/6/18.
 */
public interface FriendsService {

    boolean makeNewFriend(String emailAddress1, String emailAddress2);

    List<String> getAllFriends(String emailAddress);

    List<String> getAllCommonFriends(String emailAddress1, String emailAddress2);

}
