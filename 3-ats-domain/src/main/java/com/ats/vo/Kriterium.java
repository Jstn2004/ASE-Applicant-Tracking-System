package com.ats.vo;

import java.util.Objects;

public final class Kriterium {
    private final String name;
    private final float gewichtung;

    public Kriterium(String name, float gewichtung) {
        this.name = name;
        this.gewichtung = gewichtung;
    }

    public String getName() {
        return name;
    }

    public float getGewichtung() {
        return gewichtung;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Kriterium kriterium)) return false;
        return Float.compare(gewichtung, kriterium.gewichtung) == 0 && Objects.equals(name, kriterium.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gewichtung);
    }
}
