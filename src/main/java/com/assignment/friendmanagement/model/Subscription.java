package com.assignment.friendmanagement.model;

public class Subscription {

    private long id;
    private Email requestor;
    private Email target;
    private int status;

    public Subscription(Email requestor, Email target, int status) {
        this.requestor = requestor;
        this.target = target;
        this.status = status;
    }

    public Subscription(long id, Email requestor, Email target, int status) {
        this.id = id;
        this.requestor = requestor;
        this.target = target;
        this.status = status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRequestor(Email requestor) {
        this.requestor = requestor;
    }

    public void setTarget(Email target) {
        this.target = target;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {

        return id;
    }

    public Email getRequestor() {
        return requestor;
    }

    public Email getTarget() {
        return target;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (!requestor.equals(that.requestor)) return false;
        return target.equals(that.target);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + requestor.hashCode();
        result = 31 * result + target.hashCode();
        result = 31 * result + status;
        return result;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", requestor=" + requestor +
                ", target=" + target +
                ", status=" + status +
                '}';
    }
}
