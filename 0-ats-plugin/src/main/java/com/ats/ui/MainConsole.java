package com.ats.ui;

import com.ats.JobAdvertisementController;
import com.ats.JobAdvertisementValidationController;
import com.ats.database.JobAdvertisementRepositoryImpl;
import com.ats.database.DatabaseConfigurationImpl;
import com.ats.entities.EvaluationCriterion;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.interfaces.DatabaseConfiguration;
import com.ats.jobadvertisementService.*;
import com.ats.repositories.JobAdvertisementRepository;
import com.ats.validation.JobAdvertismentValidation;

import java.util.LinkedList;
import java.util.List;
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
        String header = "Start";
        System.out.println(line(header));
        System.out.println(header(header));
        System.out.println(line(header));

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

        String header = "Ausschreibungen ";
        System.out.println(line(header));
        System.out.println(header(header));
        System.out.println(line(header));

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

        String header = "Neue Ausschreibung";
        System.out.println(line(header));
        System.out.println(header(header));
        System.out.println(line(header));

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
        String header = "Bewertungskriterien festlegen ";
        System.out.println(line(header));
        System.out.println(header(header));
        System.out.println(line(header));

        EvaluationAbilities evaluationAbilities = createEvaluationAbilitiesCLI();
        evaluationCriterionIterable.add(evaluationAbilities);

        EvaluationExperience evaluationExperience = createEvaluationExperienceCLI();
        evaluationCriterionIterable.add(evaluationExperience);
        return evaluationCriterionIterable;
    }

    public EvaluationAbilities createEvaluationAbilitiesCLI() {
        System.out.println("Fähigkeiten");
        List<String> evaluationedCriterionArguments= evaluationCriterionCLI();
        System.out.print("Fähigkeit (jeweils mit \",\" trennen): ");
        String abilities = scanner.nextLine();

        return jobAdvertismentController.createEvaluationAbilities(evaluationedCriterionArguments, abilities);
    }

    public EvaluationExperience createEvaluationExperienceCLI() {
        System.out.println("Erfahrungen");
        List<String> evaluationedCriterionArguments= evaluationCriterionCLI();
        String experience;
        do{
            System.out.print("Erfahrungen (In Jahren angeben): ");
            experience= scanner.nextLine();
        }while (!jobAdvertisementValidationController.startExperienceValidation(experience));

        return jobAdvertismentController.createEvaluationExperience(evaluationedCriterionArguments, experience);
    }

    public List<String> evaluationCriterionCLI(){
        List<String> evaluationCriterionArguments= new LinkedList<>();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        // Punkte
        String points;
        evaluationCriterionArguments.add(name);
        do{
            System.out.print("Punkte (Zahl von 1 bis 100): ");
            points = scanner.nextLine();
        }while (!jobAdvertisementValidationController.startPointsValidation(points));
        evaluationCriterionArguments.add(points);
        // Gewichtung
        String weighting;
        do {
            System.out.print("Gewichtung (Zahl von 1 bis 10): ");
            weighting = scanner.nextLine();
        }while (!jobAdvertisementValidationController.startWeightingValidation(weighting));
        evaluationCriterionArguments.add(weighting);
        return evaluationCriterionArguments;
    }

    public void allJobAdvertisment() {
        System.out.println();
        String header = "Alle Ausschreibungen";
        System.out.println(line(header));
        System.out.println(header(header));
        System.out.println(line(header));
        jobAdvertismentController.loadAllJobAdvertisement().forEach(item ->
                {
                    System.out.printf("ID:            %s%n", item.getId());
                    System.out.printf("Title:         %s%n", item.getTitel());
                    System.out.printf("Beschreibung:  %s%n", item.getDescription());
                    System.out.println("Bewertungskriterien:");
                    item.getCriteria().forEach(criteria ->
                    {
                        System.out.println("  " + criteria.getClass().getSimpleName());
                        System.out.printf("     Name:           %s%n", criteria.getName());
                        System.out.printf("     Punkte:         %d%n", criteria.getPoints());
                        System.out.printf("     Gewichtung:     %d%n", criteria.getWeighting());
                        if (criteria instanceof EvaluationAbilities) {
                            System.out.printf("     Fähigkeiten:    %s%n", ((EvaluationAbilities) criteria).getListOfAbilities());                        }

                        if (criteria instanceof EvaluationExperience) {
                            System.out.printf("     Erfahrung:      %d%n", ((EvaluationExperience) criteria).getExperienceInYears());                        }

                    });
                    System.out.println(line(header));
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
