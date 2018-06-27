package com.assignment.friendmanagement.wrapper;

import java.util.Arrays;
import java.util.List;

public class Subscribers {

    private boolean success;
    private String emails;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Subscribers(boolean success, String emails) {
        this.success = success;
        this.emails = emails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscribers that = (Subscribers) o;

        if (success != that.success) return false;
        return emails != null ? emails.equals(that.emails) : that.emails == null;
    }

    @Override
    public int hashCode() {
        int result = (success ? 1 : 0);
        result = 31 * result + (emails != null ? emails.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Subscribers{" +
                "success=" + success +
                ", emails='" + emails + '\'' +
                '}';
    }
}
