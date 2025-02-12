package com.ats.resumeService;

import com.ats.interfaces.FileManager;

import java.util.List;
import java.util.logging.Logger;

public class ResumeLoader {
    private final Logger logger;
    private final FileManager fileManager;

    public ResumeLoader(Logger logger, FileManager fileManager) {
        this.logger = logger;
        this.fileManager = fileManager;
    }

    public List<String> loadAllResumes()
    {
        System.out.println(fileManager.loadResumeFiles());
        return fileManager.loadResumeFiles();
    }
}
