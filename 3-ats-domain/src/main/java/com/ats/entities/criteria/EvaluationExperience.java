package com.ats.entities.criteria;

import com.ats.entities.EvaluationCriterion;

public class EvaluationExperience extends EvaluationCriterion {
    private int jahreErfahrung;

    public EvaluationExperience(String name, int punkte, int jahreErfahrung) {
        super(name, punkte);
        this.jahreErfahrung = jahreErfahrung;
    }
}
