package com.ats;

import com.ats.entities.JobAdvertisement;
import com.ats.resumeService.ResumeService;

import java.util.logging.Logger;

public class ResumeController {

    private final Logger logger;
    private final ResumeService resumeService;
    public int numberOfResumes = 0;

    public ResumeController(Logger logger, ResumeService resumeLoader) {
        this.logger = logger;
        this.resumeService = resumeLoader;
    }

    public void loadAllResumes() {
           resumeService.loadAllResumes();
           numberOfResumes = resumeService.loadAllResumes().size();
    }

    public String selectedResume(String id)
    {
        return resumeService.setSelectedJobAdvertisement(id);
    }

    public JobAdvertisement getActiveJobAdvertisement()
    {
        return resumeService.selectedJobAdvertisement;
    }
}
