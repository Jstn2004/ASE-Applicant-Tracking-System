package com.ats.entities;

import java.util.Objects;
import java.util.UUID;

public class Leaderboard {
    private final UUID id;
    private String name;
    private Iterable<Applicants> bewerber;
    private JobAdvertisement jobAdvertisement;

    public Leaderboard(String name, Iterable<Applicants> bewerber, JobAdvertisement jobAdvertisement) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.bewerber = bewerber;
        this.jobAdvertisement = jobAdvertisement;
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

    public Iterable<Applicants> getBewerber() {
        return bewerber;
    }

    public void setBewerber(Iterable<Applicants> bewerber) {
        this.bewerber = bewerber;
    }

    public JobAdvertisement getAusschreibung() {
        return jobAdvertisement;
    }

    public void setAusschreibung(JobAdvertisement jobAdvertisement) {
        this.jobAdvertisement = jobAdvertisement;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Leaderboard rangliste)) return false;
        return Objects.equals(id, rangliste.id) && Objects.equals(name, rangliste.name) && Objects.equals(bewerber, rangliste.bewerber) && Objects.equals(jobAdvertisement, rangliste.jobAdvertisement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bewerber, jobAdvertisement);
    }
}
