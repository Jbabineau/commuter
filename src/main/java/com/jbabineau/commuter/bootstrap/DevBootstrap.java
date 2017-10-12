package com.jbabineau.commuter.bootstrap;

import com.jbabineau.commuter.data.entities.Person;
import com.jbabineau.commuter.data.entities.TripType;
import com.jbabineau.commuter.data.repositories.PersonRepository;
import com.jbabineau.commuter.data.repositories.TripTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{
    private TripTypeRepository tripTypeRepository;
    private PersonRepository personRepository;

    @Autowired
    public DevBootstrap(TripTypeRepository tripTypeRepository, PersonRepository personRepository) {
        this.tripTypeRepository = tripTypeRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }


    private void initData() {
        tripTypeRepository.save(new TripType("Train", "You are riding a train."));
        tripTypeRepository.save(new TripType("Subway", "You are riding the subway."));
        tripTypeRepository.save(new TripType("Bus", "You are riding a bus."));
        tripTypeRepository.save(new TripType("Ferry", "You're on a boat and you're riding fast."));
        tripTypeRepository.save(new TripType("Walking", "Getting around on those Sketchers of yours."));

        personRepository.save(new Person("Jimmy", "Babs", "babineau.james@gmail.com","001" ));
        personRepository.save(new Person("Denwa", "Griffon", "denwa.griffon@gmail.com","002" ));
        personRepository.save(new Person("Zak", "Attack", "ZakAttack003@gmail.com","003" ));

    }


}
