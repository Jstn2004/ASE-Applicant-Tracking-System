package com.ats.jobadvertisementService.EvaluationCriterionCreater;

import com.ats.entities.EvaluationCriterion;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.vo.Ability;
import com.ats.vo.Keyword;

import java.util.List;

public interface EvaluationCriterionFactory {
    EvaluationCriterion createCriterion(String name, List<?> items, int weighting);
}

