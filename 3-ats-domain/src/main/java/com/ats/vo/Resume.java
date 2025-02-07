package com.ats.vo;

import com.ats.entities.Applicant;
import com.ats.entities.EvaluationCriterion;

import java.util.Objects;

public final class Resume {
    private final String path;
    private Applicant application;

    public Resume(String pfad, Applicant application) {
        this.path = pfad;
        this.application = application;
    }

    public String getPath() {
        return path;
    }

    public Applicant getApplication() {
        return application;
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
