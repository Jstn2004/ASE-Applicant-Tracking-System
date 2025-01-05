package com.ats.vo;

import com.ats.entities.Bewertungskriterium;

import java.util.Objects;

public class Lebenslauf {
    private final String pfad;
    private final Iterable<Bewertungskriterium> kriterien;


    public Lebenslauf(String pfad, Iterable<Bewertungskriterium> kriterien) {
        this.pfad = pfad;
        this.kriterien = kriterien;
    }

    public String getPfad() {
        return pfad;
    }

    public Iterable<Bewertungskriterium> getKriterien() {
        return kriterien;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Lebenslauf that)) return false;
        return Objects.equals(pfad, that.pfad) && Objects.equals(kriterien, that.kriterien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pfad, kriterien);
    }
}
