package com.jbabineau.commuter.bootstrap;

import com.jbabineau.commuter.data.entities.TripType;
import com.jbabineau.commuter.data.repositories.TripTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{
    private TripTypeRepository tripTypeRepository;

    @Autowired
    public DevBootstrap(TripTypeRepository tripTypeRepository) {
        this.tripTypeRepository = tripTypeRepository;
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
    }


}
