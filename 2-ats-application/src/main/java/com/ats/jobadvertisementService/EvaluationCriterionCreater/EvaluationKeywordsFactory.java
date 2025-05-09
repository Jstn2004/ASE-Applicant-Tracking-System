package com.ats.jobadvertisementService.EvaluationCriterionCreater;

import com.ats.entities.EvaluationCriterion;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.vo.Keyword;

import java.util.List;

public class EvaluationKeywordsFactory implements EvaluationCriterionFactory {
    @Override
    public EvaluationCriterion createCriterion(String name, List<?> keywords, int weighting) {
        return new EvaluationKeywords(name, 1, (List<Keyword>) keywords, weighting);
    }
}
