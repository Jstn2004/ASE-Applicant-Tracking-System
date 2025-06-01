package com.ats;

import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.evaluationCriterionService.EvaluationAbilitiesFactory;
import com.ats.vo.Ability;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluationAbilitiesFactoryTest {

    @Test
    public void testCreateCriterion_returnsCorrectEvaluationAbilities() {
        EvaluationAbilitiesFactory factory = new EvaluationAbilitiesFactory();

        String name = "Programmiersprachen";
        List<Ability> abilities = List.of(
                new Ability("Java", 5),
                new Ability("Python", 3)
        );
        int weighting = 10;

        EvaluationAbilities result = factory.createCriterion(name, abilities, weighting);

        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(abilities, result.getListOfAbilities());
        assertEquals(weighting, result.getWeighting());
        assertEquals(1, result.getPoints());
    }
}