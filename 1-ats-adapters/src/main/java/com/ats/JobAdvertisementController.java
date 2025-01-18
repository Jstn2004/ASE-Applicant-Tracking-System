package com.ats;


import com.ats.entities.JobAdvertisement;
import com.ats.jobadvertisementService.JobAdvertisementCreater;
import com.ats.jobadvertisementService.JobAdvertisementDeleter;
import com.ats.jobadvertisementService.JobAdvertisementLoader;
import com.ats.repositories.JobAdvertisementRepository;

import java.util.List;

public class JobAdvertisementController {

    private JobAdvertisementDeleter jobAdvertisementDeleter;
    private JobAdvertisementCreater jobAdvertisementCreater;
    private JobAdvertisementLoader jobAdvertisementLoader;


    public JobAdvertisementController(JobAdvertisementDeleter jobAdvertisementDeleter, JobAdvertisementCreater jobAdvertisementCreater, JobAdvertisementLoader jobAdvertisementLoader) {
        this.jobAdvertisementDeleter = jobAdvertisementDeleter;
        this.jobAdvertisementCreater = jobAdvertisementCreater;
        this.jobAdvertisementLoader = jobAdvertisementLoader;
    }

    public void createTender(String title, String description)
    {
        jobAdvertisementCreater.createNewTender(title, description);
    }

    public List<JobAdvertisement> loadAllTenders()
    {
        return jobAdvertisementLoader.loadAllTenders();
    }

    public void deleteTenderById(String id)
    {
        jobAdvertisementDeleter.deleteTender(id);
    }


}