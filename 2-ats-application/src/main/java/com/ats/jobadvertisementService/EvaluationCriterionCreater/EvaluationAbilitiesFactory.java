package com.ats.jobadvertisementService.EvaluationCriterionCreater;

import com.ats.entities.EvaluationCriterion;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.vo.Ability;

import java.util.List;

public class EvaluationAbilitiesFactory implements EvaluationCriterionFactory {
    @Override
    public EvaluationCriterion createCriterion(String name, List<?> abilities, int weighting) {
        return new EvaluationAbilities(name, 1, (List<Ability>) abilities, weighting);
    }
}
