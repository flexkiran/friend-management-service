package com.assignment.friendmanagement.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class EmailRequest {

    @NotNull(message = "email must be provided")
    @Email(message = "Invalid email id")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmailRequest() {
    }

    public EmailRequest(@NotNull @Email(message = "Invalid email id") String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailRequest that = (EmailRequest) o;

        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
