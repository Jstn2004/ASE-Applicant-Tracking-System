package com.ats.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JobAdvertisement {
    private final String id;
    private final String titel;
    private final String description;
    private final List<EvaluationCriterion> criteria;

    private JobAdvertisement(Builder builder) {
        this.id = builder.id;
        this.titel = builder.titel;
        this.description = builder.description;
        this.criteria = builder.criteria;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public String getDescription() {
        return description;
    }

    public List<EvaluationCriterion> getCriteria() {
        return criteria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobAdvertisement that)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(titel, that.titel) &&
                Objects.equals(description, that.description) &&
                Objects.equals(criteria, that.criteria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titel, description);
    }

    @Override
    public String toString() {
        return "JobAdvertisement{" +
                "id='" + id + '\'' +
                ", titel='" + titel + '\'' +
                ", description='" + description + '\'' +
                ", criteria=" + criteria +
                '}';
    }

    public static class Builder {
        private String id;
        private String titel;
        private String description;
        private List<EvaluationCriterion> criteria = new ArrayList<>();

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withTitel(String titel) {
            this.titel = titel;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withCriteria(List<EvaluationCriterion> criteria) {
            this.criteria = criteria;
            return this;
        }

        public JobAdvertisement build() {
            if (id == null || titel == null || description == null) {
                throw new IllegalStateException("ID, Titel und Beschreibung dürfen nicht null sein.");
            }
            return new JobAdvertisement(this);
        }
    }
}