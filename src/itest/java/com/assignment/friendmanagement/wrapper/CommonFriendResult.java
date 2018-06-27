package com.assignment.friendmanagement.wrapper;

public class CommonFriendResult {

    private String emails;
    private int count;
    private boolean success;

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
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
        System.out.println("===============");
        System.out.println(emails);
        this.emails = emails;
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
