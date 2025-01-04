package com.ats.entities;

import java.util.Objects;
import java.util.UUID;

public class Rangliste {
    private final UUID id;
    private String name;
    private Iterable<Bewerber> bewerber;
    private Ausschreibung ausschreibung;

    public Rangliste(String name, Iterable<Bewerber> bewerber, Ausschreibung ausschreibung) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.bewerber = bewerber;
        this.ausschreibung = ausschreibung;
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

    public Iterable<Bewerber> getBewerber() {
        return bewerber;
    }

    public void setBewerber(Iterable<Bewerber> bewerber) {
        this.bewerber = bewerber;
    }

    public Ausschreibung getAusschreibung() {
        return ausschreibung;
    }

    public void setAusschreibung(Ausschreibung ausschreibung) {
        this.ausschreibung = ausschreibung;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rangliste rangliste)) return false;
        return Objects.equals(id, rangliste.id) && Objects.equals(name, rangliste.name) && Objects.equals(bewerber, rangliste.bewerber) && Objects.equals(ausschreibung, rangliste.ausschreibung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bewerber, ausschreibung);
    }
}
