package com.ats;

import com.ats.entities.EvaluationCriterion;
import com.ats.entities.JobAdvertisement;
import com.ats.jobadvertisementService.JobAdvertisementCreater;
import com.ats.repositories.JobAdvertisementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class JobAdvertisementCreaterTest {
    private JobAdvertisementCreater jobAdvertisementCreater;
    private String title;
    private String description;

    @Mock
    private JobAdvertisementRepository jobAdvertisementRepositoryMock;

    @BeforeEach
    public void setUp() {
        this.title = "Software Engineer";
        this.description = "Develop software applications";
        jobAdvertisementRepositoryMock = mock(JobAdvertisementRepository.class);
        jobAdvertisementCreater = new JobAdvertisementCreater(jobAdvertisementRepositoryMock, Logger.getLogger(JobAdvertisementCreater.class.getName()));
    }

    @Test
    public void testCreateNewJobAdvertisement() {
        Iterable<EvaluationCriterion> criteria = List.of(new EvaluationCriterion("Experience", 50, 1));
        jobAdvertisementCreater.createNewJobAdvertisement(title, description, criteria);
        verify(jobAdvertisementRepositoryMock).saveJobAdvertisement(any(JobAdvertisement.class));
    }

    @Test
    public void testJobAdvertisementHasId() {
        Iterable<EvaluationCriterion> criteria = new ArrayList<>();
        jobAdvertisementCreater.createNewJobAdvertisement(title, description, criteria);

        ArgumentCaptor<JobAdvertisement> argumentCaptor = ArgumentCaptor.forClass(JobAdvertisement.class);
        verify(jobAdvertisementRepositoryMock).saveJobAdvertisement(argumentCaptor.capture());

        JobAdvertisement capturedJobAdvertisement = argumentCaptor.getValue();
        assertNotNull(capturedJobAdvertisement.getId());
        assertFalse(capturedJobAdvertisement.getId().isEmpty());
    }
}
