package com.ats.vo;

import java.util.Objects;

public final class Rang {
    private final int platz;

    public Rang(int platz) {
        super();
        if (platz < 1) {
            throw new IllegalArgumentException("Platz muss mindestens 1 sein");
        }
        this.platz = platz;
    }

    public int getPlatz() {
        return platz;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rang rang = (Rang) o;
        return platz == rang.platz;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(platz);
    }

}
