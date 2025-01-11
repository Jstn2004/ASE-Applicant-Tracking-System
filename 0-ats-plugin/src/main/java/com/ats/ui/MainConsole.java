package com.ats.ui;

import com.ats.TenderController;
import com.ats.TenderRepositoryImpl;
import com.ats.datenbank.DatabaseConfigurationImpl;
import com.ats.interfaces.DatabaseConfiguration;
import com.ats.interfaces.TenderRepository;

import java.util.Scanner;

public class MainConsole {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;
    DatabaseConfiguration databaseConfiguration = new DatabaseConfigurationImpl();
    TenderRepository tenderRepository = new TenderRepositoryImpl(databaseConfiguration.getDatabaseFilePath());
    TenderController tenderAdapter = new TenderController(tenderRepository);


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
                    tenderCLI();
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

    public void tenderCLI() {
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
                    newTenderCLI();
                    break;
                case "2":
                    allTenders();
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
                    tenderCLI();
            }
        }
        scanner.close();

    }

    public void newTenderCLI() {
        System.out.println();
        System.out.println("== Neue Ausschreibung == ");

        System.out.print("Titel: ");
        String title = scanner.nextLine();
        title = scanner.nextLine();

        System.out.print("Beschreibung: ");
        String description = scanner.nextLine();

        tenderAdapter.createTender(title, description);

        tenderCLI();
        //kriterienCLI();
    }

    public void allTenders() {
        System.out.println();
        System.out.println("== Alle Ausschreibungen == ");
        tenderAdapter.loadAllTenders().forEach(tender ->
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
                    deleteActiveTender();
                    break;
                case "2":
                    tenderCLI();
                    break;
                default:
                    System.out.println("Ungültige Eingabe, bitte versuchen Sie es erneut.");
                    allTenders();
            }
        }

    }

    public void deleteActiveTender() {
        System.out.println();
        System.out.println("== Ausschreibung löschen == ");
        System.out.println("Zum Löschen die ID der Ausschreibung eingeben");
        String idToDelete = scanner.next();
        tenderAdapter.deleteTenderById(idToDelete);
        System.out.println("Ausschreibung wurde gelöscht");


    }

    public void criteriaCLI() {
        System.out.println("= Kriterien =");
    }

}
