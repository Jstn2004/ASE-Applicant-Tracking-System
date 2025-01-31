package com.ats.jobadvertisementService;

import com.ats.entities.EvaluationCriterion;
import com.ats.entities.JobAdvertisement;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.vo.Ability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JobAdvertisementParser {

    public JobAdvertisement parseTenderString(String tenderString) {
        String regex = "JobAdvertisement\\{id='([\\w-]+)',\\s*titel='([^']*)',\\s*description='([^']*)',\\s*criteria=\\[(.*)\\]\\}";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(tenderString);

        if (matcher.find()) {
            String id = matcher.group(1);
            String title = matcher.group(2);
            String description = matcher.group(3);
            String criteria = matcher.group(4);
            return new JobAdvertisement(id, title, description,parseCriteria(criteria));
        }
        return null;
    }

    public static List<EvaluationCriterion> parseCriteria(String input) {
        ArrayList<EvaluationCriterion> criteria = new ArrayList<>();

        // Regex für EvaluationAbilities
        Pattern abilitiesPattern = Pattern.compile("EvaluationAbilities\\{name=([^,]+), points=(\\d+), listOfAbilities=\\[(.*?)\\], weighting=(\\d+)\\}");
        Matcher abilitiesMatcher = abilitiesPattern.matcher(input);

        while (abilitiesMatcher.find()) {
            String name = abilitiesMatcher.group(1).trim();
            int points = Integer.parseInt(abilitiesMatcher.group(2));
            List<String> abilities = Arrays.asList(abilitiesMatcher.group(3).split(",\\s*"));
            List<Ability> abilityList = new ArrayList<>();
            abilities.forEach(ability -> {
                abilityList.add(new Ability(ability));
            });
            int weighting = Integer.parseInt(abilitiesMatcher.group(4));

            criteria.add(new EvaluationAbilities(name, points, abilityList, weighting));
        }

        // Regex für EvaluationExperience
        Pattern experiencePattern = Pattern.compile("EvaluationExperience\\{name=([^,]+), points=(\\d+), experienceInYear=(\\d+), weighting=(\\d+)\\}");
        Matcher experienceMatcher = experiencePattern.matcher(input);

        while (experienceMatcher.find()) {
            String name = experienceMatcher.group(1).trim();
            int points = Integer.parseInt(experienceMatcher.group(2));
            int experienceInYear = Integer.parseInt(experienceMatcher.group(3));
            int weighting = Integer.parseInt(experienceMatcher.group(4));

            criteria.add(new EvaluationExperience(name, points, experienceInYear, weighting));
        }

        return criteria;
    }
}
