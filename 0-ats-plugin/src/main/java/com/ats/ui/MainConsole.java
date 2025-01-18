package com.ats.ui;

import com.ats.JobAdvertisementController;
import com.ats.database.JobAdvertisementRepositoryImpl;
import com.ats.database.DatabaseConfigurationImpl;
import com.ats.interfaces.DatabaseConfiguration;
import com.ats.jobadvertisementService.JobAdvertisementCreater;
import com.ats.jobadvertisementService.JobAdvertisementDeleter;
import com.ats.jobadvertisementService.JobAdvertisementLoader;
import com.ats.repositories.JobAdvertisementRepository;

import java.util.Scanner;

public class MainConsole {
    private final Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    DatabaseConfiguration databaseConfiguration = new DatabaseConfigurationImpl();;
    JobAdvertisementRepository jobAdvertisementRepository = new JobAdvertisementRepositoryImpl(databaseConfiguration.getDatabaseFilePath());
    JobAdvertisementCreater jobAdvertisementCreater = new JobAdvertisementCreater(jobAdvertisementRepository);
    JobAdvertisementLoader jobAdvertisementLoader = new JobAdvertisementLoader(jobAdvertisementRepository);
    JobAdvertisementDeleter jobAdvertisementDeleter = new JobAdvertisementDeleter(jobAdvertisementRepository);
    JobAdvertisementController jobAdvertismentController = new JobAdvertisementController(jobAdvertisementDeleter, jobAdvertisementCreater, jobAdvertisementLoader);

    public MainConsole() {
    }

    public void startCLI() {

        System.out.println();
        System.out.println("== Start ==");
        System.out.println("Wählen Sie eine Option:");
        System.out.println("1: Lebensläufe");
        System.out.println("2: Ausschreibungen");
        System.out.println("3: Beenden");
        System.out.print("Eingabe:");


        while (running) {
            String input = scanner.next();
            switch (input) {
                case "1":
                    System.out.println("Lebensläufe");
                    break;
                case "2":
                    System.out.println();
                    jobAdvertismentCLI();
                    break;
                case "3":
                    System.out.println("Beenden");
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Eingabe, bitte versuchen Sie es erneut.");
                    startCLI();
            }
        }
        scanner.close();
    }

    public void jobAdvertismentCLI() {
        System.out.println();
        System.out.println("== Ausschreibungen == ");
        System.out.println("Wählen Sie eine Option:");
        System.out.println("1: Neue Ausschreibung");
        System.out.println("2: Alle Ausschreibung");
        System.out.println("3: Zurück");
        System.out.println("4: Beenden");
        System.out.print("Eingabe:");
        while (running) {
            String input = scanner.next();
            switch (input) {
                case "1":
                    newJobAdvertismentCLI();
                    break;
                case "2":
                    allJobAdvertisment();
                    break;
                case "3":
                    startCLI();
                    break;
                case "4":
                    System.out.println("Beenden");
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Eingabe, bitte versuchen Sie es erneut.");
                    jobAdvertismentCLI();
            }
        }
        scanner.close();

    }

    public void newJobAdvertismentCLI() {
        System.out.println();
        System.out.println("== Neue Ausschreibung == ");

        System.out.print("Titel: ");
        String title = scanner.nextLine();

        title = scanner.nextLine();

        System.out.print("Beschreibung: ");
        String description = scanner.nextLine();

        jobAdvertismentController.createTender(title, description);

        jobAdvertismentCLI();
        //kriterienCLI();
    }

    public void allJobAdvertisment() {
        System.out.println();
        System.out.println("== Alle Ausschreibungen == ");
        jobAdvertismentController.loadAllTenders().forEach(tender ->
                {
                    System.out.println("ID:");
                    System.out.println(" → " + tender.getId());
                    System.out.println("Title:");
                    System.out.println(" → " + tender.getTitel());
                    System.out.println("Beschreibung:");
                    System.out.println(" → " + tender.getDescription());
                    for (int i = 0; i < 100; i++) {
                        System.out.print("-");
                    }
                    System.out.println();
                }
        );

        System.out.println("Wählen Sie eine Option:");
        System.out.println("1: Ausschreibung Löschen");
        System.out.println("2: Zurück");
        System.out.print("Eingabe:");
        while (running) {
            String input = scanner.next();
            switch (input) {
                case "1":
                    deleteActiveJobAdvertisment();
                    break;
                case "2":
                    jobAdvertismentCLI();
                    break;
                default:
                    System.out.println("Ungültige Eingabe, bitte versuchen Sie es erneut.");
                    allJobAdvertisment();
            }
        }

    }

    public void deleteActiveJobAdvertisment() {
        System.out.println();
        System.out.println("== Ausschreibung löschen == ");
        System.out.println("Zum Löschen die ID der Ausschreibung eingeben");
        String idToDelete = scanner.next();
        jobAdvertismentController.deleteTenderById(idToDelete);
        System.out.println("Ausschreibung wurde gelöscht");
        allJobAdvertisment();


    }

    public void criteriaCLI() {
        System.out.println("= Kriterien =");
    }

}
