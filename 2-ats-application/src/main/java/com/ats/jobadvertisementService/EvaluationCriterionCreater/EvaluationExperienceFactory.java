package com.ats.jobadvertisementService.EvaluationCriterionCreater;

import com.ats.entities.EvaluationCriterion;
import com.ats.entities.criteria.EvaluationExperience;

import java.util.List;

public class EvaluationExperienceFactory implements EvaluationCriterionFactory {
    @Override
    public EvaluationCriterion createCriterion(String name, List<?> experience, int weighting) {
        return new EvaluationExperience(name, 1, (int) experience.get(0), weighting);
    }
}
