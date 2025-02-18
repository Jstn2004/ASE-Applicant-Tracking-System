package com.ats.jobadvertisementService;

import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.vo.Ability;
import com.ats.vo.Keyword;

import java.util.List;
import java.util.logging.Logger;

public class EvaluationCriteriaCreater {

    private final Logger logger;

    public EvaluationCriteriaCreater(Logger logger) {
        this.logger = logger;
    }

    public Ability generateAbility(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        String[] abilities = input.split(";");
        Ability ability = new Ability(abilities[0].toLowerCase(), Integer.parseInt(abilities[1].replaceAll("\\s+", "")));
        return ability;
    }

    public Keyword generateKeyword(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        String[] keywords = input.split(";");
        Keyword keyword = new Keyword(keywords[0],Integer.parseInt(keywords[1].replaceAll("\\s+", "")) );
        return keyword;
    }

    public EvaluationAbilities generateEvaluationAbilityCriteria(String name,  List<Ability> abilityList, int weighting) {
        int points = 1;
        EvaluationAbilities evaluationAbilities = new EvaluationAbilities(name, points, abilityList, weighting);
        return evaluationAbilities;
    }

    public EvaluationExperience generateEvaluationExperienceCriteria(String name, int points, int experience, int weighting) {

        EvaluationExperience evaluationExperience = new EvaluationExperience(name, points, experience, weighting);
        return evaluationExperience;
    }

    public EvaluationKeywords generateEvaluationKeywordCriteria(String name, List<Keyword> keywordList, int weighting)
    {
        int points = 1;
        EvaluationKeywords evaluationKeywords = new EvaluationKeywords(name, points, keywordList, weighting);
        return evaluationKeywords;
    }

}
