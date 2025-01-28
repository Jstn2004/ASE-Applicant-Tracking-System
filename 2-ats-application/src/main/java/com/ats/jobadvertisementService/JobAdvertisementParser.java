package com.ats.jobadvertisementService;

import com.ats.entities.EvaluationCriterion;
import com.ats.entities.JobAdvertisement;

public class JobAdvertisementParser {

    public JobAdvertisement parseTenderString(String tenderString) {
        String regex = "JobAdvertisement\\{id=(.*?), titel='(.*?)', description='(.*?)'}";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(tenderString);

        if (matcher.find()) {
            String id = matcher.group(1);
            String title = matcher.group(2);
            String description = matcher.group(3);
            Iterable<EvaluationCriterion> criteria = null;
            return new JobAdvertisement(id, title, description,criteria);
        }
        return null;
    }
}
