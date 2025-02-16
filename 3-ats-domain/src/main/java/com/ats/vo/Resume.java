package com.ats.vo;

import java.util.Objects;

public final class Resume {
    private final String contant;

    public Resume(String pfad) {
        this.contant = pfad;
    }

    public String getContant() {
        return contant;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Resume that)) return false;
        return Objects.equals(contant, that.contant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contant);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "contant='" + contant + '\'' +
                '}';
    }
}
