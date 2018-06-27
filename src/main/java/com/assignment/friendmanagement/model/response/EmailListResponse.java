package com.assignment.friendmanagement.model.response;

import java.util.List;

public class EmailListResponse {

    private List<String> emails;

    private boolean success;

    private int count;

    public EmailListResponse(List<String> emails, boolean success) {
        this.emails = emails;
        this.success = success;
        this.count = emails != null ? emails.size() : 0;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
        this.count = emails != null ? emails.size() : 0;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getEmails() {

        return emails;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailListResponse that = (EmailListResponse) o;

        if (success != that.success) return false;
        if (count != that.count) return false;
        return emails != null ? emails.equals(that.emails) : that.emails == null;
    }

    @Override
    public int hashCode() {
        int result = emails != null ? emails.hashCode() : 0;
        result = 31 * result + (success ? 1 : 0);
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        return "EmailListResponse{" +
                "emails=" + emails +
                ", success=" + success +
                ", count=" + count +
                '}';
    }
}
