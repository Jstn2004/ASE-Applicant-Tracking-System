package com.ats.repositories;

import com.ats.entities.JobAdvertisement;

import java.util.List;

public interface JobAdvertisementRepository {
        void saveJobAdvertisement(JobAdvertisement jobAdvertisement);
        List<String> loadTender();
        void deleteTenderById(String tenderId);
        String getJobAdvertisementById(String id);
}
