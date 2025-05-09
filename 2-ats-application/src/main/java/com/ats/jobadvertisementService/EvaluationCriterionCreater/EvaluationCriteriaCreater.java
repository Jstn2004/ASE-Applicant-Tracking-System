package com.ats.jobadvertisementService.EvaluationCriterionCreater;

import com.ats.entities.EvaluationCriterion;
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

    public EvaluationCriterion createEvaluationCriterion(EvaluationCriterionFactory factory, String name, List<?> items, int weighting) {
        return factory.createCriterion(name, items, weighting);
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



}
