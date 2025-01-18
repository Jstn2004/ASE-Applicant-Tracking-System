package com.ats.jobadvertisementService;

import com.ats.entities.JobAdvertisement;
import com.ats.repositories.JobAdvertisementRepository;

import java.util.ArrayList;
import java.util.List;

public class JobAdvertisementLoader {

    private JobAdvertisementRepository jobAdvertisementRepository;

    public JobAdvertisementLoader(JobAdvertisementRepository jobAdvertisementRepository) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
    }

    public List<JobAdvertisement> loadAllTenders()
    {
        List<JobAdvertisement> jobAdvertisementsList = new ArrayList<>();
        List<String> tendersStringList = jobAdvertisementRepository.loadTender();
        System.out.println(tendersStringList.size());
        tendersStringList.forEach(tender -> jobAdvertisementsList.add(parseTenderString(tender)));
        return jobAdvertisementsList;
    }

    public static JobAdvertisement parseTenderString(String tenderString) {
        String regex = "JobAdvertisement\\{id=(.*?), titel='(.*?)', description='(.*?)'}";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(tenderString);

        if (matcher.find()) {
            String id = matcher.group(1);
            String title = matcher.group(2);
            String description = matcher.group(3);
            return new JobAdvertisement(id, title, description);
        }
        return null;
    }
}
