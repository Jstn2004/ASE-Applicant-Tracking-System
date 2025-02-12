package com.ats.ui;

import com.ats.JobAdvertisementController;
import com.ats.JobAdvertisementValidationController;
import com.ats.ResumeController;
import com.ats.entities.EvaluationCriterion;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.interfaces.DatabaseConfiguration;
import com.ats.jobadvertisementService.*;
import com.ats.repositories.JobAdvertisementRepository;
import com.ats.validation.JobAdvertismentValidation;
import com.ats.vo.Ability;
import com.ats.vo.Keyword;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainConsole {
    private final Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    private String stop;
    private final Logger logger;

    private final ResumeUI resumeUI;
    private final JobAdvertisementUI jobAdvertisementUI;

    public MainConsole(Logger logger, ResumeUI resumeUI, JobAdvertisementUI jobAdvertisementUI) {
        this.logger = logger;
        this.resumeUI = resumeUI;
        this.jobAdvertisementUI = jobAdvertisementUI;
    }

    public void startCLI() {

        System.out.println();
        String header = "Willkommen bei ATS";
        System.out.println(line(header));
        System.out.println(header(header));
        System.out.println(line(header));

        System.out.println("Wählen Sie eine Option:");
        System.out.println("1: Lebensläufe");
        System.out.println("2: Jobausschreibungen");
        System.out.println("3: Beenden");
        System.out.print("Eingabe:");


        while (running) {
            String input = scanner.next();
            switch (input) {
                case "1":
                    resumeUI.resumeCLI();
                    break;
                case "2":
                    System.out.println();
                    jobAdvertisementUI.jobAdvertismentCLI();
                    break;
                case "3":
                    System.out.println("Beenden");
                    running = false;
                    break;
                default:
                    System.out.println("\u001B[31mUngültige Eingabe, bitte versuchen Sie es erneut.\u001B[0m");
                    startCLI();
            }
        }
        scanner.close();
    }

    public String header(String headertext) {
        int totalLength = 50;
        int padding = (totalLength - headertext.length() - 4) / 2;

        String header = "=".repeat(padding) + "== " + headertext + " ==";
        header += "=".repeat(padding);

        if (header.length() < totalLength) {
            header += "=";
        }
        return header;
    }

    public String line(String headertext)
    {
        String line = "-".repeat(header(headertext).length());
        return line;
    }
}
