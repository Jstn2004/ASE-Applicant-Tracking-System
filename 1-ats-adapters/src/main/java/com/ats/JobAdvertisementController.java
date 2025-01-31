package com.ats;


import com.ats.entities.EvaluationCriterion;
import com.ats.entities.JobAdvertisement;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
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

    private int pointsInt = 0;
    private int weightingInt  = 0;



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

    public EvaluationAbilities createEvaluationAbilities(List<String> evaluationCriterionArguments, String abilities)
    {
        logger.info("Creating Evaluation Abilities");
        logger.info(abilities);
       parseStringtoInterges(evaluationCriterionArguments.get(1), evaluationCriterionArguments.getLast());
       return evaluationCriteriaCreater.generateEvaluationAbilityCriteria(evaluationCriterionArguments.getFirst(), pointsInt, abilities, weightingInt);
    }

    public void parseStringtoInterges(String points, String weighting)
    {
        pointsInt = Integer.parseInt(points);
        weightingInt = Integer.parseInt(weighting);
    }

    public EvaluationExperience createEvaluationExperience(List<String> evaluationCriterionArguments, String experience)
    {
        logger.info("Creating EvaluationExperience");
        logger.info(experience);
        parseStringtoInterges(evaluationCriterionArguments.get(1), evaluationCriterionArguments.getLast());
        int experienceInt = Integer.parseInt(experience);
        return evaluationCriteriaCreater.generateEvaluationExperienceCriteria(evaluationCriterionArguments.getFirst(), pointsInt,experienceInt, weightingInt);
    }


}