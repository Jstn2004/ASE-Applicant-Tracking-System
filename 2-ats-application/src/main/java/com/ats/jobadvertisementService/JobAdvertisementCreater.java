package com.ats.jobadvertisementService;

import com.ats.entities.EvaluationCriterion;
import com.ats.entities.JobAdvertisement;
import com.ats.repositories.JobAdvertisementRepository;

import java.util.UUID;
import java.util.logging.Logger;

public class JobAdvertisementCreater {

    private final Logger logger;
    private final JobAdvertisementRepository jobAdvertisementRepository;

    public JobAdvertisementCreater(JobAdvertisementRepository jobAdvertisementRepository, Logger logger) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
        this.logger = logger;
    }

    public void createNewJobAdvertisement(String title, String description, Iterable<EvaluationCriterion> criteria) {
        String id = UUID.randomUUID().toString();
        JobAdvertisement jobAdvertisement = new JobAdvertisement(id, title, description, criteria);
        jobAdvertisementRepository.saveJobAdvertisement(jobAdvertisement);
    }

}
