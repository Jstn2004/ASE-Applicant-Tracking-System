package com.ats.vo;

public class Lebenslauf {
    private final String pfad;
    private final Iterable<Kriterium> kriterien;


    public Lebenslauf(String pfad, Iterable<Kriterium> kriterien) {
        this.pfad = pfad;
        this.kriterien = kriterien;
    }
}
