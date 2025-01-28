package com.ats.entities;

import java.util.Objects;
import java.util.UUID;

public class EvaluationCriterion {
    private final UUID id;
    private String name;
    private int points;

    public EvaluationCriterion(String name, int points) {
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