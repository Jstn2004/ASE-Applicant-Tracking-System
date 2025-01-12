package com.ats.vo;

import java.util.Objects;

public final class Points {
    private final int anzahl;

    public Points(int anzahl) {
        if (anzahl < 1) {
            throw new IllegalArgumentException("Anzahl muss größer als 0 sein");
        }
        this.anzahl = anzahl;
    }

    public int getAnzahl() {
        return this.anzahl;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Points punkte)) return false;
        return anzahl == punkte.anzahl;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(anzahl);
    }
}
