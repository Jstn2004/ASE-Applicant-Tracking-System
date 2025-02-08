package com.ats.entities;


import java.util.Objects;

/**
 * Die Klasse repräsentiert eine Jobausschreibung und dient dazu, alle relevanten Informationen
 * für eine spezifische Stellenanzeige zu speichern. Diese Informationen umfassen die eindeutige ID
 * der Ausschreibung, den Titel der Stelle, die Beschreibung der Position und eine Sammlung von
 * Bewertungskriterien, die zur Evaluierung von Bewerbungen verwendet werden.
 * <p>
 * Die Jobausschreibung wird mit einer eindeutigen ID erstellt, die es ermöglicht, sie von anderen
 * Ausschreibungen zu unterscheiden. Der Titel und die Beschreibung der Stelle können bei Bedarf angepasst werden.
 * <p>
 * Die Kriterien der Ausschreibung, die aus verschiedenen Evaluierungsmaßstäben bestehen (z.B. Fähigkeiten,
 * Erfahrungen, Schlüsselwörter), werden ebenfalls in der Ausschreibung gespeichert. Diese Kriterien
 * helfen dabei, Bewerber hinsichtlich ihrer Eignung für die Position zu bewerten.
 */
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
}
