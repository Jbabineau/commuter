package com.jbabineau.commuter.data.repositories;

import com.jbabineau.commuter.data.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndEmailIgnoreCase(String firstName, String lastName, String email);
    Iterable<Person> findByFirstNameIgnoreCase(String firstName);
    Iterable<Person> findByLastNameIgnoreCase(String lastName);
    Iterable<Person> findByEmailIgnoreCase(String email);
    Person findByUniqueIdentifierIgnoreCase(String uniqueIdentifier);
}
