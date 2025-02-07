package com.ats.jobadvertisementService;

import com.ats.repositories.JobAdvertisementRepository;

import java.util.logging.Logger;

public class JobAdvertisementDeleter {

    private final Logger logger;
    private final JobAdvertisementRepository jobAdvertisementRepository;

    public JobAdvertisementDeleter(JobAdvertisementRepository jobAdvertisementRepository, Logger logger) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
        this.logger = logger;
    }

    public void deleteTender(String id) {
        jobAdvertisementRepository.deleteTenderById(id);
    }

}
