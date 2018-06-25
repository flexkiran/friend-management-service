package com.assignment.friendmanagement.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MessagingRequest {

    @Email
    @NotNull
    private String sender;

    @NotEmpty
    private String text;

    public MessagingRequest(){}

    public MessagingRequest(@Email @NotNull String sender, @NotEmpty String text) {
        this.sender = sender;
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessagingRequest that = (MessagingRequest) o;

        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        return text != null ? text.equals(that.text) : that.text == null;
    }

    @Override
    public int hashCode() {
        int result = sender != null ? sender.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}