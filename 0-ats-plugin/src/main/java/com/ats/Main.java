package com.ats;

import com.ats.database.DatabaseConfigurationImpl;
import com.ats.database.JobAdvertisementRepositoryImpl;
import com.ats.interfaces.DatabaseConfiguration;
import com.ats.interfaces.FileManager;
import com.ats.interfaces.FileManagerConfiguration;
import com.ats.jobadvertisementService.*;
import com.ats.repositories.JobAdvertisementRepository;
import com.ats.resumeService.ResumeLoader;
import com.ats.resumes.FileManagerConfigurationImpl;
import com.ats.resumes.FileManagerImpl;
import com.ats.ui.JobAdvertisementUI;
import com.ats.ui.MainConsole;
import com.ats.ui.ResumeUI;
import com.ats.validation.JobAdvertismentValidation;

import java.io.IOException;
import java.util.logging.*;

public class Main {
    static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        for (Handler handler : handlers) {
            if (handler instanceof ConsoleHandler) {
                rootLogger.removeHandler(handler);
            }
        }
        try {
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            System.err.println("Fehler beim Erstellen des FileHandler: " + e.getMessage());
        }


        DatabaseConfiguration databaseConfiguration = new DatabaseConfigurationImpl();
        JobAdvertisementRepository jobAdvertisementRepository = new JobAdvertisementRepositoryImpl(databaseConfiguration.getDatabaseFilePath());
        JobAdvertisementCreater jobAdvertisementCreater = new JobAdvertisementCreater(jobAdvertisementRepository, logger);
        JobAdvertisementLoader jobAdvertisementLoader = new JobAdvertisementLoader(jobAdvertisementRepository, logger);
        JobAdvertisementDeleter jobAdvertisementDeleter = new JobAdvertisementDeleter(jobAdvertisementRepository, logger);
        JobAdvertisementParser jobAdvertisementParser = new JobAdvertisementParser(logger);
        EvaluationCriteriaCreater evaluationCriteriaCreater = new EvaluationCriteriaCreater(logger);
        JobAdvertismentValidation jobAdvertismentValidation = new JobAdvertismentValidation(logger);
        JobAdvertisementController jobAdvertisementController = new JobAdvertisementController(logger, jobAdvertisementDeleter, jobAdvertisementCreater, jobAdvertisementLoader, jobAdvertisementParser, evaluationCriteriaCreater);
        JobAdvertisementValidationController jobAdvertisementValidationController = new JobAdvertisementValidationController(jobAdvertismentValidation, logger);


        FileManagerConfiguration fileManagerConfiguration = new FileManagerConfigurationImpl();
        FileManager fileManager = new FileManagerImpl(fileManagerConfiguration.getInputFolderPath());
        ResumeLoader resumeLoader = new ResumeLoader(logger, fileManager);
        ResumeController resumeController = new ResumeController(logger, resumeLoader);

        ResumeUI resumeUI = new ResumeUI(null,resumeController, null);
        JobAdvertisementUI jobAdvertisementUI = new JobAdvertisementUI(jobAdvertisementValidationController, jobAdvertisementController, null,null);

        MainConsole appKonsole = new MainConsole(logger,resumeUI, jobAdvertisementUI);

        jobAdvertisementUI.setMainConsole(appKonsole);
        jobAdvertisementUI.setResumeUI(resumeUI);
        resumeUI.setMainConsole(appKonsole);
        resumeUI.setJobAdvertisementUI(jobAdvertisementUI);

        appKonsole.startCLI();
    }
}
