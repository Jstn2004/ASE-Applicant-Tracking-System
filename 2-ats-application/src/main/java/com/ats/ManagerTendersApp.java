package com.ats;

import com.ats.entities.Tender;
import com.ats.interfaces.TenderRepository;

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


    
}
