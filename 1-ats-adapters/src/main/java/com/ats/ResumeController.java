package com.ats;

import com.ats.resumeService.ResumeLoader;

import java.util.List;
import java.util.logging.Logger;

public class ResumeController {

    private final Logger logger;
    private final ResumeLoader resumeLoader;
    public int numberOfResumes = 0;

    public ResumeController(Logger logger, ResumeLoader resumeLoader) {
        this.logger = logger;
        this.resumeLoader = resumeLoader;
    }

    public void loadAllResumes() {
           resumeLoader.loadAllResumes();
           numberOfResumes = resumeLoader.loadAllResumes().size();
    }
}
