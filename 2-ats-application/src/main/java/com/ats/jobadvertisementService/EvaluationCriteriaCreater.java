package com.ats.jobadvertisementService;

import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.vo.Ability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EvaluationCriteriaCreater {

    public List<Ability> generateAbilityList(String abilityString) {
        List<Ability> abilities = new ArrayList<>();
        if (abilityString == null || abilityString.isEmpty()) {
            return new ArrayList<>();
        }
        Arrays.stream(abilityString.split(",\\s*"))
                .toList()
                .forEach(ability -> {
            abilities.add(new Ability(ability));
        });
        return abilities;

    }

    public EvaluationAbilities generateEvaluationAbilityCriteria(String name, int points, String abilityString, int weighting) {
        List<Ability> abilities = generateAbilityList(abilityString);
        EvaluationAbilities evaluationAbilities = new EvaluationAbilities(name, points, abilities, weighting);
        return evaluationAbilities;
    }

    public EvaluationExperience generateEvaluationExperienceCriteria() {
        return null;
    }

    public EvaluationKeywords generateEvaluationKeywordCriteria() {
        return null;
    }

}
