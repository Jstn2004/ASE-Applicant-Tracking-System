package com.ats.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Leaderboard {
    private final UUID id;
    private String name;
    private Iterable<Applicant> applicants;
    private JobAdvertisement jobAdvertisement;

    public Leaderboard(String name, Iterable<Applicant> bewerber, JobAdvertisement jobAdvertisement) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.applicants = bewerber;
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

    public Iterable<Applicant> getApplicants() {
        return applicants;
    }

    public List<String> formatLeaderboard() {
        List<Applicant> sortedApplicants = new ArrayList<>();
        applicants.forEach(sortedApplicants::add);

        sortedApplicants.sort((a1, a2) -> Integer.compare(a2.getPoints(), a1.getPoints()));

        List<String> resultStrings = new ArrayList<>();

        resultStrings.add("Leaderboard: " + name);

        int rank = 1;
        for (Applicant a : sortedApplicants) {
            String formatted = String.format("%d | %s | %d", rank++, a.getName(), a.getPoints());
            resultStrings.add(formatted);
        }

        return resultStrings;
    }

    public void setApplicants(Iterable<Applicant> applicants) {
        this.applicants = applicants;
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
        return Objects.equals(id, rangliste.id) && Objects.equals(name, rangliste.name) && Objects.equals(applicants, rangliste.applicants) && Objects.equals(jobAdvertisement, rangliste.jobAdvertisement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, applicants, jobAdvertisement);
    }
}
