package com.assignment.friendmanagement.wrapper;

import java.util.Arrays;
import java.util.List;

public class Subscribers {

    private boolean success;
    private List<String> emails;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Subscribers(boolean success, String emails) {
        this.success = success;
        this.emails = Arrays.asList(emails.split(";"));
    }

    @Override
    public String toString() {
        return "Subscribers{" +
                "success=" + success +
                ", emails=" + emails +
                '}';
    }
}
