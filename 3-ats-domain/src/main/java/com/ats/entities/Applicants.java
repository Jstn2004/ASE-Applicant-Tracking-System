package com.ats.entities;

import com.ats.vo.Resume;
import com.ats.vo.Rank;

import java.util.Objects;
import java.util.UUID;

public class Applicants {
    private final UUID id;
    private String name;
    private String email;
    private Resume resume;
    private Rank rank;

    public Applicants(String name, String email, Resume resume) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.resume = resume;
        this.rank = null;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Resume getLebenslaufPfad() {
        return resume;
    }

    public void setLebenslaufPfad(Resume resume) {
        this.resume = resume;
    }

    public Rank getRang() {
        return rank;
    }

    public void setRang(Rank rank) {
        this.rank = rank;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicants applicants = (Applicants) o;
        return id.equals(applicants.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}