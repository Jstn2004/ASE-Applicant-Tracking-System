package com.ats.entities.kriterien;

import com.ats.entities.Bewertungskriterium;

public class BewertungBerufserfahrung extends Bewertungskriterium {
    private int jahreErfahrung;

    public BewertungBerufserfahrung(String name, int punkte, int jahreErfahrung) {
        super(name, punkte);
        this.jahreErfahrung = jahreErfahrung;
    }
}
