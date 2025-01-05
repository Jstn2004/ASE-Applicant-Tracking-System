package com.ats;


import com.ats.entities.Tender;
import com.ats.interfaces.TenderRepository;

public class TenderAdapter {

    private String title;
    private String description;
    private CreateTenderApp createTenderApp;

    public TenderAdapter(String titel, String beschreibung, TenderRepository tenderRepository) {
        this.title = titel;
        this.description = beschreibung;
        this.createTenderApp = new CreateTenderApp(tenderRepository);
    }

    public void createTender()
    {
        System.out.println("Bin im Adapter");
        createTenderApp.createNewTender(title, description);
    }
}