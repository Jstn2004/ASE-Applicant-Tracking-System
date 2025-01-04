package com.ats.entities;

import com.ats.vo.Kriterium;

import java.util.Objects;
import java.util.UUID;

public class Ausschreibung {
    private final UUID id;
    private String titel;
    private String description;
    private Iterable<Kriterium> kriterien;


    public Ausschreibung( String titel, String description, Iterable<Kriterium> kriterien) {
        this.id = UUID.randomUUID();
        this.titel = titel;
        this.description = description;
        this.kriterien = kriterien;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKriterien(Iterable<Kriterium> kriterien) {
        this.kriterien = kriterien;
    }

    public UUID getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public String getDescription() {
        return description;
    }

    public Iterable<Kriterium> getKriterien() {
        return kriterien;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ausschreibung that)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(titel, that.titel) &&
                Objects.equals(description, that.description) &&
                Objects.equals(kriterien, that.kriterien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titel, description, kriterien);
    }
}
