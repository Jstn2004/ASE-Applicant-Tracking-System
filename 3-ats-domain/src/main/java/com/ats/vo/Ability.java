package com.ats.vo;

import java.util.Objects;

public final class Ability {
    private final String name;

    public Ability( String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ability ability)) return false;
        return Objects.equals(name, ability.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return  name ;
    }
}
