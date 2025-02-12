package com.ats;

import com.ats.validation.JobAdvertismentValidation;

import java.util.logging.Logger;

public class JobAdvertisementValidationController {

    private final Logger logger;
    private final JobAdvertismentValidation jobAdvertismentValidation;

    public JobAdvertisementValidationController(JobAdvertismentValidation jobAdvertismentValidation, Logger logger) {
        this.jobAdvertismentValidation = jobAdvertismentValidation;
        this.logger = logger;
    }

    public boolean startPointsValidation(String points) {
       return jobAdvertismentValidation.validatePoints(points);
    }

    public boolean startWeightingValidation(String weighting) {
        return jobAdvertismentValidation.validateWeighting(weighting);
    }

    public boolean startBlankInputValidation(String input)
    {
        return jobAdvertismentValidation.validateBlankInput(input);
    }

    public boolean startAbilitiesValidationOrKeyword(String ability) {
        return jobAdvertismentValidation.validateAbilitieOrKeyword(ability);
    }

    public boolean startExperienceValidation(String experience) {
        return jobAdvertismentValidation.isValidNumber(experience);
    }
}
