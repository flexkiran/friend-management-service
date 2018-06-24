package com.assignment.friendmanagement.wrapper;

public class NotificationEmailPair {

    private String requestor;
    private String target;

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

    public NotificationEmailPair(String requestor, String target) {
        this.requestor = requestor;
        this.target = target;
    }

    @Override
    public String toString() {
        return "NotificationEmailPair{" +
                "requestor='" + requestor + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
