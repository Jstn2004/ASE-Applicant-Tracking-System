package com.ats.vo;

import com.ats.entities.EvaluationCriterion;

import java.util.Objects;

public class Resume {
    private final String pfad;
    private final Iterable<EvaluationCriterion> kriterien;


    public Resume(String pfad, Iterable<EvaluationCriterion> kriterien) {
        this.pfad = pfad;
        this.kriterien = kriterien;
    }

    public String getPfad() {
        return pfad;
    }

    public Iterable<EvaluationCriterion> getKriterien() {
        return kriterien;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Resume that)) return false;
        return Objects.equals(pfad, that.pfad) && Objects.equals(kriterien, that.kriterien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pfad, kriterien);
    }
}
