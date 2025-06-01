package com.ats.evaluationCriterionService;

import com.ats.entities.EvaluationCriterion;

import java.util.List;

public interface EvaluationCriterionFactory {
    EvaluationCriterion createCriterion(String name, List<?> items, int weighting);
}

