package com.ats.entities;


import java.util.Objects;
import java.util.UUID;

public class Tender {
    private final UUID id;
    private String titel;
    private String description;
    //private Iterable<Bewertungskriterium> kriterien;


    public Tender(String titel, String description) {
        this.id = UUID.randomUUID();
        this.titel = titel;
        this.description = description;
        //this.kriterien = kriterien;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public void setKriterien(Iterable<Bewertungskriterium> kriterien) {
//        this.kriterien = kriterien;
//    }

    public UUID getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public String getDescription() {
        return description;
    }

//    public Iterable<Bewertungskriterium> getKriterien() {
//        return kriterien;
//    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tender that)) return false;
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
        return "Tender{" +
                "id=" + id +
                ", titel='" + titel + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
