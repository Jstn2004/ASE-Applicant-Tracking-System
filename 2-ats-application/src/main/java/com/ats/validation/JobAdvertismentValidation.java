package com.ats.validation;

public class JobAdvertismentValidation {

    public boolean validatePoints(String points)
    {
        if(isValidNumber(points))
        {
            int pointsInt = Integer.parseInt(points);
            System.out.println("Bitte gebe ein Zahl zwischen 1 und 100 ein");
            return pointsInt >= 1 && pointsInt <= 100;
        }
        else return false;
    }

    public boolean validateWeighting(String weighting)
    {
        if(isValidNumber(weighting))
        {
            int weightingInt = Integer.parseInt(weighting);
            System.out.println("Bitte gebe ein Zahl zwischen 1 und 10 ein");
            return weightingInt >= 1 && weightingInt <= 10;
        }
        else return false;
    }

    public static boolean isValidNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Bitte gebe eine Zahl ein");
            return false;
        }
    }
}
