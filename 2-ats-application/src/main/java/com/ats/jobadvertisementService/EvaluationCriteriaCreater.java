package com.ats.jobadvertisementService;

import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.vo.Ability;

import java.util.ArrayList;
import java.util.List;

public class EvaluationCriteriaCreater {

    public List<Ability> generateAbilityList(String abilityString)
    {
        return null;
    }
    public EvaluationAbilities generateEvaluationAbilityCriteria(String name, int points, List<Ability> abilities, int weighting){
        EvaluationAbilities evaluationAbilities = new EvaluationAbilities(name, points, abilities, weighting);
        return null;
    }
    public EvaluationExperience generateEvaluationExperienceCriteria(){
        return null;
    }

    public EvaluationKeywords generateEvaluationKeywordCriteria(){
        return null;
    }

}
