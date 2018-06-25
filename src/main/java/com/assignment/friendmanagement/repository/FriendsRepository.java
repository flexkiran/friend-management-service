package com.assignment.friendmanagement.repository;

import com.assignment.friendmanagement.model.Email;
import com.assignment.friendmanagement.model.Friend;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FriendsRepository {

    List<Friend> friends = new ArrayList<>();

    public Friend isExists(Email email1, Email email2) {
        return friends.stream().filter((Friend f) -> {
            return ((f.getEmail1().equals(email1) && f.getEmail2().equals(email2))
                    || (f.getEmail1().equals(email2) && f.getEmail2().equals(email1)));
        }).findFirst()
                .orElse(null);
    }

    public Friend save(Email email1, Email email2) {
        Friend friend = new Friend(friends.size() + 1, email1, email2);
        friends.add(friend);
        return friend;
    }

    public List<Email> getAllFriends(Email email) {
        return friends.stream().filter(f -> f.getEmail1().equals(email) || f.getEmail2().equals(email))
                .map(friend -> friend.getEmail2().equals(email)?friend.getEmail1():friend.getEmail2())
                .collect(Collectors.toList());
    }

}
