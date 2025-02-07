package com.ats.validation;

import java.util.logging.Logger;
import java.util.regex.Pattern;

public class JobAdvertismentValidation {

    private final Logger logger;

    public JobAdvertismentValidation(Logger logger) {
        this.logger = logger;
    }

    public boolean validatePoints(String points)
    {
        logger.info("Validate: " + points);
        if(isValidNumber(points))
        {
            int pointsInt = Integer.parseInt(points);
            if(pointsInt >= 1 && pointsInt <= 100)
            {
                return true;
            }else
            {
                System.out.println("\u001B[31mBitte gebe ein Zahl zwischen 1 und 10 ein\u001B[0m");
                return false;
            }
        }
        else{
            return false;
        }
    }

    public boolean validateAbilitieOrKeyword(String input) {
        logger.info("Validate Abilities Or Keyword: " + input);
        boolean result = Pattern.matches("^.*;\\d+$", input);
        System.out.println(result);
        System.out.println(input);
        if(result)
        {
            return true;
        }
        else
        {
            System.out.println("\u001B[31mBitte Nutzen Sie das korrekte Format\u001B[0m");
            return false;
        }
    }

    public boolean validateWeighting(String weighting)
    {
        logger.info("ValidateWeighting: " + weighting);
        if(isValidNumber(weighting))
        {
            int weightingInt = Integer.parseInt(weighting);
            if(weightingInt >= 1 && weightingInt <= 10)
            {
                return true;
            }else
            {
                System.out.println("\u001B[31mBitte gebe ein Zahl zwischen 1 und 10 ein\u001B[0m");
                return false;
            }
        }
        else {
            return false;
        }
    }

    public boolean isValidNumber(String input) {
        logger.info("Validate Number: " + input);
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("\u001B[31mBitte gebe eine Zahl ein\u001B[0m");
            return false;
        }
    }
}
