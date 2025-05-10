package com.ats.evaluationCriterionService;

import com.ats.entities.EvaluationCriterion;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.vo.Ability;

import java.util.List;

public class EvaluationAbilitiesFactory implements EvaluationCriterionFactory {
    @Override
    public EvaluationAbilities createCriterion(String name, List<?> abilities, int weighting) {
        return new EvaluationAbilities(name, 1, (List<Ability>) abilities, weighting);
    }
}
