package com.ats.entities;

import java.util.Objects;
import java.util.UUID;

public class EvaluationCriterion {
    private final UUID id;
    private String name;
    private int points;
    private int weighting;

    public EvaluationCriterion(String name, int points, int weighting) {
        this.weighting = weighting;
        this.id = UUID.randomUUID();
        this.name = name;
        this.points = points;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWeighting() {
        return weighting;
    }

    public void setWeighting(int weighting) {
        this.weighting = weighting;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EvaluationCriterion that = (EvaluationCriterion) obj;
        return points == that.points && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, points);
    }
}