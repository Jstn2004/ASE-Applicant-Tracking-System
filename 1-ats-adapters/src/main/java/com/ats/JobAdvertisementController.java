package com.ats;


import com.ats.entities.EvaluationCriterion;
import com.ats.entities.JobAdvertisement;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.jobadvertisementService.*;
import com.ats.vo.Ability;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JobAdvertisementController {

    private Logger logger;
    private JobAdvertisementDeleter jobAdvertisementDeleter;
    private JobAdvertisementCreater jobAdvertisementCreater;
    private JobAdvertisementLoader jobAdvertisementLoader;
    private JobAdvertisementParser jobAdvertisementParser;
    private EvaluationCriteriaCreater evaluationCriteriaCreater;



    public JobAdvertisementController(Logger logger, JobAdvertisementDeleter jobAdvertisementDeleter, JobAdvertisementCreater jobAdvertisementCreater, JobAdvertisementLoader jobAdvertisementLoader, JobAdvertisementParser jobAdvertisementParser, EvaluationCriteriaCreater evaluationCriteriaCreater) {
        this.logger = logger;
        this.jobAdvertisementDeleter = jobAdvertisementDeleter;
        this.jobAdvertisementCreater = jobAdvertisementCreater;
        this.jobAdvertisementLoader = jobAdvertisementLoader;
        this.jobAdvertisementParser = jobAdvertisementParser;
        this.evaluationCriteriaCreater = evaluationCriteriaCreater;
    }

    public void createJobAdvertisement(String title, String description, Iterable<EvaluationCriterion> criteria)
    {
        this.logger.info("Creating Job Advertisement");
        this.logger.info(criteria.toString());
        jobAdvertisementCreater.createNewJobAdvertisement(title, description, criteria);
    }

    public List<JobAdvertisement> loadAllJobAdvertisement()
    {
        List<JobAdvertisement> jobAdvertisementsList = new ArrayList<>();
        jobAdvertisementLoader.loadAllTenders().forEach(item-> jobAdvertisementsList.add(jobAdvertisementParser.parseTenderString(item)));
        return jobAdvertisementsList;
    }

    public void deleteJobAdvertisementById(String id)
    {
        jobAdvertisementDeleter.deleteTender(id);
    }

    public EvaluationAbilities createEvaluationAbilities(String name, String points, String weighting, String abilities)
    {
        logger.info("Creating Evaluation Abilities");
        logger.info(abilities);
       int pointsInt = Integer.parseInt(points);
       int weightingInt = Integer.parseInt(weighting);
       return evaluationCriteriaCreater.generateEvaluationAbilityCriteria(name, pointsInt, abilities, weightingInt);
    }


}