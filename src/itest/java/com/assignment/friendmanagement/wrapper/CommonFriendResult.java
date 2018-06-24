package com.assignment.friendmanagement.wrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonFriendResult {

    private List<String> emails;
    private int count;
    private boolean success;

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CommonFriendResult(String emails, int count, boolean success) {
        if (emails != null) {
            this.emails = Arrays.asList(emails.split(";"));
        } else {
            this.emails = new ArrayList<String>();
        }
        this.count = count;
        this.success = success;
    }

    @Override
    public String toString() {
        return "CommonFriendResult{" +
                "emails=" + emails +
                ", count=" + count +
                ", success=" + success +
                '}';
    }
}
