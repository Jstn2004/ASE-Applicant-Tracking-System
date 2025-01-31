package com.ats.entities;


import java.util.Objects;

public class JobAdvertisement {
    private final String id;
    private String titel;
    private String description;
    private Iterable<EvaluationCriterion> criteria;


    public JobAdvertisement(String id, String titel, String description, Iterable<EvaluationCriterion> criteria) {
        this.id = id;
        this.titel = titel;
        this.description = description;
        this.criteria = criteria;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setCriteria(Iterable<EvaluationCriterion> criteria) {
        this.criteria = criteria;
    }

    public Iterable<EvaluationCriterion> getCriteria() {
        return criteria;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof JobAdvertisement that)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(titel, that.titel) &&
                Objects.equals(description, that.description);  //&&
                //Objects.equals(kriterien, that.kriterien);
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
}
