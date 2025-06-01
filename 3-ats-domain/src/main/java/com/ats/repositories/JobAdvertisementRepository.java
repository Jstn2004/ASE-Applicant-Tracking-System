package com.ats.repositories;

import com.ats.entities.JobAdvertisement;

import java.util.List;

public interface JobAdvertisementRepository {
        void saveJobAdvertisement(JobAdvertisement jobAdvertisement);
        List<String> loadJobAdvertisement();
        void deleteJobAdvertisementById(String tenderId);
        String getJobAdvertisementById(String id);
}
