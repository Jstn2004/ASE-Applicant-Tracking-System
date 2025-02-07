package com.ats.vo;

import java.util.Objects;

public final class Keyword {

    private final String keyword;
    private final int points;

    public Keyword(String keyword, int points) {
        this.keyword = keyword;
        this.points = points;
    }

    public String getKeyword() {
        return keyword;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Keyword keyword1)) return false;
        return points == keyword1.points && Objects.equals(keyword, keyword1.keyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyword, points);
    }

    @Override
    public String toString() {
        return "(" + keyword + "; " + points + ")";
    }
}
