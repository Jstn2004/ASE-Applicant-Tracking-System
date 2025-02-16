package com.ats.services;

import com.ats.entities.Applicant;
import com.ats.entities.EvaluationCriterion;
import com.ats.entities.JobAdvertisement;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.vo.Resume;

import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ResumeAnalyser {

    private final Logger logger;

    public ResumeAnalyser(Logger logger) {
        this.logger = logger;
    }

    public void analyseResume(List<Applicant> applicantList, JobAdvertisement selectedJobAdvertisement) {

        logger.info("Analysing Resume for " + selectedJobAdvertisement);
        logger.info("Applicant list: " + applicantList);
        String sectionRegex = "(?<=\\n|^)##\\s*([A-Za-zÄÖÜäöüß\\s&]+)\\s*(?=\\n)([\\s\\S]+?)(?=\\n##|\\z)";
        Pattern pattern = Pattern.compile(sectionRegex);


        applicantList.forEach(applicant -> {
            Matcher matcher = pattern.matcher(applicant.getResume().getContant());
            List<String[]> sections = new ArrayList<>();

            while (matcher.find()) {
                String title = matcher.group(1).trim();
                String content = matcher.group(2).trim();
                sections.add(new String[] {title, content});
            }

            analyseAbilitiesInResume(sections.get(0)[0], sections.get(0)[1], getEvaluationCriterionType(selectedJobAdvertisement.getCriteria(),"ABILITIES"));
            //analyseExperienceInResume(sections.get(1)[0], sections.get(1)[1]);
            //analyseKeywordsInResume(sections.get(2)[0], sections.get(2)[1]);

        });
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

    public void analyseAbilitiesInResume(String title, String content, List<EvaluationCriterion> evaluationAbilities)
    {
        logger.info("Start analysing Abilities");
        logger.info("Title: " + title);
        logger.info("Content: " + content);
        logger.info("Evaluation Abilities: " + evaluationAbilities);
        Map<String, List<String>> result = parseAbilitieContant(content);
        result.forEach((key, values) ->
                System.out.println("Name: " + key + " | Werte: " + values)
        );




    }

    public static Map<String, List<String>> parseAbilitieContant(String input) {
        List<String> lines = Arrays.asList(input.split("\\R"));

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
                    .toList();
            result.put(name, values);
        }
        return result;
    }

    public void analyseExperienceInResume(String title, String content)
    {
        System.out.println("Title: " + title);
        System.out.println("Content: " + content);
    }

    public void analyseKeywordsInResume(String title, String content)
    {
        System.out.println("Title: " + title);
        System.out.println("Content: " + content);
    }
}
