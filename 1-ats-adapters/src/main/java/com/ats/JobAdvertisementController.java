package com.ats;


import com.ats.entities.JobAdvertisement;
import com.ats.jobadvertisementService.JobAdvertisementCreater;
import com.ats.jobadvertisementService.JobAdvertisementDeleter;
import com.ats.jobadvertisementService.JobAdvertisementLoader;
import com.ats.jobadvertisementService.JobAdvertisementParser;

import java.util.ArrayList;
import java.util.List;

public class JobAdvertisementController {

    private JobAdvertisementDeleter jobAdvertisementDeleter;
    private JobAdvertisementCreater jobAdvertisementCreater;
    private JobAdvertisementLoader jobAdvertisementLoader;
    private JobAdvertisementParser jobAdvertisementParser;


    public JobAdvertisementController(JobAdvertisementDeleter jobAdvertisementDeleter, JobAdvertisementCreater jobAdvertisementCreater, JobAdvertisementLoader jobAdvertisementLoader, JobAdvertisementParser jobAdvertisementParser) {
        this.jobAdvertisementDeleter = jobAdvertisementDeleter;
        this.jobAdvertisementCreater = jobAdvertisementCreater;
        this.jobAdvertisementLoader = jobAdvertisementLoader;
        this.jobAdvertisementParser = jobAdvertisementParser;
    }

    public void createJobAdvertisement(String title, String description)
    {
        jobAdvertisementCreater.createNewTender(title, description);
    }

    public List<JobAdvertisement> loadAllTenders()
    {
        List<JobAdvertisement> jobAdvertisementsList = new ArrayList<>();
        jobAdvertisementLoader.loadAllTenders().forEach(item-> jobAdvertisementsList.add(jobAdvertisementParser.parseTenderString(item)));
        return jobAdvertisementsList;
    }

    public void deleteTenderById(String id)
    {
        jobAdvertisementDeleter.deleteTender(id);
    }

    public void createEvaluationCriterions()
    {

    }


}