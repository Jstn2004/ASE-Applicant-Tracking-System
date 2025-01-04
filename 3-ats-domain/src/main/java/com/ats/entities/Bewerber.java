package com.ats.entities;

import com.ats.vo.Lebenslauf;
import com.ats.vo.Rang;

import java.util.Objects;
import java.util.UUID;

public class Bewerber {
    private final UUID id;
    private String name;
    private String email;
    private Lebenslauf lebenslauf;
    private Rang rang;

    public Bewerber(String name, String email, Lebenslauf lebenslauf) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.lebenslauf = lebenslauf;
        this.rang = null;
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

    public Lebenslauf getLebenslaufPfad() {
        return lebenslauf;
    }

    public void setLebenslaufPfad(Lebenslauf lebenslauf) {
        this.lebenslauf = lebenslauf;
    }

    public Rang getRang() {
        return rang;
    }

    public void setRang(Rang rang) {
        this.rang = rang;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bewerber bewerber = (Bewerber) o;
        return id.equals(bewerber.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}