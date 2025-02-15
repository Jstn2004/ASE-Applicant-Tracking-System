package com.ats.vo;

import com.ats.entities.Applicant;
import com.ats.entities.EvaluationCriterion;

import java.util.Objects;

public final class Resume {
    private final String path;

    public Resume(String pfad) {
        this.path = pfad;
    }

    public String getPath() {
        return path;
    }



    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Resume that)) return false;
        return Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }
}
