package com.ats.entities.criteria;

import com.ats.entities.EvaluationCriterion;
import com.ats.vo.Keyword;

import java.util.List;
import java.util.Objects;

/**
 * Die Klasse wird für die Evaluierung von Schlüsselwörtern im Lebenslauf eines Bewerbers verwendet.
 * Sie ist Teil der Jobausschreibung und wird beim Erstellen einer neuen Ausschreibung erzeugt.
 * Die Klasse enthält eine Liste von Schlüsselwörtern, die für den Bewerber erforderlich sind, um eine Übereinstimmung mit den Anforderungen der Stelle zu erzielen.
 * Diese Schlüsselwörter werden im gesamten Lebenslauf des Bewerbers gesucht und abgeglichen.
 * Auf dieser Grundlage wird dem Bewerber eine Punktzahl zugewiesen, die seine Übereinstimmung mit den geforderten Schlüsselwörtern widerspiegelt.
 * Zusätzlich wird ein Rang zugeordnet, um die Eignung des Bewerbers zu bewerten.
 * Die Gewichtung der Schlüsselwörter ermöglicht eine differenzierte Beurteilung basierend auf der Relevanz der Schlüsselwörter für die Position.
 */
public class EvaluationKeywords extends EvaluationCriterion {

    private List<Keyword> listOfKeywords;

    public EvaluationKeywords(String name, int points, List<Keyword> listOfKeywords, int weighting) {
        super(name, points, weighting);
        if(points < 1 || points > 100 ){
            throw new IllegalArgumentException("Points must be between 1 and 100");
        }
        if(weighting < 1 || weighting > 10){
            throw new IllegalArgumentException("Weighting must be between 1 and 10");
        }
        this.listOfKeywords = listOfKeywords;
    }

    public List<Keyword> getListOfKeywords() {
        return listOfKeywords;
    }

    public void setListOfKeywords(List<Keyword> listOfKeywords) {
        this.listOfKeywords = listOfKeywords;
    }

    public void addKeyword(Keyword keyword) {
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

    @Override
    public String toString() {
        return "EvaluationKeywords{" +
                "name=" + super.getName() +
                ", listOfKeywords=" + listOfKeywords +
                ", weighting=" + super.getWeighting() +
                '}';
    }


}
