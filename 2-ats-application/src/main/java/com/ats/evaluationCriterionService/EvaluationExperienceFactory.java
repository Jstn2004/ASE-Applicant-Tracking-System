package com.ats.evaluationCriterionService;

import com.ats.entities.EvaluationCriterion;
import com.ats.entities.criteria.EvaluationExperience;

import java.util.List;

public class EvaluationExperienceFactory implements EvaluationCriterionFactory {
    @Override
    public EvaluationExperience createCriterion(String name, List<?> experience, int weighting) {
        return new EvaluationExperience(name, (int) experience.get(1), (int) experience.get(0), weighting);
    }
}
