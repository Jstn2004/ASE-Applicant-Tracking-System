package com.ats.interfaces;

import com.ats.entities.JobAdvertisement;

import java.util.List;

public interface TenderRepository {
        void saveTender(JobAdvertisement jobAdvertisement);
        List<String> loadTender();
        void deleteTenderById(String tenderId);
}
