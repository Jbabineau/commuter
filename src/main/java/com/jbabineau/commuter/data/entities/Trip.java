package com.jbabineau.commuter.data.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date departureDate;
    private boolean toWork;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "trip_type_id")
    private TripType typeOfTrip;
    private int score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public boolean isToWork() {
        return toWork;
    }

    public void setToWork(boolean toWork) {
        this.toWork = toWork;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public TripType getTypeOfTrip() {
        return typeOfTrip;
    }

    public void setTypeOfTrip(TripType typeOfTrip) {
        this.typeOfTrip = typeOfTrip;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Trip() {
    }

    public Trip(Date departureDate, boolean toWork, Person person, TripType typeOfTrip, int score) {
        this.departureDate = departureDate;
        this.toWork = toWork;
        this.person = person;
        this.typeOfTrip = typeOfTrip;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", departureDate=" + departureDate +
                ", toWork=" + toWork +
                ", person=" + person +
                ", typeOfTrip=" + typeOfTrip +
                ", score=" + score +
                '}';
    }
}
