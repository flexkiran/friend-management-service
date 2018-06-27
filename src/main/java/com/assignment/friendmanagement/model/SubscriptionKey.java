package com.assignment.friendmanagement.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class SubscriptionKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "requestor")
    private Person requestor;
    @ManyToOne
    @JoinColumn(name = "target")
    private Person target;

    public SubscriptionKey() {
    }

    public SubscriptionKey(Person requestor, Person target) {
        this.requestor = requestor;
        this.target = target;
    }

    public Person getRequestor() {
        return requestor;
    }

    public Person getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionKey that = (SubscriptionKey) o;

        if (!requestor.equals(that.requestor)) return false;
        return target.equals(that.target);
    }

    @Override
    public int hashCode() {
        int result = requestor.hashCode();
        result = 31 * result + target.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SubscriptionKey{" +
                "requestor=" + requestor +
                ", target=" + target +
                '}';
    }
}
