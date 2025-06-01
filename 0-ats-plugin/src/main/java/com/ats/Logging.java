package com.ats;

import com.ats.entities.JobAdvertisement;
import com.ats.interfaces.observer.JobAdvertisementCreatedObserver;

import java.util.logging.Logger;

public class Logging implements JobAdvertisementCreatedObserver {

    private final Logger logger;

    public Logging(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void onJobAdvertisementCreated(JobAdvertisement jobAdvertisement) {
        logger.info("Neues JobInserat erstellt: " + jobAdvertisement.getTitel());
    }
}
