package com.ats;


import com.ats.entities.EvaluationCriterion;
import com.ats.entities.JobAdvertisement;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.evaluationCriterionService.EvaluationAbilitiesFactory;
import com.ats.evaluationCriterionService.AbilityKeywordCreator;
import com.ats.evaluationCriterionService.EvaluationExperienceFactory;
import com.ats.evaluationCriterionService.EvaluationKeywordsFactory;
import com.ats.jobadvertisementService.*;
import com.ats.vo.Ability;
import com.ats.vo.Keyword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class JobAdvertisementController {

    private final Logger logger;
    private final JobAdvertisementDeleter jobAdvertisementDeleter;
    private final JobAdvertisementCreater jobAdvertisementCreater;
    private final JobAdvertisementLoader jobAdvertisementLoader;
    private final JobAdvertisementParser jobAdvertisementParser;
    private final AbilityKeywordCreator abilityKeywordCreator;
    private final EvaluationKeywordsFactory evaluationKeywordsFactory;
    private final EvaluationAbilitiesFactory evaluationAbilitiesFactory;
    private final EvaluationExperienceFactory evaluationExperienceFactory;
    private final EvaluationCriteriaCreater evaluationCriteriaCreater;

    private int pointsInt = 0;
    private int weightingInt  = 0;



    public JobAdvertisementController(Logger logger, JobAdvertisementDeleter jobAdvertisementDeleter, JobAdvertisementCreater jobAdvertisementCreater, JobAdvertisementLoader jobAdvertisementLoader, JobAdvertisementParser jobAdvertisementParser, AbilityKeywordCreator abilityKeywordCreator, EvaluationKeywordsFactory evaluationKeywordsFactory, EvaluationAbilitiesFactory evaluationAbilitiesFactory, EvaluationExperienceFactory evaluationExperienceFactory, EvaluationCriteriaCreater evaluationCriteriaCreater) {
        this.logger = logger;
        this.jobAdvertisementDeleter = jobAdvertisementDeleter;
        this.jobAdvertisementCreater = jobAdvertisementCreater;
        this.jobAdvertisementLoader = jobAdvertisementLoader;
        this.jobAdvertisementParser = jobAdvertisementParser;
        this.abilityKeywordCreator = abilityKeywordCreator;
        this.evaluationKeywordsFactory = evaluationKeywordsFactory;
        this.evaluationAbilitiesFactory = evaluationAbilitiesFactory;
        this.evaluationExperienceFactory = evaluationExperienceFactory;
        this.evaluationCriteriaCreater = evaluationCriteriaCreater;
    }

    public void createJobAdvertisement(String title, String description, List<EvaluationCriterion> criteria)
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
        jobAdvertisementDeleter.deleteJobAdvertisement(id);
    }

    public Ability createAbility(String input)
    {
        logger.info("Creating Ability");
        return abilityKeywordCreator.generateAbility(input);
    }

    public Keyword createKeyword(String input)
    {
        logger.info("Creating Keyword");
        return abilityKeywordCreator.generateKeyword(input);

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
        return evaluationAbilitiesFactory.createCriterion(evaluationCriterionArguments.getFirst(), abilities, weightingInt);
    }

    public EvaluationExperience createEvaluationExperience(List<String> evaluationCriterionArguments, String experience)
    {
        System.out.println("Started creating EvaluationExperience");
        List<Integer> experienceList = new ArrayList<>(List.of());
        logger.info("Creating EvaluationExperience");
        logger.info(experience);
        parseStringtoInterges(evaluationCriterionArguments.get(2), evaluationCriterionArguments.getLast());
        System.out.println("First");
        int experienceInt = Integer.parseInt(experience);
        System.out.println("Second");
        System.out.println(experienceInt);
        experienceList.add(experienceInt);
        System.out.println(pointsInt);
        experienceList.add(pointsInt);
        System.out.println("List:" + experienceList);
        return evaluationExperienceFactory.createCriterion(evaluationCriterionArguments.getFirst(), experienceList, weightingInt);
    }

    public EvaluationKeywords createEvaluationKeywords(List<String> evaluationCriterionArguments, List<Keyword> keywords)
    {
        logger.info("Creating EvaluationKeywords");
        parseStringtoInterges(evaluationCriterionArguments.get(1), evaluationCriterionArguments.getLast());
        return evaluationKeywordsFactory.createCriterion(evaluationCriterionArguments.getFirst(), keywords, weightingInt);
    }


}