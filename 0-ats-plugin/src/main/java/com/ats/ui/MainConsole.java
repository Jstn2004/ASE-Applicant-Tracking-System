package com.ats.ui;

import com.ats.JobAdvertisementController;
import com.ats.JobAdvertisementValidationController;
import com.ats.database.JobAdvertisementRepositoryImpl;
import com.ats.database.DatabaseConfigurationImpl;
import com.ats.entities.EvaluationCriterion;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.interfaces.DatabaseConfiguration;
import com.ats.jobadvertisementService.*;
import com.ats.repositories.JobAdvertisementRepository;
import com.ats.validation.JobAdvertismentValidation;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainConsole {
    private final Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    private Logger logger;
    DatabaseConfiguration databaseConfiguration = new DatabaseConfigurationImpl();
    JobAdvertisementRepository jobAdvertisementRepository = new JobAdvertisementRepositoryImpl(databaseConfiguration.getDatabaseFilePath());
    JobAdvertisementCreater jobAdvertisementCreater = new JobAdvertisementCreater(jobAdvertisementRepository);
    JobAdvertisementLoader jobAdvertisementLoader = new JobAdvertisementLoader(jobAdvertisementRepository);
    JobAdvertisementDeleter jobAdvertisementDeleter = new JobAdvertisementDeleter(jobAdvertisementRepository);
    JobAdvertisementParser jobAdvertisementParser = new JobAdvertisementParser();
    EvaluationCriteriaCreater evaluationCriteriaCreater = new EvaluationCriteriaCreater();
    JobAdvertismentValidation jobAdvertismentValidation = new JobAdvertismentValidation();
    JobAdvertisementController jobAdvertismentController;
    JobAdvertisementValidationController jobAdvertisementValidationController;



    public MainConsole(Logger logger) {
        this.logger = logger;
        jobAdvertismentController = new JobAdvertisementController(logger, jobAdvertisementDeleter, jobAdvertisementCreater, jobAdvertisementLoader, jobAdvertisementParser, evaluationCriteriaCreater);
        jobAdvertisementValidationController = new JobAdvertisementValidationController(jobAdvertismentValidation);
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

        Iterable<EvaluationCriterion> evaluationCriterionIterable = createEvaluationCriteriaCLI();
        jobAdvertismentController.createJobAdvertisement(title, description, evaluationCriterionIterable);

        jobAdvertismentCLI();
        //kriterienCLI();
    }

    public Iterable<EvaluationCriterion> createEvaluationCriteriaCLI() {
        LinkedList<EvaluationCriterion> evaluationCriterionIterable = new LinkedList<>();
        //Erstellen von Fähigkeits Bewertungen
        EvaluationAbilities evaluationAbilities = createEvaluationAbilitiesCLI();
        evaluationCriterionIterable.add(evaluationAbilities);
        return evaluationCriterionIterable;
    }

    public EvaluationAbilities createEvaluationAbilitiesCLI() {
        System.out.println("= Bewertungskriterien festlegen =");
        System.out.println("Fähigkeiten");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        // Punkte
        String points;
        do{
            System.out.print("Punkte (Zahl von 1 bis 100): ");
            points = scanner.nextLine();
        }while (!jobAdvertisementValidationController.startPointsValidation(points));

        // Gewichtung
        String weighting;
        do {
            System.out.print("Gewichtung (Zahl von 1 bis 10): ");
            weighting = scanner.nextLine();
        }while (!jobAdvertisementValidationController.startWeightingValidation(weighting));

        // Fähigkeiten
        System.out.print("Fähigkeit (jeweils mit \",\" trennen): ");
        String abilities = scanner.nextLine();

        return jobAdvertismentController.createEvaluationAbilities(name, points, weighting, abilities);
    }

    public void allJobAdvertisment() {
        System.out.println();
        System.out.println("== Alle Ausschreibungen == ");
        jobAdvertismentController.loadAllJobAdvertisement().forEach(tender ->
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
        jobAdvertismentController.deleteJobAdvertisementById(idToDelete);
        System.out.println("Ausschreibung wurde gelöscht");
        allJobAdvertisment();


    }

    public void criteriaCLI() {
        System.out.println("= Kriterien =");
    }

}
