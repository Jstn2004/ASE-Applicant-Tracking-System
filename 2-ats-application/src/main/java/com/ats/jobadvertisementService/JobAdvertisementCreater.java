package com.ats.jobadvertisementService;

import com.ats.entities.EvaluationCriterion;
import com.ats.entities.JobAdvertisement;
import com.ats.interfaces.observer.JobAdvertisementCreatedObserver;
import com.ats.repositories.JobAdvertisementRepository;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class JobAdvertisementCreater {

    private final Logger logger;
    private final JobAdvertisementRepository jobAdvertisementRepository;
    private final List<JobAdvertisementCreatedObserver> observers;

    public JobAdvertisementCreater(JobAdvertisementRepository jobAdvertisementRepository,
                                   Logger logger,
                                   List<JobAdvertisementCreatedObserver> observers) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
        this.logger = logger;
        this.observers = observers;
    }

    public void createNewJobAdvertisement(String title, String description, List<EvaluationCriterion> criteria) {
        String id = UUID.randomUUID().toString();

        JobAdvertisement jobAdvertisement = JobAdvertisement.builder()
                .withId(id)
                .withTitel(title)
                .withDescription(description)
                .withCriteria(criteria)
                .build();

        jobAdvertisementRepository.saveJobAdvertisement(jobAdvertisement);

        notifyObservers(jobAdvertisement);
    }

    private void notifyObservers(JobAdvertisement jobAdvertisement) {
        for (JobAdvertisementCreatedObserver observer : observers) {
            observer.onJobAdvertisementCreated(jobAdvertisement);
        }
    }
}
