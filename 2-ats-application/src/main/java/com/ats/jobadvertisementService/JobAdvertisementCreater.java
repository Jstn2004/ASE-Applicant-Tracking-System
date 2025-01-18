package com.ats.jobadvertisementService;

import com.ats.entities.JobAdvertisement;
import com.ats.repositories.JobAdvertisementRepository;

import java.util.UUID;

public class JobAdvertisementCreater {

    private JobAdvertisementRepository jobAdvertisementRepository;

    public JobAdvertisementCreater(JobAdvertisementRepository jobAdvertisementRepository) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
    }

    public void createNewTender(String title, String description) {
        String id = UUID.randomUUID().toString();
        JobAdvertisement jobAdvertisement = new JobAdvertisement(id, title, description);
        jobAdvertisementRepository.saveTender(jobAdvertisement);
    }

}
