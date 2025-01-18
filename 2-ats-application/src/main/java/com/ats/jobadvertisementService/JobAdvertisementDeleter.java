package com.ats.jobadvertisementService;

import com.ats.repositories.JobAdvertisementRepository;

public class JobAdvertisementDeleter {

    private JobAdvertisementRepository jobAdvertisementRepository;

    public JobAdvertisementDeleter(JobAdvertisementRepository jobAdvertisementRepository) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
    }

    public void deleteTender(String id) {
        jobAdvertisementRepository.deleteTenderById(id);
    }

}
