package com.ats.resumeService;

import com.ats.entities.Applicant;
import com.ats.entities.JobAdvertisement;
import com.ats.entities.Leaderboard;
import com.ats.interfaces.FileManager;
import com.ats.interfaces.ResumeService;
import com.ats.jobadvertisementService.JobAdvertisementParser;
import com.ats.repositories.JobAdvertisementRepository;
import com.ats.services.ApplicantCreater;
import com.ats.services.ResumeAnalyser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ResumeServiceImpl implements ResumeService {
    private final Logger logger;
    private final FileManager fileManager;
    private final JobAdvertisementRepository jobAdvertisementRepository;
    private final JobAdvertisementParser jobAdvertisementParser;
    private final ApplicantCreater applicantCreater;
    private final ResumeAnalyser resumeAnalyser;

    public List<String> loadedResumes;
    private List<Applicant> analysedApplicants = new ArrayList<>();
    public JobAdvertisement selectedJobAdvertisement = null;

    public ResumeServiceImpl(Logger logger, FileManager fileManager, JobAdvertisementRepository jobAdvertisementRepository, JobAdvertisementParser jobAdvertisementParser, ApplicantCreater applicantCreater, ResumeAnalyser resumeAnalyser) {
        this.logger = logger;
        this.fileManager = fileManager;
        this.jobAdvertisementRepository = jobAdvertisementRepository;
        this.jobAdvertisementParser = jobAdvertisementParser;
        this.applicantCreater = applicantCreater;
        this.resumeAnalyser = resumeAnalyser;
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

    public boolean startResumeAnalysing()
    {
        if(selectedJobAdvertisement != null)
        {
            List<Applicant> analyseResult = new ArrayList<>();
            logger.info("Starting resume analysing");
            List<Applicant> listOfApplicants = applicantCreater.getApplicantFromResume(loadedResumes);
            this.analysedApplicants = resumeAnalyser.analyseResume(listOfApplicants, selectedJobAdvertisement);
            return true;
        }else
        {
            System.out.println("\033[0;31mBitte wähle zuerst ein Jobangebot aus\033[0m");
            return false;
        }
    };

    public List<Applicant> getAnalysedApplicants() {
        return analysedApplicants;
    }

    public Leaderboard getLeaderboard() {
        return new Leaderboard(
                "Rangliste für " + selectedJobAdvertisement.getTitel(),
                analysedApplicants,
                selectedJobAdvertisement
        );
    }





}
