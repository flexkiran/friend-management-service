package com.assignment.friendmanagement.model;


import com.assignment.friendmanagement.service.FriendsService;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "Friends")
public class Friends {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "person1")
    private Person person1;
    @ManyToOne
    @JoinColumn(name = "person2")
    private Person person2;

    public Friends(){}

    public Friends( Person person1, Person person2) {
        this.person1 = person1;
        this.person2 = person2;
    }


    public Person getPerson1() {
        return person1;
    }

    public Person getPerson2() {
        return person2;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friends friends = (Friends) o;

        if (id != friends.id) return false;
        if (!person1.equals(friends.person1)) return false;
        return person2.equals(friends.person2);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + person1.hashCode();
        result = 31 * result + person2.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "id=" + id +
                ", person1=" + person1 +
                ", person2=" + person2 +
                '}';
    }
}
