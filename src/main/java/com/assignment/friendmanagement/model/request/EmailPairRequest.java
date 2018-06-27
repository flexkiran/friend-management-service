package com.assignment.friendmanagement.model.request;

import com.assignment.friendmanagement.validator.EmailPairConstraint;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class EmailPairRequest {

    @EmailPairConstraint
    @NotNull(message = "Friends list can not be empty")
    @Size(max = 2, min = 2, message = "Friend list should contain exactly 2 emails")
    private List<String> friends;

    public EmailPairRequest() {
    }

    public EmailPairRequest(@NotNull(message = "Friends list can not be empty") @Size(max = 2, min = 2, message = "Friend list should contain exactly 2 emails")
                                    List<String> friends) {
        this.friends = friends;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailPairRequest that = (EmailPairRequest) o;

        return friends != null ? friends.equals(that.friends) : that.friends == null;
    }

    @Override
    public int hashCode() {
        return friends != null ? friends.hashCode() : 0;
    }
}
