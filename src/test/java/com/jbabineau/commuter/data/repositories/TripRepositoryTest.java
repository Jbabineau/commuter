package com.jbabineau.commuter.data.repositories;

import com.jbabineau.commuter.data.entities.Person;
import com.jbabineau.commuter.data.entities.Trip;
import com.jbabineau.commuter.data.entities.TripType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TripRepositoryTest {

    private final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TripRepository tripRepository;

    @Test
    public void findAllByPerson() throws Exception {
        // Arrange
        Person personToFind = new Person("First", "Last", "first.last@gmail.com", "001");
        Person personToNotFind = new Person("John", "Doe", "John.Doe@gmail.com", "002");

        entityManager.persist(personToFind);
        entityManager.persist(personToNotFind);

        TripType commuterRail = new TripType("CommuterRail", "CommuterRail");
        TripType bus = new TripType("Bus", "Bus");

        entityManager.persist(commuterRail);
        entityManager.persist(bus);

        Trip expectedOne = new Trip(dateFormat.parse("01/01/2018"), true, personToFind, commuterRail, 0);
        Trip expectedTwo = new Trip(dateFormat.parse("01/01/2018"), false, personToFind, commuterRail, 0);
        Trip unExpectedOne = new Trip(dateFormat.parse("01/01/2018"), true, personToNotFind, commuterRail, 0);
        Trip unExpectedTwo = new Trip(dateFormat.parse("01/01/2018"), false, personToNotFind, commuterRail, 0);

        entityManager.persist(expectedOne);
        entityManager.persist(expectedTwo);
        entityManager.persist(unExpectedOne);
        entityManager.persist(unExpectedTwo);

        // Act
        Set<Trip> actualTrips = tripRepository.findAllByPerson(personToFind);

        // Assert
        assertThat(actualTrips.size(), is(2));
        assertThat(actualTrips, containsInAnyOrder(expectedOne, expectedTwo));

    }

    @Test
    public void findAllByPersonAndDepartureDate() throws Exception {
        // Arrange
        Person personToFind = new Person("First", "Last", "first.last@gmail.com", "001");
        Person personToNotFind = new Person("John", "Doe", "John.Doe@gmail.com", "002");

        entityManager.persist(personToFind);
        entityManager.persist(personToNotFind);

        TripType commuterRail = new TripType("CommuterRail", "CommuterRail");
        TripType bus = new TripType("Bus", "Bus");

        entityManager.persist(commuterRail);
        entityManager.persist(bus);

        Trip expectedOne = new Trip(dateFormat.parse("01/01/2018"), true, personToFind, commuterRail, 0);
        Trip expectedTwo = new Trip(dateFormat.parse("01/01/2018"), false, personToFind, commuterRail, 0);
        Trip unExpectedOne = new Trip(dateFormat.parse("01/01/2018"), true, personToNotFind, commuterRail, 0);
        Trip unExpectedTwo = new Trip(dateFormat.parse("01/01/2018"), false, personToNotFind, commuterRail, 0);
        Trip unExpectedThree = new Trip(dateFormat.parse("01/02/2018"), false, personToFind, commuterRail, 0);

        entityManager.persist(expectedOne);
        entityManager.persist(expectedTwo);
        entityManager.persist(unExpectedOne);
        entityManager.persist(unExpectedTwo);
        entityManager.persist(unExpectedThree);

        // Act
        Set<Trip> actualTrips = tripRepository.findAllByPersonAndDepartureDate(personToFind, expectedOne.getDepartureDate());

        // Assert
        assertThat(actualTrips.size(), is(2));
        assertThat(actualTrips, containsInAnyOrder(expectedOne, expectedTwo));

    }

    @Test
    public void findAllByPersonAndAndTypeOfTrip() throws Exception {
        // Arrange
        Person personToFind = new Person("First", "Last", "first.last@gmail.com", "001");
        Person personToNotFind = new Person("John", "Doe", "John.Doe@gmail.com", "002");

        entityManager.persist(personToFind);
        entityManager.persist(personToNotFind);

        TripType commuterRail = new TripType("CommuterRail", "CommuterRail");
        TripType bus = new TripType("Bus", "Bus");

        entityManager.persist(commuterRail);
        entityManager.persist(bus);

        Trip expectedOne = new Trip(dateFormat.parse("01/01/2018"), true, personToFind, commuterRail, 0);
        Trip expectedTwo = new Trip(dateFormat.parse("01/01/2018"), false, personToFind, commuterRail, 0);
        Trip unExpectedOne = new Trip(dateFormat.parse("01/01/2018"), true, personToNotFind, commuterRail, 0);
        Trip unExpectedTwo = new Trip(dateFormat.parse("01/01/2018"), false, personToNotFind, commuterRail, 0);
        Trip unExpectedThree = new Trip(dateFormat.parse("01/01/2018"), false, personToFind, bus, 0);

        entityManager.persist(expectedOne);
        entityManager.persist(expectedTwo);
        entityManager.persist(unExpectedOne);
        entityManager.persist(unExpectedTwo);
        entityManager.persist(unExpectedThree);

        // Act
        Set<Trip> actualTrips = tripRepository.findAllByPersonAndAndTypeOfTrip(personToFind, commuterRail);

        // Assert
        assertThat(actualTrips.size(), is(2));
        assertThat(actualTrips, containsInAnyOrder(expectedOne, expectedTwo));
    }

    @Test
    public void findAllByPersonAndDepartureDateAfter() throws Exception {
        // Arrange
        Person personToFind = new Person("First", "Last", "first.last@gmail.com", "001");
        Person personToNotFind = new Person("John", "Doe", "John.Doe@gmail.com", "002");

        entityManager.persist(personToFind);
        entityManager.persist(personToNotFind);

        TripType commuterRail = new TripType("CommuterRail", "CommuterRail");
        TripType bus = new TripType("Bus", "Bus");

        entityManager.persist(commuterRail);
        entityManager.persist(bus);

        Trip expectedOne = new Trip(dateFormat.parse("01/01/2018"), true, personToFind, commuterRail, 0);
        Trip expectedTwo = new Trip(dateFormat.parse("01/01/2018"), false, personToFind, commuterRail, 0);
        Trip unExpectedOne = new Trip(dateFormat.parse("01/01/2018"), true, personToNotFind, commuterRail, 0);
        Trip unExpectedTwo = new Trip(dateFormat.parse("01/01/2018"), false, personToNotFind, commuterRail, 0);
        Trip unExpectedThree = new Trip(dateFormat.parse("12/31/2017"), true, personToFind, commuterRail, 0);

        entityManager.persist(expectedOne);
        entityManager.persist(expectedTwo);
        entityManager.persist(unExpectedOne);
        entityManager.persist(unExpectedTwo);
        entityManager.persist(unExpectedThree);

        // Act
        Set<Trip> actualTrips = tripRepository.findAllByPersonAndDepartureDateAfter(personToFind, unExpectedThree.getDepartureDate());

        // Assert
        assertThat(actualTrips.size(), is(2));
        assertThat(actualTrips, containsInAnyOrder(expectedOne, expectedTwo));
    }

    @Test
    public void findAllByPersonAndDepartureDateBefore() throws Exception {
        // Arrange
        Person personToFind = new Person("First", "Last", "first.last@gmail.com", "001");
        Person personToNotFind = new Person("John", "Doe", "John.Doe@gmail.com", "002");

        entityManager.persist(personToFind);
        entityManager.persist(personToNotFind);

        TripType commuterRail = new TripType("CommuterRail", "CommuterRail");
        TripType bus = new TripType("Bus", "Bus");

        entityManager.persist(commuterRail);
        entityManager.persist(bus);

        Trip expectedOne = new Trip(dateFormat.parse("01/01/2018"), true, personToFind, commuterRail, 0);
        Trip expectedTwo = new Trip(dateFormat.parse("01/01/2018"), false, personToFind, commuterRail, 0);
        Trip unExpectedOne = new Trip(dateFormat.parse("01/01/2018"), true, personToNotFind, commuterRail, 0);
        Trip unExpectedTwo = new Trip(dateFormat.parse("01/01/2018"), false, personToNotFind, commuterRail, 0);
        Trip unExpectedThree = new Trip(dateFormat.parse("01/02/2018"), true, personToFind, commuterRail, 0);

        entityManager.persist(expectedOne);
        entityManager.persist(expectedTwo);
        entityManager.persist(unExpectedOne);
        entityManager.persist(unExpectedTwo);
        entityManager.persist(unExpectedThree);

        // Act
        Set<Trip> actualTrips = tripRepository.findAllByPersonAndDepartureDateBefore(personToFind, unExpectedThree.getDepartureDate());

        // Assert
        assertThat(actualTrips.size(), is(2));
        assertThat(actualTrips, containsInAnyOrder(expectedOne, expectedTwo));

    }

    @Test
    public void findAllByPersonAndDateAndToWork() throws Exception {
        // Arrange
        Person personToFind = new Person("First", "Last", "first.last@gmail.com", "001");
        Person personToNotFind = new Person("John", "Doe", "John.Doe@gmail.com", "002");

        entityManager.persist(personToFind);
        entityManager.persist(personToNotFind);

        TripType commuterRail = new TripType("CommuterRail", "CommuterRail");
        TripType bus = new TripType("Bus", "Bus");

        entityManager.persist(commuterRail);
        entityManager.persist(bus);

        Trip expectedOne = new Trip(dateFormat.parse("01/01/2018"), true, personToFind, commuterRail, 0);
        Trip unExpectedFour = new Trip(dateFormat.parse("01/01/2018"), false, personToFind, commuterRail, 0);
        Trip unExpectedOne = new Trip(dateFormat.parse("01/01/2018"), true, personToNotFind, commuterRail, 0);
        Trip unExpectedTwo = new Trip(dateFormat.parse("01/01/2018"), false, personToNotFind, commuterRail, 0);
        Trip unExpectedThree = new Trip(dateFormat.parse("01/02/2018"), true, personToFind, commuterRail, 0);

        entityManager.persist(expectedOne);
        entityManager.persist(unExpectedFour);
        entityManager.persist(unExpectedOne);
        entityManager.persist(unExpectedTwo);
        entityManager.persist(unExpectedThree);

        // Act
        Set<Trip> actualTrips = tripRepository.findAllByPersonAndDepartureDateAndToWork(personToFind, expectedOne.getDepartureDate(), true);

        // Assert
        assertThat(actualTrips.size(), is(1));
        assertThat(actualTrips, containsInAnyOrder(expectedOne));
    }

}