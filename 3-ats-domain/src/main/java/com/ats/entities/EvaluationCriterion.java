package com.ats.entities;

import java.util.Objects;
import java.util.UUID;

public class EvaluationCriterion {
    private final UUID id;
    private String name;
    private int punkte;

    public EvaluationCriterion(String name, int punkte) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.punkte = punkte;
    }

    public UUID getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EvaluationCriterion that = (EvaluationCriterion) obj;
        return punkte == that.punkte && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, punkte);
    }
}