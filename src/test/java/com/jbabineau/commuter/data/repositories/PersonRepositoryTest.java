package com.jbabineau.commuter.data.repositories;

import com.jbabineau.commuter.data.entities.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;


    @Test
    public void findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndEmailIgnoreCase() throws Exception {
        // Arrange
        Person expected = new Person("jamie", "babs", "jb@hs.com", "1234");
        entityManager.persist(expected);
        entityManager.flush();;

        // Act
        Person actual = personRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndEmailIgnoreCase("jamie", "babs", "jb@hs.com");

        // Assert
        assertThat(actual.getFirstName(), is(expected.getFirstName()));
        assertThat(actual.getLastName(), is(expected.getLastName()));
        assertThat(actual.getEmail(), is(expected.getEmail()));
        assertThat(actual.getUniqueIdentifier(), is(expected.getUniqueIdentifier()));
    }

    @Test
    public void findByFirstNameIgnoreCase() throws Exception {
        // Arrange
        Person expected1 = new Person("jamie", "babs", "jb@hs.com", "1234");
        Person expected2 = new Person("jamie", "Doe", "jd@hs.com", "1235");
        Person dummyRecord = new Person("Jimmy", "babs", "jimmy@hs.com", "1236");
        entityManager.persist(expected1);
        entityManager.persist(expected2);
        entityManager.persist(dummyRecord);
        entityManager.flush();;

        // Act
        List<Person> actual = (List<Person>) personRepository.findByFirstNameIgnoreCase("jamie");

        // Assert
        assertThat(actual.size(), is(2));
        assertThat(actual, contains(expected1, expected2));
    }

    @Test
    public void findByLastNameIgnoreCase() throws Exception {
        // Arrange
        Person expected1 = new Person("jamie", "babs", "jb@hs.com", "1234");
        Person expected2 = new Person("Jimmy", "babs", "jimmy@hs.com", "1236");
        Person dummyRecord = new Person("jamie", "Doe", "jd@hs.com", "1235");

        entityManager.persist(expected1);
        entityManager.persist(expected2);
        entityManager.persist(dummyRecord);
        entityManager.flush();;

        // Act
        List<Person> actual = (List<Person>) personRepository.findByLastNameIgnoreCase("babs");

        // Assert
        assertThat(actual.size(), is(2));
        assertThat(actual, contains(expected1, expected2));
    }

    @Test
    public void findByEmailIgnoreCase() throws Exception {
        // Arrange
        Person expected1 = new Person("jamie", "babs", "jb@hs.com", "1234");
        Person expected2 = new Person("Jimmy", "babs", "jb@hs.com", "1236");
        Person dummyRecord = new Person("jamie", "Doe", "jd@hs.com", "1235");

        entityManager.persist(expected1);
        entityManager.persist(expected2);
        entityManager.persist(dummyRecord);
        entityManager.flush();;

        // Act
        List<Person> actual = (List<Person>) personRepository.findByEmailIgnoreCase("jb@hs.com");

        // Assert
        assertThat(actual.size(), is(2));
        assertThat(actual, contains(expected1, expected2));
    }

    @Test
    public void findByUniqueIdentifierIgnoreCase() throws Exception {
        // Arrange
        Person expected = new Person("jamie", "babs", "jb@hs.com", "1234");
        Person dummyRecord = new Person("jamie", "Doe", "jd@hs.com", "1235");
        entityManager.persist(expected);
        entityManager.persist(dummyRecord);
        entityManager.flush();;

        // Act
        Person actual = personRepository.findByUniqueIdentifierIgnoreCase("1234");

        // Assert
        assertThat(actual.getFirstName(), is(expected.getFirstName()));
        assertThat(actual.getLastName(), is(expected.getLastName()));
        assertThat(actual.getEmail(), is(expected.getEmail()));
        assertThat(actual.getUniqueIdentifier(), is(expected.getUniqueIdentifier()));
    }
}