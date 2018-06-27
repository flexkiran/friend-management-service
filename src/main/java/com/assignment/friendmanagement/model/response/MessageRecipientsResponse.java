package com.assignment.friendmanagement.model.response;

import java.util.List;

public class MessageRecipientsResponse {

    private boolean success;
    private List<String> recipients;

    public MessageRecipientsResponse() {
    }

    public MessageRecipientsResponse(boolean success, List<String> recipients) {
        this.success = success;
        this.recipients = recipients;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageRecipientsResponse that = (MessageRecipientsResponse) o;

        if (success != that.success) return false;
        return recipients != null ? recipients.equals(that.recipients) : that.recipients == null;
    }

    @Override
    public int hashCode() {
        int result = (success ? 1 : 0);
        result = 31 * result + (recipients != null ? recipients.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NotificationResponse{" +
                "success=" + success +
                ", recipients=" + recipients +
                '}';
    }
}
