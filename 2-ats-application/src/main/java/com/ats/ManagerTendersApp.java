package com.ats;

import com.ats.entities.JobAdvertisement;
import com.ats.interfaces.TenderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Use Case:
 * Soll es ermöglichen eine Ausschreibung zu erzeugen anhand der Benutzer eingaben
 */
public class ManagerTendersApp {

    private JobAdvertisement jobAdvertisement;
    private TenderRepository tenderRepository;

    public ManagerTendersApp(TenderRepository tenderRepository) {
        this.tenderRepository = tenderRepository;
    }

    public void createNewTender(String title, String description) {
        String id = UUID.randomUUID().toString();
        this.jobAdvertisement = new JobAdvertisement(id, title, description);
        tenderRepository.saveTender(this.jobAdvertisement);
    }

    public List<JobAdvertisement> loadAllTenders()
    {
        List<JobAdvertisement> tendersList = new ArrayList<>();
        List<String> tendersStringList = tenderRepository.loadTender();
        tendersStringList.forEach(tender -> tendersList.add(parseTenderString(tender)));
        return tendersList;
    }

    public void deleteTender(String id) {
        tenderRepository.deleteTenderById(id);
    }

    public static JobAdvertisement parseTenderString(String tenderString) {
        String regex = "Tender\\{id=(.*?), titel='(.*?)', description='(.*?)'}";
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
