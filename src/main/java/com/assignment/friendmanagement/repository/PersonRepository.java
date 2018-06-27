package com.assignment.friendmanagement.repository;

import com.assignment.friendmanagement.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findByEmail(String email);
}
