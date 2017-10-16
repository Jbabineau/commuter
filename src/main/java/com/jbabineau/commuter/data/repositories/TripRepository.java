package com.jbabineau.commuter.data.repositories;

import com.jbabineau.commuter.data.entities.Person;
import com.jbabineau.commuter.data.entities.Trip;
import com.jbabineau.commuter.data.entities.TripType;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Set;

public interface TripRepository extends CrudRepository<Trip, Long>{
    Set<Trip> findAllByPerson(Person person);
    Set<Trip> findAllByPersonAndDepartureDate(Person person, Date date);
    Set<Trip> findAllByPersonAndAndTypeOfTrip(Person person, TripType typeOfTrip);
    Set<Trip> findAllByPersonAndDepartureDateAfter(Person person, Date date);
    Set<Trip> findAllByPersonAndDepartureDateBefore(Person person, Date date);
    Set<Trip> findAllByPersonAndDepartureDateAndToWork(Person person, Date date, boolean toWork);
}
