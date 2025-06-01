package com.ats;

import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.evaluationCriterionService.EvaluationKeywordsFactory;
import com.ats.vo.Keyword;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluationKeywordsFactoryTest {

    @Test
    public void testCreateCriterion_validInput() {
        EvaluationKeywordsFactory factory = new EvaluationKeywordsFactory();

        String name = "Technologien";
        List<Keyword> keywords = List.of(
                new Keyword("Spring Boot", 10),
                new Keyword("Docker", 8)
        );
        int weighting = 9;

        EvaluationKeywords result = factory.createCriterion(name, keywords, weighting);

        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(keywords, result.getListOfKeywords());
        assertEquals(weighting, result.getWeighting());
    }

    @Test
    public void testCreateCriterion_invalidInput_wrongType() {
        EvaluationKeywordsFactory factory = new EvaluationKeywordsFactory();
        List<String> invalidList = List.of("Spring", "Docker");

        assertThrows(ClassCastException.class, () ->
                factory.createCriterion("Fehler", (List<?>) invalidList, 10));
    }
}