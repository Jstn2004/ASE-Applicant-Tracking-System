package com.ats;

import com.ats.entities.Applicant;
import com.ats.entities.JobAdvertisement;
import com.ats.entities.Leaderboard;
import com.ats.interfaces.FileManager;
import com.ats.resumeService.ResumeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ResumeController {

    private final Logger logger;
    private final FileManager fileManager;
    private final ResumeServiceImpl resumeService;
    public int numberOfResumes = 0;

    public ResumeController(Logger logger, FileManager fileManager, ResumeServiceImpl resumeLoader) {
        this.logger = logger;
        this.fileManager = fileManager;
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

    public boolean startResumeAnalyse()
    {
        boolean result = resumeService.startResumeAnalysing();
        if (result) {
            fileManager.saveLeaderboardFile(getActiveJobAdvertisement().getTitel(), transformLeaderboard(resumeService.getLeaderboard().formatLeaderboard()));
        }
        return result;
    }

    public List<String> transformLeaderboard(List<String> leaderboard) {
        return leaderboard;
    }
}
