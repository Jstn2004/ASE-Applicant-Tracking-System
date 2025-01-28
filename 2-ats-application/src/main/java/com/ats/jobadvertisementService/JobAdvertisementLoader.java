package com.ats.jobadvertisementService;

import com.ats.entities.JobAdvertisement;
import com.ats.repositories.JobAdvertisementRepository;

import java.util.ArrayList;
import java.util.List;

public class JobAdvertisementLoader {

    private JobAdvertisementRepository jobAdvertisementRepository;

    public JobAdvertisementLoader(JobAdvertisementRepository jobAdvertisementRepository) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
    }

    public List<String> loadAllTenders()
    {
        return jobAdvertisementRepository.loadTender();
    }

}
