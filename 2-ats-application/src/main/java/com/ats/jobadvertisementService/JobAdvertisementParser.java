package com.ats.jobadvertisementService;

import com.ats.entities.EvaluationCriterion;
import com.ats.entities.JobAdvertisement;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.vo.Ability;
import com.ats.vo.Keyword;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JobAdvertisementParser {

    private final Logger logger;

    public JobAdvertisementParser(Logger logger) {
        this.logger = logger;
    }

    public JobAdvertisement parseJobAdvertisementString(String jobAdvertisementString) {
        if (jobAdvertisementString == null || jobAdvertisementString.isBlank()) {
            throw new IllegalArgumentException("Eingabe darf nicht leer oder null sein.");
        }

        String regex = "JobAdvertisement\\{id='([\\w-]+)',\\s*titel='([^']*)',\\s*description='([^']*)',\\s*criteria=\\[(.*)\\]\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(jobAdvertisementString);

        if (matcher.find()) {
            String id = matcher.group(1);
            String title = matcher.group(2);
            String description = matcher.group(3);
            String criteria = matcher.group(4);
            return new JobAdvertisement(id, title, description, parseCriteria(criteria));
        }

        throw new IllegalArgumentException("Ungültiges JobAdvertisement-Format: " + jobAdvertisementString);
    }

    public static List<EvaluationCriterion> parseCriteria(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Kriterieneingabe darf nicht leer sein.");
        }

        List<EvaluationCriterion> criteria = new ArrayList<>();
        criteria.addAll(parseAbilities(input));
        criteria.addAll(parseExperience(input));
        criteria.addAll(parseKeywords(input));

        if (criteria.isEmpty()) {
            throw new IllegalArgumentException("Keine gültigen Bewertungskriterien erkannt.");
        }

        return criteria;
    }

    private static List<EvaluationCriterion> parseAbilities(String input) {
        List<EvaluationCriterion> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("EvaluationAbilities\\{name=([^,]+), listOfAbilities=\\[(.*?)\\], weighting=(\\d+)\\}");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String name = matcher.group(1).trim();
            String[] abilities = matcher.group(2).trim().split(",\\s*");

            int weighting = parsePositiveInt(matcher.group(3), "Gewichtung (Abilities)");

            List<Ability> abilityList = new ArrayList<>();
            for (String abilityStr : abilities) {
                Matcher abilityMatcher = Pattern.compile("\\(([^;]+);\\s*(\\d+)\\)").matcher(abilityStr);
                if (abilityMatcher.find()) {
                    String abilityName = abilityMatcher.group(1).trim();
                    int points = parsePositiveInt(abilityMatcher.group(2), "Punkte (Ability)");
                    abilityList.add(new Ability(abilityName, points));
                } else {
                    throw new IllegalArgumentException("Ungültiges Ability-Format: " + abilityStr);
                }
            }

            result.add(new EvaluationAbilities(name, 1, abilityList, weighting));
        }

        return result;
    }

    private static List<EvaluationCriterion> parseExperience(String input) {
        List<EvaluationCriterion> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("EvaluationExperience\\{name=([^,]+), points=(\\d+), experienceInYear=(\\d+), weighting=(\\d+)\\}");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String name = matcher.group(1).trim();
            int points = parsePositiveInt(matcher.group(2), "Punkte (Experience)");
            int experienceInYear = parsePositiveInt(matcher.group(3), "Erfahrung in Jahren");
            int weighting = parsePositiveInt(matcher.group(4), "Gewichtung (Experience)");

            result.add(new EvaluationExperience(name, points, experienceInYear, weighting));
        }

        return result;
    }

    private static List<EvaluationCriterion> parseKeywords(String input) {
        List<EvaluationCriterion> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("EvaluationKeywords\\{name=([^,]+), listOfKeywords=\\[(.*?)\\], weighting=(\\d+)\\}");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String name = matcher.group(1).trim();
            String[] keywords = matcher.group(2).trim().split(",\\s*");

            int weighting = parsePositiveInt(matcher.group(3), "Gewichtung (Keywords)");

            List<Keyword> keywordList = new ArrayList<>();
            for (String keywordStr : keywords) {
                Matcher keywordMatcher = Pattern.compile("\\(([^;]+);\\s*(\\d+)\\)").matcher(keywordStr);
                if (keywordMatcher.find()) {
                    String keyword = keywordMatcher.group(1).trim();
                    int points = parsePositiveInt(keywordMatcher.group(2), "Punkte (Keyword)");
                    keywordList.add(new Keyword(keyword, points));
                } else {
                    throw new IllegalArgumentException("Ungültiges Keyword-Format: " + keywordStr);
                }
            }

            result.add(new EvaluationKeywords(name, 1, keywordList, weighting));
        }

        return result;
    }

    private static int parsePositiveInt(String input, String fieldName) {
        try {
            int value = Integer.parseInt(input.trim());
            if (value < 0) {
                throw new IllegalArgumentException(fieldName + " darf nicht negativ sein: " + input);
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ungültiger ganzzahliger Wert für " + fieldName + ": " + input, e);
        }
    }
}