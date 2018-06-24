package com.assignment.friendmanagement.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class SubscriptionRequest {

    @Email
    @NotNull
    private String  requestor;

    @Email
    @NotNull
    private String target;

    public SubscriptionRequest() {
    }

    public SubscriptionRequest(@Email @NotNull String requestor, @Email @NotNull String target) {
        this.requestor = requestor;
        this.target = target;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionRequest that = (SubscriptionRequest) o;

        if (requestor != null ? !requestor.equals(that.requestor) : that.requestor != null) return false;
        return target != null ? target.equals(that.target) : that.target == null;
    }

    @Override
    public int hashCode() {
        int result = requestor != null ? requestor.hashCode() : 0;
        result = 31 * result + (target != null ? target.hashCode() : 0);
        return result;
    }
}
