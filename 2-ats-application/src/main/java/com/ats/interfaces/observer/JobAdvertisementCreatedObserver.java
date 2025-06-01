package com.ats.interfaces.observer;

import com.ats.entities.JobAdvertisement;

public interface JobAdvertisementCreatedObserver {
    void onJobAdvertisementCreated(JobAdvertisement jobAdvertisement);
}
