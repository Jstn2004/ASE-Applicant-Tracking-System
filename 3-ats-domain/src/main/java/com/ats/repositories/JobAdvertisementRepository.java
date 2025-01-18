package com.ats.repositories;

import com.ats.entities.JobAdvertisement;

import java.util.List;

public interface JobAdvertisementRepository {
        void saveTender(JobAdvertisement jobAdvertisement);
        List<String> loadTender();
        void deleteTenderById(String tenderId);
}
