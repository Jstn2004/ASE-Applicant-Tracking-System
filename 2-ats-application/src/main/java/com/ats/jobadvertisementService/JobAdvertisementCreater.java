package com.ats.jobadvertisementService;

import com.ats.entities.EvaluationCriterion;
import com.ats.entities.JobAdvertisement;
import com.ats.repositories.JobAdvertisementRepository;

import java.util.UUID;

public class JobAdvertisementCreater {

    private JobAdvertisementRepository jobAdvertisementRepository;

    public JobAdvertisementCreater(JobAdvertisementRepository jobAdvertisementRepository) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
    }

    public void createNewJobAdvertisement(String title, String description, Iterable<EvaluationCriterion> criteria) {
        String id = UUID.randomUUID().toString();
        JobAdvertisement jobAdvertisement = new JobAdvertisement(id, title, description, criteria);
        jobAdvertisementRepository.saveJobAdvertisement(jobAdvertisement);
    }

}
