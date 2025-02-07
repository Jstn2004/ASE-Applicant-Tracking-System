package com.ats;


import com.ats.entities.EvaluationCriterion;
import com.ats.entities.JobAdvertisement;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.jobadvertisementService.*;
import com.ats.vo.Ability;
import com.ats.vo.Keyword;

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
        jobAdvertisementLoader.loadAllJobAdvertisement().forEach(item-> jobAdvertisementsList.add(jobAdvertisementParser.parseJobAdvertisementString(item)));
        return jobAdvertisementsList;
    }

    public void deleteJobAdvertisementById(String id)
    {
        jobAdvertisementDeleter.deleteTender(id);
    }

    public Ability createAbility(String input)
    {
        logger.info("Creating Ability");
        return evaluationCriteriaCreater.generateAbility(input);
    }

    public Keyword createKeyword(String input)
    {
        logger.info("Creating Keyword");
        return evaluationCriteriaCreater.generateKeyword(input);

    }


    public void parseStringtoInterges(String points, String weighting)
    {
        pointsInt = Integer.parseInt(points);
        weightingInt = Integer.parseInt(weighting);
    }

    public EvaluationAbilities createEvaluationAbilities(List<String> evaluationCriterionArguments, List<Ability> abilities)
    {
        logger.info("Creating Evaluation Abilities");
        logger.info(abilities.toString());
        parseStringtoInterges(evaluationCriterionArguments.get(1), evaluationCriterionArguments.getLast());
        return evaluationCriteriaCreater.generateEvaluationAbilityCriteria(evaluationCriterionArguments.getFirst(), abilities, weightingInt);
    }

    public EvaluationExperience createEvaluationExperience(List<String> evaluationCriterionArguments, String experience)
    {
        logger.info("Creating EvaluationExperience");
        logger.info(experience);
        parseStringtoInterges(evaluationCriterionArguments.get(1), evaluationCriterionArguments.getLast());
        int experienceInt = Integer.parseInt(experience);
        return evaluationCriteriaCreater.generateEvaluationExperienceCriteria(evaluationCriterionArguments.getFirst(), pointsInt,experienceInt, weightingInt);
    }

    public EvaluationKeywords createEvaluationKeywords(List<String> evaluationCriterionArguments, List<Keyword> keywords)
    {
        logger.info("Creating EvaluationKeywords");
        parseStringtoInterges(evaluationCriterionArguments.get(1), evaluationCriterionArguments.getLast());
        return evaluationCriteriaCreater.generateEvaluationKeywordCriteria(evaluationCriterionArguments.getFirst(), keywords, weightingInt);
    }


}