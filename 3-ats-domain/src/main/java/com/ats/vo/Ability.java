package com.ats.vo;

import java.util.Objects;

public final class Ability {
    private final int points;
    private final String name;

    public Ability(int points, String name) {
        if (points < 1) {
            throw new IllegalArgumentException("Punkte muss größer als 0 sein");
        }
        this.points = points;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return this.points;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ability punkte)) return false;
        return points == punkte.points;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(points);
    }
}
