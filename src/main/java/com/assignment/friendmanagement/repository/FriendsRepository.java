package com.assignment.friendmanagement.repository;

import com.assignment.friendmanagement.model.Person;
import com.assignment.friendmanagement.model.Friends;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface FriendsRepository extends CrudRepository<Friends, Long> {

    @Query(value = "SELECT * FROM FRIENDS f WHERE " +
            "(f.person1 = ?1 AND f.person2 = ?2) OR (f.person1 = ?2 AND f.person2 = ?1)",
            nativeQuery = true)
    List<Friends> getFriendship(Person person1, Person person2);

    @Query(value = "SELECT * FROM FRIENDS f WHERE (f.person1 = ?1 OR f.person2 = ?1)",
            nativeQuery = true)
    List<Friends> getAllFriends(Person person);

}
