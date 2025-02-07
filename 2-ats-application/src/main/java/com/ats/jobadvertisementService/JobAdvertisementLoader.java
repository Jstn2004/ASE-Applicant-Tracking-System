package com.ats.jobadvertisementService;

import com.ats.repositories.JobAdvertisementRepository;

import java.util.List;
import java.util.logging.Logger;

public class JobAdvertisementLoader {

    private final Logger logger;
    private final JobAdvertisementRepository jobAdvertisementRepository;

    public JobAdvertisementLoader(JobAdvertisementRepository jobAdvertisementRepository, Logger logger) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
        this.logger = logger;
    }

    public List<String> loadAllJobAdvertisement()
    {
        return jobAdvertisementRepository.loadTender();
    }

}
