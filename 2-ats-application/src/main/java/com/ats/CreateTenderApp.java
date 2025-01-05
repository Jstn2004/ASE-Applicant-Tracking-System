package com.ats;

import com.ats.entities.Tender;
import com.ats.interfaces.TenderRepository;

/**
 * Use Case:
 * Soll es ermöglichen eine Ausschreibung zu erzeugen anhand der Benutzer eingaben
 */
public class CreateTenderApp {
    private Tender tender;
    private TenderRepository tenderRepository;

    public CreateTenderApp(TenderRepository tenderRepository) {
        this.tenderRepository = tenderRepository;
    }

    public void createNewTender(String title, String description) {
        System.out.println("Bin in der App");
        this.tender = new Tender(title, description);
        tenderRepository.saveTender(this.tender);
    }


}
