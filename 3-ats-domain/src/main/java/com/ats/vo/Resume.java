package com.ats.vo;

import com.ats.entities.EvaluationCriterion;

import java.util.Objects;

public final class Resume {
    private final String path;
    private final Iterable<EvaluationCriterion> criterias;


    public Resume(String pfad, Iterable<EvaluationCriterion> criterias) {
        this.path = pfad;
        this.criterias = criterias;
    }

    public String getPath() {
        return path;
    }

    public Iterable<EvaluationCriterion> getCriterias() {
        return criterias;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Resume that)) return false;
        return Objects.equals(path, that.path) && Objects.equals(criterias, that.criterias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, criterias);
    }
}
