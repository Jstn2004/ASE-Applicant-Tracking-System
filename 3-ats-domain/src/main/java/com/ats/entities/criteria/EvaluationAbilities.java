package com.ats.entities.criteria;

import com.ats.entities.EvaluationCriterion;
import com.ats.vo.Ability;

import java.util.List;
import java.util.Objects;

/**
 * Die Klasse wird für die Evaluierung und Auswertung von Lebensläufen verwendet.
 * Sie ist ein zentraler Bestandteil der Jobausschreibung und wird beim Erstellen einer neuen Ausschreibung erzeugt.
 * Die Klasse enthält eine Liste von Fähigkeiten, die für den Bewerber erforderlich sind.
 * Diese Fähigkeiten werden mit den Angaben des Bewerbers im Lebenslauf abgeglichen.
 * Auf dieser Grundlage wird dem Bewerber eine Punktzahl zugewiesen, die seine Übereinstimmung mit den geforderten Fähigkeiten widerspiegelt.
 * Zusätzlich wird ein Rang zugeordnet, um die Eignung des Bewerbers zu bewerten.
 * Die Gewichtung der einzelnen Fähigkeiten ermöglicht eine differenzierte Beurteilung basierend auf ihrer Relevanz für die Position.
 */
public class EvaluationAbilities extends EvaluationCriterion {
    private List<Ability> listOfAbilities;

    public EvaluationAbilities(String name, int points, List<Ability> listOfAbilities, int weighting) {
        super(name, points, weighting);
        if (weighting < 1 || weighting > 10) {
            throw new IllegalArgumentException("Weighting must be between 1 and 10");
        }
        this.listOfAbilities = listOfAbilities;
    }

    public void addAbility(Ability ability) {
        listOfAbilities.add(ability);
    }

    public void removeAbility(Ability ability) {
        listOfAbilities.remove(ability);
    }

    public List<Ability> getListOfAbilities() {

        return listOfAbilities;
    }

    public void setListOfAbilities(List<Ability> listOfAbilities) {
        this.listOfAbilities = listOfAbilities;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EvaluationAbilities that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(listOfAbilities, that.listOfAbilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), listOfAbilities);
    }

    @Override
    public String toString() {
        return "EvaluationAbilities{" +
                "name=" + super.getName() +
                ", listOfAbilities=" + listOfAbilities +
                ", weighting=" + super.getWeighting() +
                '}';
    }
}
