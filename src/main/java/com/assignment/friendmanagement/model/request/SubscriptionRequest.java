package com.assignment.friendmanagement.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubscriptionRequest {

    @NotNull(message = "email must be provided")
    @Email(message = "Invalid email id")
    @Size(min = 3 , max = 254 ,message = "email id should have min 3 characters")
    private String requestor;

    @NotNull(message = "email must be provided")
    @Email(message = "Invalid email id")
    @Size(min = 3 , max = 254 ,message = "email id should have min 3 characters")
    private String target;

    public SubscriptionRequest() {
    }

    public SubscriptionRequest(@NotNull(message = "email must be provided") @Email(message = "Invalid email id") String requestor,
                               @NotNull(message = "email must be provided") @Email(message = "Invalid email id") String target) {
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
