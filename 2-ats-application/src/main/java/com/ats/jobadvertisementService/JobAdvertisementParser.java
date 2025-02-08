package com.ats.jobadvertisementService;

import com.ats.entities.EvaluationCriterion;
import com.ats.entities.JobAdvertisement;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.vo.Ability;
import com.ats.vo.Keyword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JobAdvertisementParser {

    private final Logger logger ;

    public JobAdvertisementParser(Logger logger) {
        this.logger = logger;
    }

    public JobAdvertisement parseJobAdvertisementString(String jobAdvertisementString) {
        String regex = "JobAdvertisement\\{id='([\\w-]+)',\\s*titel='([^']*)',\\s*description='([^']*)',\\s*criteria=\\[(.*)\\]\\}";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(jobAdvertisementString);

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

        Pattern abilitiesPattern = Pattern.compile("EvaluationAbilities\\{name=([^,]+), listOfAbilities=\\[(.*?)\\], weighting=(\\d+)\\}");
        Matcher abilitiesMatcher = abilitiesPattern.matcher(input);

        while (abilitiesMatcher.find()) {
            String name = abilitiesMatcher.group(1).trim();
            String listOfAbilitiesStr = abilitiesMatcher.group(2).trim();
            String[] abilities = listOfAbilitiesStr.split(",\\s*");

            int weighting = Integer.parseInt(abilitiesMatcher.group(3).trim());

            List<Ability> abilityList = new ArrayList<>();

            for (String abilityStr : abilities) {
                Pattern abilityPattern = Pattern.compile("\\(([^;]+);\\s*(\\d+)\\)");
                Matcher abilityMatcher = abilityPattern.matcher(abilityStr);
                if (abilityMatcher.find()) {
                    String abilityName = abilityMatcher.group(1).trim();
                    int points = Integer.parseInt(abilityMatcher.group(2).trim());
                    abilityList.add(new Ability(abilityName, points));
                }
            }
            criteria.add(new EvaluationAbilities(name, 1, abilityList, weighting));
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

        // Regex für EvaluationKeywords
        Pattern keywordsPattern = Pattern.compile("EvaluationKeywords\\{name=([^,]+), listOfKeywords=\\[(.*?)\\], weighting=(\\d+)\\}");
        Matcher keywordsMatcher = keywordsPattern.matcher(input);

        while (keywordsMatcher.find()) {
            String name = keywordsMatcher.group(1).trim();
            String listOfKeywordsStr = keywordsMatcher.group(2).trim();
            String[] keywords = listOfKeywordsStr.split(",\\s*");

            int weighting = Integer.parseInt(keywordsMatcher.group(3).trim());

            List<Keyword> keywordList = new ArrayList<>();

            for (String keywordsStr : keywords) {
                Pattern keywordPattern = Pattern.compile("\\(([^;]+);\\s*(\\d+)\\)");
                Matcher keywordMatcher = keywordPattern.matcher(keywordsStr);
                if (keywordMatcher.find()) {
                    String keyword = keywordMatcher.group(1).trim();
                    int points = Integer.parseInt(keywordMatcher.group(2).trim());
                    keywordList.add(new Keyword(keyword, points));
                }
            }
            criteria.add(new EvaluationKeywords(name, 1, keywordList, weighting));
        }

        return criteria;
    }
}
