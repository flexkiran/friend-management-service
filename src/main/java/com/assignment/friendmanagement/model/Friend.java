package com.assignment.friendmanagement.model;

public class Friend {

    private long id;
    private Email email1;
    private Email email2;

    public Friend(long id, Email email1, Email email2) {
        this.id = id;
        this.email1 = email1;
        this.email2 = email2;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail1(Email email1) {
        this.email1 = email1;
    }

    public void setEmail2(Email email2) {
        this.email2 = email2;
    }

    public long getId() {

        return id;
    }

    public Email getEmail1() {
        return email1;
    }

    public Email getEmail2() {
        return email2;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friend friends = (Friend) o;

        if (id != friends.id) return false;
        if (!email1.equals(friends.email1)) return false;
        return email2.equals(friends.email2);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + email1.hashCode();
        result = 31 * result + email2.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "id=" + id +
                ", email1=" + email1 +
                ", email2=" + email2 +
                '}';
    }
}
