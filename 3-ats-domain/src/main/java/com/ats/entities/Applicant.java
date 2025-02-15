package com.ats.entities;

import com.ats.vo.Resume;
import com.ats.vo.Rank;

import java.util.Objects;
import java.util.UUID;

public class Applicant {
    private final UUID id;
    private String name;
    private String email;
    private Rank rank;
    private String phoneNumber;
    private String address;
    private Resume resume;


    public Applicant(String name, String email, String phoneNumber, String address, Resume resume) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.resume = resume;
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
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

    public Rank getRang() {
        return rank;
    }

    public void setRang(Rank rank) {
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicants = (Applicant) o;
        return id.equals(applicants.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}