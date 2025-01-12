package com.ats;


import com.ats.entities.JobAdvertisement;
import com.ats.interfaces.TenderRepository;

import java.util.List;

public class TenderController {

    private ManagerTendersApp createTenderApp;
    private final TenderRepository tenderRepository;

    public TenderController(TenderRepository tenderRepository) {
        this.tenderRepository = tenderRepository;
        this.createTenderApp = new ManagerTendersApp(tenderRepository);
    }

    public void createTender(String title, String description)
    {
        createTenderApp.createNewTender(title, description);
    }

    public List<JobAdvertisement> loadAllTenders()
    {
        return createTenderApp.loadAllTenders();
    }

    public void deleteTenderById(String id)
    {
        createTenderApp.deleteTender(id);
    }


}