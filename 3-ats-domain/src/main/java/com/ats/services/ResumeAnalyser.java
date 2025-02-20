package com.ats.services;

import com.ats.entities.Applicant;
import com.ats.entities.EvaluationCriterion;
import com.ats.entities.JobAdvertisement;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.vo.Ability;
import com.ats.vo.Keyword;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ResumeAnalyser {

    private final Logger logger;
    private int points;

    public ResumeAnalyser(Logger logger) {
        this.logger = logger;
    }

    //Soll eine Liste an Bewerbern zurückgeben, die dann für die Erstellung des Leaderboards verwendet werden sollen.
    public List<Applicant> analyseResume(List<Applicant> applicantList, JobAdvertisement selectedJobAdvertisement) {

        logger.info("Analysing Resume for " + selectedJobAdvertisement);
        logger.info("Applicant list: " + applicantList);
        String sectionRegex = "(?<=\\n|^)##\\s*([A-Za-zÄÖÜäöüß\\s&]+)\\s*(?=\\n)([\\s\\S]+?)(?=\\n##|\\z)";
        Pattern pattern = Pattern.compile(sectionRegex);


        applicantList.forEach(applicant -> {
            int points = 0;
            Matcher matcher = pattern.matcher(applicant.getResume().getContant());
            List<String[]> sections = new ArrayList<>();

            while (matcher.find()) {
                String title = matcher.group(1).trim();
                String content = matcher.group(2).trim();
                sections.add(new String[] {title, content});
            }

            points += analyseAbilitiesInResume(sections.get(0)[1], getEvaluationCriterionType(selectedJobAdvertisement.getCriteria(),"ABILITIES"), applicant);
            points += analyseExperienceInResume( sections.get(1)[1], getEvaluationCriterionType(selectedJobAdvertisement.getCriteria(),"EXPERIENCE"));
            points += analyseKeywordsInResume( sections.get(2)[1], getEvaluationCriterionType(selectedJobAdvertisement.getCriteria(),"KEYWORDS"));
            applicant.setPoints(points);
        });
        return applicantList;
    }

    public List<EvaluationCriterion> getEvaluationCriterionType(List<EvaluationCriterion> evaluationCriterions, String evaluationCriterionType) {
        return switch (evaluationCriterionType.toUpperCase()) {
            case "ABILITIES" -> evaluationCriterions.stream()
                    .filter(evaluationCriterion -> evaluationCriterion instanceof EvaluationAbilities)
                    .collect(Collectors.toList());
            case "EXPERIENCE" -> evaluationCriterions.stream()
                    .filter(evaluationCriterion -> evaluationCriterion instanceof EvaluationExperience)
                    .collect(Collectors.toList());
            case "KEYWORDS" -> evaluationCriterions.stream()
                    .filter(evaluationCriterion -> evaluationCriterion instanceof EvaluationKeywords)
                    .collect(Collectors.toList());
            default -> new ArrayList<>();
        };
    }

    public static Map<String, List<String>> parseAbilitieContant(String input) {
        String[] lines = input.split("\\R");

        Map<String, List<String>> result = new LinkedHashMap<>();

        for (String line : lines) {
            if (!line.startsWith("- ")) {
                continue;
            }
            String[] parts = line.substring(2).split(":", 2);

            if (parts.length != 2) {
                continue;
            }
            String name = parts[0].trim();
            List<String> values = Arrays.stream(parts[1].split(","))
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .toList();
            result.put(name.toLowerCase(), values);
        }
        return result;
    }

    //TODO: Remove Applicant
    public int analyseAbilitiesInResume( String content, List<EvaluationCriterion> evaluationAbilities, Applicant applicant)
    {
        logger.info("Start analysing Abilities");
        logger.info("Content: " + content);
        logger.info("Evaluation Abilities: " + evaluationAbilities);
        AtomicInteger points = new AtomicInteger();
        Map<String, List<String>> result = parseAbilitieContant(content);
        System.out.println("Abilities");
        evaluationAbilities.forEach(evaluationability -> {
            AtomicInteger tempPoints = new AtomicInteger();
            if (evaluationability instanceof EvaluationAbilities) {
                List<Ability> abilitiesList = ((EvaluationAbilities) evaluationability).getListOfAbilities();
                int weighting = evaluationability.getWeighting();
                if (result.containsKey(evaluationability.getName().toLowerCase())) {
                    List<String> resultList = result.get(evaluationability.getName().toLowerCase());
                    abilitiesList.forEach(ability -> {
                        if (resultList.contains(ability.getAbility().toLowerCase())) {
                            tempPoints.addAndGet(ability.getPoints());
                        }
                    });
                }
                tempPoints.accumulateAndGet(weighting, (a, b) -> a * b);
                points.addAndGet(tempPoints.get());
            }
        });
        logger.info("Name: " + applicant.getName());
        logger.info("Points: " + points.get());
        return points.get() ;
    }

    public static int parseExperienceContant(String input) {
        int totalYears = 0;

        Pattern pattern = Pattern.compile("\\((\\d{4}) - (\\d{4})\\)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            int startYear = Integer.parseInt(matcher.group(1));
            int endYear = Integer.parseInt(matcher.group(2));
            totalYears += (endYear - startYear);
        }

        return totalYears;

    }

    //TODO: Remove Applicant
    public int analyseExperienceInResume( String content, List<EvaluationCriterion> evaluationExperience )
    {
        logger.info("Start analysing Experience");
        logger.info("Content: " + content);

        int points = 0;
        int result = parseExperienceContant(content);
        int expectedPoints = ((EvaluationExperience) evaluationExperience.get(0)).getExperienceInYears();
        int evaluationPoints = evaluationExperience.get(0).getPoints();
        int weighting = evaluationExperience.get(0).getWeighting();
        if(result == expectedPoints) {
            points += evaluationPoints * weighting;
        }
        return points;
    }

    public int analyseKeywordsInResume(String content, List<EvaluationCriterion> evaluationKeywords)
    {
        logger.info("Start analysing Keywords");
        logger.info("Content: " + content);
        logger.info("Evaluation Keywords: " + evaluationKeywords);
        AtomicInteger points = new AtomicInteger();
        evaluationKeywords.forEach(evaluationKeyword -> {
            AtomicInteger tempPoints = new AtomicInteger();
            int weighting = evaluationKeyword.getWeighting();
            if (evaluationKeyword instanceof EvaluationKeywords) {
                List<Keyword> keywords = ((EvaluationKeywords) evaluationKeyword).getListOfKeywords();
                keywords.forEach(keyword -> {
                    if (content.toLowerCase().contains(keyword.getKeyword().toLowerCase())) {
                        tempPoints.addAndGet(keyword.getPoints());
                    }

                });
            }
            tempPoints.accumulateAndGet(weighting, (a, b) -> a * b);
            points.addAndGet(tempPoints.get());
        });

        return points.get();
    }


}
