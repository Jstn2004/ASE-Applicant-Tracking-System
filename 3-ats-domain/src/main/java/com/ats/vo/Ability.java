package com.ats.vo;

import java.util.Objects;

public final class Ability {
    private final String ability;
    private final int points;

    public Ability(String ability, int points) {
        this.ability = ability;
        this.points = points;
    }

    public String getAbility() {
        return ability;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ability ability)) return false;
        return Objects.equals(this.ability, ability.ability);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ability);
    }

    @Override
    public String toString() {
        return "(" + ability + "; " + points + ")";
    }
}
