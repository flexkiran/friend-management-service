package com.assignment.friendmanagement.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "Subscription")
public class Subscription {

    @EmbeddedId
    private SubscriptionKey id;
    @Enumerated(EnumType.STRING)
    private  SubscriptionStatus status;

    public Subscription(){}

    public Subscription(SubscriptionKey id, SubscriptionStatus status) {
        this.id = id;
        this.status = status;
    }

    public SubscriptionKey getId() {
        return id;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (!id.equals(that.id)) return false;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
