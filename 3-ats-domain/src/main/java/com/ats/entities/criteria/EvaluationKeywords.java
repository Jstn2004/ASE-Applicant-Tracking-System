package com.ats.entities.criteria;

import com.ats.entities.EvaluationCriterion;

import java.util.List;
import java.util.Objects;

public class EvaluationKeywords extends EvaluationCriterion {

    private List<String> listOfKeywords;
    private int weighting;

    public EvaluationKeywords(String name, int punkte, List<String> listOfKeywords, int weighting) {
        super(name, punkte);
        this.listOfKeywords = listOfKeywords;
        this.weighting = weighting;
    }

    public int getWeighting() {
        return weighting;
    }

    public void setWeighting(int weighting) {
        this.weighting = weighting;
    }

    public void setListOfKeywords(List<String> listOfKeywords) {
        this.listOfKeywords = listOfKeywords;
    }

    public List<String> getListOfKeywords() {
        return listOfKeywords;
    }

    public void addKeyword(String keyword) {
        listOfKeywords.add(keyword);
    }

    public void removeKeyword(String keyword) {
        listOfKeywords.remove(keyword);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EvaluationKeywords that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(listOfKeywords, that.listOfKeywords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), listOfKeywords);
    }
}
