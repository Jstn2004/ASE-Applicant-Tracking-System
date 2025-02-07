package com.ats.entities.criteria;

import com.ats.entities.EvaluationCriterion;

import java.util.Objects;

public class EvaluationExperience extends EvaluationCriterion {
    private int experienceInYears;

    public EvaluationExperience(String name, int points, int experienceInYears, int weighting) {
        super(name, points, weighting);
        if(points < 1 || points > 100 ){
            throw new IllegalArgumentException("Points must be between 1 and 100");
        }
        if(weighting < 1 || weighting > 10){
            throw new IllegalArgumentException("Weighting must be between 1 and 10");
        }
        this.experienceInYears = experienceInYears;
    }

    public int getExperienceInYears() {
        return experienceInYears;
    }

    public void setExperienceInYears(int experienceInYears) {
        this.experienceInYears = experienceInYears;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EvaluationExperience that)) return false;
        if (!super.equals(o)) return false;
        return experienceInYears == that.experienceInYears;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experienceInYears);
    }

    @Override
    public String toString() {
        return  "EvaluationExperience{" +
                "name=" + super.getName() +
                ", points=" + super.getPoints() +
                ", experienceInYear=" + experienceInYears +
                ", weighting=" + super.getWeighting() +
                '}';
    }
}
