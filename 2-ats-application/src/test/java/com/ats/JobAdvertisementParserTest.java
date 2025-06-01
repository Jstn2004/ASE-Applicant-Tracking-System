package com.ats;

import com.ats.entities.JobAdvertisement;
import com.ats.entities.criteria.EvaluationAbilities;
import com.ats.entities.criteria.EvaluationExperience;
import com.ats.entities.criteria.EvaluationKeywords;
import com.ats.jobadvertisementService.JobAdvertisementCreater;
import com.ats.jobadvertisementService.JobAdvertisementParser;
import com.ats.interfaces.observer.JobAdvertisementCreatedObserver;
import com.ats.vo.Ability;
import com.ats.vo.Keyword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JobAdvertisementParserTest {

    private JobAdvertisement expectetJobAd;
    private String jobAdString;

    @BeforeEach
    public void setUp() {
        expectetJobAd = JobAdvertisement.builder()
                .withId("7919cb8f-acd9-49c9-8dfe-82396d2f9e41")
                .withTitel("Softwareentwickler")
                .withDescription("Wir suchen einen erfahrenen Softwareentwickler mit Kenntnissen in Java und Spring Boot.")
                .withCriteria(List.of(
                        new EvaluationAbilities("Programmiersprachen", 1, List.of(
                                new Ability("Java", 5),
                                new Ability("Python", 3)
                        ), 4),
                        new EvaluationExperience("Berufserfahrung", 10, 5, 5),
                        new EvaluationKeywords("Schlüsselwörter", 1, List.of(
                                new Keyword("Spring Boot", 10),
                                new Keyword("Microservices", 10),
                                new Keyword("CI/CD", 20)
                        ), 2)
                ))
                .build();

        jobAdString = expectetJobAd.toString(); // Hinweis: besser wäre JSON!
    }

    @Test
    public void testParseJobAdvertisement_shouldReturnCorrectObject() {
        JobAdvertisementParser parser = new JobAdvertisementParser(
                Logger.getLogger(JobAdvertisementCreater.class.getName())
        );

        JobAdvertisement result = parser.parseJobAdvertisementString(jobAdString);

        assertNotNull(result, "Parser gibt null zurück");
        assertEquals(expectetJobAd.getId(), result.getId());
        assertEquals(expectetJobAd.getTitel(), result.getTitel());
        assertEquals(expectetJobAd.getDescription(), result.getDescription());
        assertEquals(expectetJobAd.getCriteria().size(), result.getCriteria().size());
    }


}