package com.ats;

import com.ats.validation.JobAdvertismentValidation;

public class JobAdvertisementValidationController {


    private JobAdvertismentValidation jobAdvertismentValidation;

    public JobAdvertisementValidationController(JobAdvertismentValidation jobAdvertismentValidation) {
        this.jobAdvertismentValidation = jobAdvertismentValidation;
    }

    public boolean startPointsValidation(String points) {
       return jobAdvertismentValidation.validatePoints(points);
    }

    public boolean startWeightingValidation(String weighting) {
        return jobAdvertismentValidation.validateWeighting(weighting);
    }

    public boolean startExperienceValidation(String experience) {
        return jobAdvertismentValidation.isValidNumber(experience);
    }
}
