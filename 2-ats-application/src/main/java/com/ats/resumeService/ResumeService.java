package com.ats.resumeService;

import com.ats.entities.JobAdvertisement;
import com.ats.interfaces.FileManager;
import com.ats.jobadvertisementService.JobAdvertisementParser;
import com.ats.repositories.JobAdvertisementRepository;

import java.util.List;
import java.util.logging.Logger;

public class ResumeService {
    private final Logger logger;
    private final FileManager fileManager;
    private final JobAdvertisementRepository jobAdvertisementRepository;
    private final JobAdvertisementParser jobAdvertisementParser;

    public List<String> loadedResumes;
    public JobAdvertisement selectedJobAdvertisement = null;

    public ResumeService(Logger logger, FileManager fileManager, JobAdvertisementRepository jobAdvertisementRepository, JobAdvertisementParser jobAdvertisementParser) {
        this.logger = logger;
        this.fileManager = fileManager;
        this.jobAdvertisementRepository = jobAdvertisementRepository;
        this.jobAdvertisementParser = jobAdvertisementParser;
    }

    public List<String> loadAllResumes()
    {
        logger.info("Loading all resumes");
        loadedResumes = fileManager.loadResumeFiles();
        return loadedResumes;
    }

    public String setSelectedJobAdvertisement(String id)
    {
        logger.info("Selecting resume");
        String selectedResumes = jobAdvertisementRepository.getJobAdvertisementById(id);
        logger.info("Selected resume: " + selectedResumes);
        selectedJobAdvertisement = jobAdvertisementParser.parseJobAdvertisementString(selectedResumes);
        return selectedJobAdvertisement.getTitel();


    }

}
