package com.assignment.friendmanagement.service;

import java.util.List;

/**
 * Created by Kiran on 24/6/18.
 */
public interface FriendsService {

    boolean makeFriends(String emailAddress1, String emailAddress2);

    List<String> getFriends(String emailAddress);

    List<String> getCommonFriends(String emailAddress1, String emailAddress2);

}
