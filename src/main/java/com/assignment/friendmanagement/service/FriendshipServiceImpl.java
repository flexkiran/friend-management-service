package com.assignment.friendmanagement.service;

import com.assignment.friendmanagement.model.Email;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Override
    public List<Email> makeNewFriend(String emailAddress1, String emailAddress2) {
        return null;
    }

    @Override
    public boolean friendshipExist(String emailAddress1, String emailAddress2) {
        return false;
    }

    @Override
    public List<Email> getAllFriends(String emailAddress) {
        return null;
    }

    @Override
    public List<Email> getAllCommonFriends(String emailAddress1, String emailAddress2) {
        return null;
    }


}
