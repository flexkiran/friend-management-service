package com.assignment.friendmanagement.wrapper;

public class EmailPair {

    private String email1;
    private String email2;

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public EmailPair(String email1, String email2) {
        this.email1 = email1;
        this.email2 = email2;
    }

    @Override
    public String toString() {
        return "EmailPair{" +
                "person1='" + email1 + '\'' +
                ", person2='" + email2 + '\'' +
                '}';
    }
}
