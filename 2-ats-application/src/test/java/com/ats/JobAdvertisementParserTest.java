package com.ats;

import com.ats.entities.JobAdvertisement;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.jobadvertisementService.JobAdvertisementCreater;
import com.ats.jobadvertisementService.JobAdvertisementParser;
import com.ats.vo.Ability;
import com.ats.vo.Keyword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JobAdvertisementParserTest {

    private JobAdvertisementParser jobAdvertisementParser;
    private JobAdvertisement expectetjobAdvertisement;

    @BeforeEach
    public void setUp() {
        expectetjobAdvertisement = new JobAdvertisement(
                "7919cb8f-acd9-49c9-8dfe-82396d2f9e41",
                "Softwareentwickler",
                "Wir suchen einen erfahrenen Softwareentwickler mit Kenntnissen in Java und Spring Boot.",
                List.of(
                        new EvaluationAbilities("Programmiersprachen", 1, List.of(new Ability("Java", 5), new Ability("Python", 3)), 4),
                        new EvaluationExperience("Berufserfahrung", 10, 5, 5),
                        new EvaluationKeywords("Schlüsselwörter", 1, List.of(new Keyword("Spring Boot", 10), new Keyword("Microservices", 10), new Keyword("CI/CD", 20)), 2)
                )
        );
        jobAdvertisementParser = new JobAdvertisementParser( Logger.getLogger(JobAdvertisementCreater.class.getName()));

    }

    @Test
    public void testParseJobAdvertisement() {
        String jobAdvertisementString = expectetjobAdvertisement.toString();
        JobAdvertisement result = jobAdvertisementParser.parseJobAdvertisementString(jobAdvertisementString);

        assertNotNull(result, "Parser gibt null zurück");
        assertEquals(expectetjobAdvertisement.hashCode(), result.hashCode());
        assertEquals(expectetjobAdvertisement.getId(), result.getId());
        assertEquals(expectetjobAdvertisement.getTitel(), result.getTitel());
        assertEquals(expectetjobAdvertisement.getDescription(), result.getDescription());

    }

}
