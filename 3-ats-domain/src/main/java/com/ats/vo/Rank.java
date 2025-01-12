package com.ats.vo;

import java.util.Objects;

public final class Rank {
    private final int platz;

    public Rank(int platz) {
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
        Rank rank = (Rank) o;
        return platz == rank.platz;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(platz);
    }

}
