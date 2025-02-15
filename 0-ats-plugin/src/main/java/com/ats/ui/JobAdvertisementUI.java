package com.ats.ui;

import com.ats.JobAdvertisementController;
import com.ats.JobAdvertisementValidationController;
import com.ats.ResumeController;
import com.ats.entities.EvaluationCriterion;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.vo.Ability;
import com.ats.vo.Keyword;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class JobAdvertisementUI {
    private final Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    private final JobAdvertisementValidationController jobAdvertisementValidationController;
    private final JobAdvertisementController jobAdvertismentController;
    private final ResumeController resumeController;
    private MainConsole mainConsole;
    private ResumeUI resumeUI;

    public JobAdvertisementUI(JobAdvertisementValidationController jobAdvertisementValidationController, JobAdvertisementController jobAdvertismentController, ResumeController resumeController, MainConsole mainConsole, ResumeUI resumeUI) {
        this.jobAdvertisementValidationController = jobAdvertisementValidationController;
        this.jobAdvertismentController = jobAdvertismentController;
        this.resumeController = resumeController;
        this.mainConsole = mainConsole;
        this.resumeUI = resumeUI;
    }

    public void setMainConsole(MainConsole mainConsole) {
        this.mainConsole = mainConsole;
    }
    public void setResumeUI(ResumeUI resumeUI) {
        this.resumeUI = resumeUI;
    }


    public void jobAdvertismentCLI() {
        System.out.println();

        String header = "Ausschreibungen ";
        System.out.println(mainConsole.line(header));
        System.out.println(mainConsole.header(header));
        System.out.println(mainConsole.line(header));

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
                    allJobAdvertismentCLI(false);
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
                    jobAdvertismentCLI();
            }
        }
        scanner.close();

    }

    public void newJobAdvertisementCLI() {
        System.out.println();

        String header = "Neue Jobausschreibung";
        System.out.println(mainConsole.line(header));
        System.out.println(mainConsole.header(header));
        System.out.println(mainConsole.line(header));

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
        System.out.println(mainConsole.line(header));
        System.out.println(mainConsole.header(header));
        System.out.println(mainConsole.line(header));

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
        System.out.println(mainConsole.header("Fähigkeiten"));
        List<String> evaluationedCriterionArguments= evaluationCriterionCLI(false);
        List<Ability> abilities = createAbilitieCLI();

        return jobAdvertismentController.createEvaluationAbilities(evaluationedCriterionArguments, abilities);
    }

    public EvaluationExperience createEvaluationExperienceCLI() {
        System.out.println(mainConsole.header("Erfahrungen"));
        List<String> evaluationedCriterionArguments= evaluationCriterionCLI(true);
        String experience;
        do{
            System.out.print("Erfahrungen (In Jahren angeben): ");
            experience= scanner.nextLine();
        }while (!jobAdvertisementValidationController.startExperienceValidation(experience));

        return jobAdvertismentController.createEvaluationExperience(evaluationedCriterionArguments, experience);
    }

    public EvaluationKeywords createEvaluationKeywordCLI() {
        System.out.println(mainConsole.header("Schlüsselwörter"));
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

    public void allJobAdvertismentCLI(boolean type) {
        System.out.println();
        String header = "Alle Jobausschreibungen";
        System.out.println(mainConsole.line(header));
        System.out.println(mainConsole.header(header));
        System.out.println(mainConsole.line(header));
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
                    System.out.println(mainConsole.line(header));
                }
        );

        if(type)
        {
            selectJobAdvertisment();
        }
        else {
            deleteJobAdvertisment();
        }
    }

    public void selectJobAdvertisment() {
        System.out.println("Wählen Sie eine Option:");
        System.out.println("1: Jobausschreibung Auswählen");
        System.out.println("2: Zurück");
        System.out.print("Eingabe:");
        while (running) {
            String input = scanner.next();
            switch (input) {
                case "1":
                    selectActiveJobAdvertisment();
                    break;
                case "2":
                    resumeUI.resumeCLI();
                    break;
                default:
                    System.out.println("\u001B[31mUngültige Eingabe, bitte versuchen Sie es erneut.\u001B[0m");
                    allJobAdvertismentCLI(false);
            }
        }
    }

    public void deleteJobAdvertisment() {
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
                    allJobAdvertismentCLI(false);
            }
        }
    }

    public void deleteActiveJobAdvertisment() {
        System.out.println();

        String header = "Jobausschreibung löschen";
        System.out.println(mainConsole.line(header));
        System.out.println(mainConsole.header(header));
        System.out.println(mainConsole.line(header));

        System.out.println("Zum Löschen die ID der Ausschreibung eingeben");
        String idToDelete = scanner.next();
        jobAdvertismentController.deleteJobAdvertisementById(idToDelete);
        allJobAdvertismentCLI(false);

    }

    public void selectActiveJobAdvertisment() {
        System.out.println();

        String header = "Jobausschreibung auswählen";
        System.out.println(mainConsole.line(header));
        System.out.println(mainConsole.header(header));
        System.out.println(mainConsole.line(header));

        System.out.println("Zum Auswählen die ID der Ausschreibung eingeben");
        String id = scanner.next();
        String result = resumeController.selectedResume(id);
        resumeUI.resumeCLI();

    }




}
