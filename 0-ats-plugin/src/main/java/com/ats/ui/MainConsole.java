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
    private final DatabaseConfiguration databaseConfiguration;
    private final JobAdvertisementRepository jobAdvertisementRepository;
    private final JobAdvertisementCreater jobAdvertisementCreater;
    private final JobAdvertisementLoader jobAdvertisementLoader;
    private final JobAdvertisementDeleter jobAdvertisementDeleter;
    private final JobAdvertisementParser jobAdvertisementParser;
    private final EvaluationCriteriaCreater evaluationCriteriaCreater;
    private final JobAdvertismentValidation jobAdvertismentValidation;
    private final JobAdvertisementController jobAdvertismentController;
    private final ResumeController resumeController;
    private final JobAdvertisementValidationController jobAdvertisementValidationController;


    public MainConsole(Logger logger,
                       DatabaseConfiguration databaseConfiguration,
                       JobAdvertisementRepository jobAdvertisementRepository,
                       JobAdvertisementCreater jobAdvertisementCreater,
                       JobAdvertisementLoader jobAdvertisementLoader,
                       JobAdvertisementDeleter jobAdvertisementDeleter,
                       JobAdvertisementParser jobAdvertisementParser,
                       EvaluationCriteriaCreater evaluationCriteriaCreater,
                       JobAdvertismentValidation jobAdvertismentValidation,
                       JobAdvertisementController jobAdvertismentController, ResumeController resumeController,
                       JobAdvertisementValidationController jobAdvertisementValidationController) {
        this.logger = logger;
        this.databaseConfiguration = databaseConfiguration;
        this.jobAdvertisementRepository = jobAdvertisementRepository;
        this.jobAdvertisementCreater = jobAdvertisementCreater;
        this.jobAdvertisementLoader = jobAdvertisementLoader;
        this.jobAdvertisementDeleter = jobAdvertisementDeleter;
        this.jobAdvertisementParser = jobAdvertisementParser;
        this.evaluationCriteriaCreater = evaluationCriteriaCreater;
        this.jobAdvertismentValidation = jobAdvertismentValidation;
        this.jobAdvertismentController = jobAdvertismentController;
        this.resumeController = resumeController;
        this.jobAdvertisementValidationController = jobAdvertisementValidationController;
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
                    System.out.println("Lebensläufe");
                    loadallResumes();
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
                    System.out.println("\u001B[31mUngültige Eingabe, bitte versuchen Sie es erneut.\u001B[0m");
                    startCLI();
            }
        }
        scanner.close();
    }

    public void loadallResumes()
    {
        resumeController.loadAllResumes();
    }

    public void jobAdvertismentCLI() {
        System.out.println();

        String header = "Ausschreibungen ";
        System.out.println(line(header));
        System.out.println(header(header));
        System.out.println(line(header));

        System.out.println("Wählen Sie eine Option:");
        System.out.println("1: Neue Jobausschreibung");
        System.out.println("2: Alle Jobausschreibung");
        System.out.println("3: Zurück");
        System.out.println("4: Beenden");
        System.out.print("Eingabe:");
        while (running) {
            String input = scanner.next();
            switch (input) {
                case "1":
                    newJobAdvertisementCLI();
                    break;
                case "2":
                    allJobAdvertismentCLI();
                    break;
                case "3":
                    startCLI();
                    break;
                case "4":
                    System.out.println("Beenden");
                    running = false;
                    break;
                default:
                    System.out.println("\u001B[31mUngültige Eingabe, bitte versuchen Sie es erneut.\u001B[0m");
                    jobAdvertismentCLI();
            }
        }
        scanner.close();

    }

    public void newJobAdvertisementCLI() {
        System.out.println();

        String header = "Neue Jobausschreibung";
        System.out.println(line(header));
        System.out.println(header(header));
        System.out.println(line(header));

        System.out.print("Titel: ");
        String title = scanner.nextLine();
        title = scanner.nextLine();

        String description;
        do {
            System.out.print("Beschreibung: ");
            description = scanner.nextLine();
        }while (!jobAdvertisementValidationController.startBlankInputValidation(description));

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

        String input;
        do {
            EvaluationAbilities evaluationAbilities = createEvaluationAbilitiesCLI();
            evaluationCriterionIterable.add(evaluationAbilities);
            System.out.println("\u001B[90mWeiter Fähigkeiten festlegen? ('y' zum Beenden 'n' zum Fortsetzen)\u001B[0m");
            input = scanner.nextLine();
        }while (input.equals("y"));


        EvaluationExperience evaluationExperience = createEvaluationExperienceCLI();
        evaluationCriterionIterable.add(evaluationExperience);

        EvaluationKeywords evaluationKeywords = createEvaluationKeywordCLI();
        evaluationCriterionIterable.add(evaluationKeywords);
        return evaluationCriterionIterable;
    }

    public EvaluationAbilities createEvaluationAbilitiesCLI() {
        System.out.println(header("Fähigkeiten"));
        List<String> evaluationedCriterionArguments= evaluationCriterionCLI(false);
        List<Ability> abilities = createAbilitieCLI();

        return jobAdvertismentController.createEvaluationAbilities(evaluationedCriterionArguments, abilities);
    }

    public EvaluationExperience createEvaluationExperienceCLI() {
        System.out.println(header("Erfahrungen"));
        List<String> evaluationedCriterionArguments= evaluationCriterionCLI(true);
        String experience;
        do{
            System.out.print("Erfahrungen (In Jahren angeben): ");
            experience= scanner.nextLine();
        }while (!jobAdvertisementValidationController.startExperienceValidation(experience));

        return jobAdvertismentController.createEvaluationExperience(evaluationedCriterionArguments, experience);
    }

    public EvaluationKeywords createEvaluationKeywordCLI() {
        System.out.println(header("Schlüsselwörter"));
        List<String> evaluationedCriterionArguments= evaluationCriterionCLI(false);
        List<Keyword> keywords = createKeywordCLI();

        return jobAdvertismentController.createEvaluationKeywords(evaluationedCriterionArguments, keywords);
    }

    public List<Ability> createAbilitieCLI()
    {
        List<Ability> abilityString = new ArrayList<>();
        String abilities;
        do{
            do {
                System.out.println("Fähigkeit: (format: Name;Punkte) ('end' zum Beenden)");
                abilities = scanner.nextLine();
                if (abilities.equalsIgnoreCase("end")) {
                    break;
                }
            }while (!jobAdvertisementValidationController.startAbilitiesValidationOrKeyword(abilities));
            if(abilities.equalsIgnoreCase("end")) {
                break;
            }
            abilityString.add(jobAdvertismentController.createAbility(abilities));
        }while (!abilities.equalsIgnoreCase("end"));
        return abilityString;

    }

    public List<Keyword> createKeywordCLI()
    {
        List<Keyword> keywordStrings = new ArrayList<>();
        String keywords;
        do{
            do {
                System.out.println("Schlüsselwörter: (format: Schlüsselwort;Punkte) ('end' zum Beenden)");
                keywords = scanner.nextLine();
                if (keywords.equalsIgnoreCase("end")) {
                    break;
                }
            }while (!jobAdvertisementValidationController.startAbilitiesValidationOrKeyword(keywords));
            if(keywords.equalsIgnoreCase("end")) {
                break;
            }
            keywordStrings.add(jobAdvertismentController.createKeyword(keywords));
        }while (!keywords.equalsIgnoreCase("end"));
        return keywordStrings;

    }


    public List<String> evaluationCriterionCLI(boolean pointsNeeded){
        List<String> evaluationCriterionArguments= new LinkedList<>();

        String name;
        do {
            System.out.print("Name: ");
            name= scanner.nextLine();
        }while (!jobAdvertisementValidationController.startBlankInputValidation(name));
        evaluationCriterionArguments.add(name);

        // Punkte
        if (pointsNeeded) {
            String points;
            do {
                System.out.print("Punkte (Zahl von 1 bis 100): ");
                points = scanner.nextLine();
            }while (!jobAdvertisementValidationController.startPointsValidation(points));
            evaluationCriterionArguments.add(points);
        }


        // Gewichtung
        String weighting;
        do {
            System.out.print("Gewichtung (Zahl von 1 bis 10): ");
            weighting = scanner.nextLine();
        }while (!jobAdvertisementValidationController.startWeightingValidation(weighting));
        evaluationCriterionArguments.add(weighting);
        return evaluationCriterionArguments;
    }

    public void allJobAdvertismentCLI() {
        System.out.println();
        String header = "Alle Jobausschreibungen";
        System.out.println(line(header));
        System.out.println(header(header));
        System.out.println(line(header));
        jobAdvertismentController.loadAllJobAdvertisement().forEach(item ->
                {
                    System.out.printf("ID:             %s%n", item.getId());
                    System.out.printf("Title:          %s%n", item.getTitel());
                    System.out.printf("Beschreibung:   %s%n", item.getDescription());
                    System.out.println("Bewertungskriterien:");
                    item.getCriteria().forEach(criteria ->
                    {
                        System.out.println("  " + criteria.getClass().getSimpleName());
                        System.out.printf("     Name:            %s%n", criteria.getName());
                        System.out.printf("     Punkte:          %d%n", criteria.getPoints());
                        System.out.printf("     Gewichtung:      %d%n", criteria.getWeighting());
                        if (criteria instanceof EvaluationAbilities) {
                            System.out.printf("     Fähigkeiten:     %s%n", ((EvaluationAbilities) criteria).getListOfAbilities());}

                        if (criteria instanceof EvaluationExperience) {
                            System.out.printf("     Erfahrung:       %d%n", ((EvaluationExperience) criteria).getExperienceInYears());}

                        if (criteria instanceof EvaluationKeywords) {
                            System.out.printf("     Schlüsselwörter: %s%n", ((EvaluationKeywords) criteria).getListOfKeywords());}

                    });
                    System.out.println(line(header));
                }
        );

        System.out.println("Wählen Sie eine Option:");
        System.out.println("1: Jobausschreibung Löschen");
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
                    System.out.println("\u001B[31mUngültige Eingabe, bitte versuchen Sie es erneut.\u001B[0m");
                    allJobAdvertismentCLI();
            }
        }

    }

    public void deleteActiveJobAdvertisment() {
        System.out.println();

        String header = "Jobausschreibung löschen";
        System.out.println(line(header));
        System.out.println(header(header));
        System.out.println(line(header));

        System.out.println("Zum Löschen die ID der Ausschreibung eingeben");
        String idToDelete = scanner.next();
        jobAdvertismentController.deleteJobAdvertisementById(idToDelete);
        allJobAdvertismentCLI();


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
