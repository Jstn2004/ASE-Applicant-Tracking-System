package com.ats.ui;

import com.ats.ResumeController;

import java.util.Scanner;

public class ResumeUI {
    private final Scanner scanner = new Scanner(System.in);
    private boolean running = true;

    private MainConsole mainConsole;
    private final ResumeController resumeController;
    private JobAdvertisementUI jobAdvertisementUI;

    public ResumeUI(MainConsole mainConsole, ResumeController resumeController, JobAdvertisementUI jobAdvertisementUI)
    {
        this.mainConsole = mainConsole;
        this.resumeController = resumeController;
        this.jobAdvertisementUI = jobAdvertisementUI;
        resumeController.loadAllResumes();
    }

    public void resumeCLI() {
        System.out.println();

        String header = "Lebensläufe verwalten";
        System.out.println(mainConsole.line(header));
        System.out.println(mainConsole.header(header));
        System.out.println(mainConsole.line(header));

        System.out.printf("Vorhandene Lebensläufe:   \u001B[93m%d\u001B[0m%n", resumeController.numberOfResumes);

        if(resumeController.getActiveJobAdvertisement() != null) {
            System.out.printf("Ausgewähltes Jobangebot: \u001B[93m%s%n\u001B[0m%n", resumeController.getActiveJobAdvertisement().getTitel());
        }

        System.out.println("Wählen Sie eine Option:");
        System.out.println("1: Jobausschreibung auswählen");
        System.out.println("2: Lebenslaufanalyse starten");
        System.out.println("3: Zurück");
        System.out.println("4: Beenden");
        System.out.print("Eingabe:");
        while (running) {
            String input = scanner.next();
            switch (input) {
                case "1":
                    jobAdvertisementUI.allJobAdvertismentCLI(true);
                    break;
                case "2":
                    boolean result = resumeController.startResumeAnalyse();
                    if(result) {
                        analyseinformation();
                        resumeCLI();
                    }
                    break;
                case "3":
                    mainConsole.startCLI();
                    break;
                case "4":
                    System.out.println("Beenden");
                    running = false;
                    break;
                default:
                    System.out.println("\u001B[31mUngültige Eingabe, bitte versuchen Sie es erneut.\u001B[0m");

            }
        }
        scanner.close();

    }

    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    public static void analyseinformation() {
        final String successmessage = "Lebensläufe wurden analysiert";
        final String loadingmessage = "Lebensläufe werden analysiert";

        System.out.println(YELLOW + loadingmessage + RESET);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Wartevorgang unterbrochen");
        }

        System.out.print(GREEN + successmessage + RESET);

    }
    public void setMainConsole(MainConsole mainConsole) {
        this.mainConsole = mainConsole;
    }

    public void setJobAdvertisementUI(JobAdvertisementUI jobAdvertisementUI) {
        this.jobAdvertisementUI = jobAdvertisementUI;
    }
}
