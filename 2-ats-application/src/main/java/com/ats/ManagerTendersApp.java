package com.ats;

import com.ats.entities.Tender;
import com.ats.interfaces.TenderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Use Case:
 * Soll es ermöglichen eine Ausschreibung zu erzeugen anhand der Benutzer eingaben
 */
public class ManagerTendersApp {
    private Tender tender;
    
    private TenderRepository tenderRepository;

    public ManagerTendersApp(TenderRepository tenderRepository) {
        this.tenderRepository = tenderRepository;
    }

    public void createNewTender(String title, String description) {
        String id = UUID.randomUUID().toString();
        this.tender = new Tender(id, title, description);
        tenderRepository.saveTender(this.tender);
    }

    public List<Tender> loadAllTenders()
    {
        List<Tender> tendersList = new ArrayList<>();
        List<String> tendersStringList = tenderRepository.loadTender();
        tendersStringList.forEach(tender -> tendersList.add(parseTenderString(tender)));
        return tendersList;
    }

    public void deleteTender(String id) {
        tenderRepository.deleteTenderById(id);
    }

    public static Tender parseTenderString(String tenderString) {
        String regex = "Tender\\{id=(.*?), titel='(.*?)', description='(.*?)'}";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(tenderString);

        if (matcher.find()) {
            String id = matcher.group(1);
            String title = matcher.group(2);
            String description = matcher.group(3);
            return new Tender(id, title, description);
        }
        return null;
    }


    
}
