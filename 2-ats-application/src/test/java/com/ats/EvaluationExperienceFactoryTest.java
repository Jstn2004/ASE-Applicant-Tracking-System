package com.ats;

import com.ats.entities.criteria.EvaluationExperience;
import com.ats.evaluationCriterionService.EvaluationExperienceFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluationExperienceFactoryTest {

    @Test
    public void testCreateCriterion_validInput() {
        EvaluationExperienceFactory factory = new EvaluationExperienceFactory();

        String name = "Berufserfahrung";
        List<Integer> experienceData = List.of(3, 5);
        int weighting = 10;

        EvaluationExperience result = factory.createCriterion(name, experienceData, weighting);

        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(3, result.getExperienceInYears());
        assertEquals(weighting, result.getWeighting());
    }

    @Test
    public void testCreateCriterion_invalidListTooShort() {
        EvaluationExperienceFactory factory = new EvaluationExperienceFactory();

        List<Integer> invalidList = List.of(3);

        assertThrows(IndexOutOfBoundsException.class, () ->
                factory.createCriterion("Fehler", invalidList, 5));
    }

    @Test
    public void testCreateCriterion_invalidListType() {
        EvaluationExperienceFactory factory = new EvaluationExperienceFactory();

        List<String> invalidList = List.of("Drei", "Fünf");

        assertThrows(ClassCastException.class, () ->
                factory.createCriterion("Fehler", (List<?>) invalidList, 5));
    }
}