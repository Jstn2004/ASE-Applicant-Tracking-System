package com.ats.ui;

import com.ats.TenderAdapter;
import com.ats.datenbank.TenderRepositoryImpl;
import com.ats.interfaces.TenderRepository;

import java.util.Scanner;

public class MainConsole {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;


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
                    System.out.println("Alle Ausschreibungen");
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
            }
        }
        scanner.close();

    }

    public void newTenderCLI() {
        System.out.println();
        System.out.println("== Neue Ausschreibung == ");

        System.out.print("Titel:");
        String title = scanner.next();

        System.out.print("Beschreibung:");
        String description = scanner.next();

        TenderRepository tenderRepository = new TenderRepositoryImpl();
        TenderAdapter tenderAdapter = new TenderAdapter(title, description, tenderRepository);
        tenderAdapter.createTender();


        //kriterienCLI();
    }

    public void criteriaCLI() {
        System.out.println("= Kriterien =");


    }

}
