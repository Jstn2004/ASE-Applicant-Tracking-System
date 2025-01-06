package com.ats;


import com.ats.entities.Tender;
import com.ats.interfaces.TenderRepository;

import java.util.ArrayList;
import java.util.List;

public class TenderAdapter {

    private String title;
    private String description;
    private ManagerTendersApp createTenderApp;
    private TenderRepository tenderRepository;

    public TenderAdapter(String titel, String beschreibung, TenderRepository tenderRepository) {
        this.title = titel;
        this.description = beschreibung;
        this.tenderRepository = tenderRepository;
        this.createTenderApp = new ManagerTendersApp(tenderRepository);
    }

    public TenderAdapter(TenderRepository tenderRepository) {
        this.tenderRepository = tenderRepository;
    }

    public void createTender()
    {
        createTenderApp.createNewTender(title, description);
    }

    public List<Tender> loadAllTenders()
    {
        List<Tender> tendersList = new ArrayList<>();
        List<String> tendersStringList = tenderRepository.loadTender();
        tendersStringList.forEach(tender ->
                {
                    tendersList.add(parseTenderString(tender));
                }

        );
        return tendersList;
    };

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
        //throw new IllegalArgumentException("Ungültiges Tender-String-Format");
    }
}