package com.ats;

import com.ats.entities.EvaluationCriterion;
import com.ats.entities.JobAdvertisement;
import com.ats.interfaces.observer.JobAdvertisementCreatedObserver;
import com.ats.jobadvertisementService.JobAdvertisementCreater;
import com.ats.repositories.JobAdvertisementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JobAdvertisementCreaterTest {

    private JobAdvertisementCreater jobAdvertisementCreater;
    private String title;
    private String description;

    private JobAdvertisementRepository jobAdvertisementRepositoryMock;
    private JobAdvertisementCreatedObserver observerMock;

    @BeforeEach
    public void setUp() {
        this.title = "Software Engineer";
        this.description = "Develop software applications";

        jobAdvertisementRepositoryMock = mock(JobAdvertisementRepository.class);
        observerMock = mock(JobAdvertisementCreatedObserver.class);

        jobAdvertisementCreater = new JobAdvertisementCreater(
                jobAdvertisementRepositoryMock,
                Logger.getLogger(JobAdvertisementCreater.class.getName()),
                List.of(observerMock)
        );
    }

    @Test
    public void testCreateNewJobAdvertisement() {
        List<EvaluationCriterion> criteria = List.of(new EvaluationCriterion("Experience", 50, 1));
        jobAdvertisementCreater.createNewJobAdvertisement(title, description, criteria);
        verify(jobAdvertisementRepositoryMock).saveJobAdvertisement(any(JobAdvertisement.class));
    }

    @Test
    public void testJobAdvertisementHasId() {
        List<EvaluationCriterion> criteria = new ArrayList<>();
        jobAdvertisementCreater.createNewJobAdvertisement(title, description, criteria);

        ArgumentCaptor<JobAdvertisement> argumentCaptor = ArgumentCaptor.forClass(JobAdvertisement.class);
        verify(jobAdvertisementRepositoryMock).saveJobAdvertisement(argumentCaptor.capture());

        JobAdvertisement capturedJobAdvertisement = argumentCaptor.getValue();
        assertNotNull(capturedJobAdvertisement.getId());
        assertFalse(capturedJobAdvertisement.getId().isEmpty());
    }

    @Test
    public void testSavedJobAdvertisementContainsCorrectData() {
        List<EvaluationCriterion> criteria = List.of(new EvaluationCriterion("Experience", 80, 2));
        jobAdvertisementCreater.createNewJobAdvertisement(title, description, criteria);

        ArgumentCaptor<JobAdvertisement> captor = ArgumentCaptor.forClass(JobAdvertisement.class);
        verify(jobAdvertisementRepositoryMock).saveJobAdvertisement(captor.capture());

        JobAdvertisement jobAd = captor.getValue();

        assertEquals(title, jobAd.getTitel());
        assertEquals(description, jobAd.getDescription());
        assertEquals(1, jobAd.getCriteria().size());
        assertEquals("Experience", jobAd.getCriteria().get(0).getName());
    }

    @Test
    public void testObserverIsNotified() {
        List<EvaluationCriterion> criteria = List.of(new EvaluationCriterion("Skills", 100, 2));
        jobAdvertisementCreater.createNewJobAdvertisement(title, description, criteria);

        // Capture das gesendete JobAd
        ArgumentCaptor<JobAdvertisement> captor = ArgumentCaptor.forClass(JobAdvertisement.class);
        verify(observerMock, times(1)).onJobAdvertisementCreated(captor.capture());

        JobAdvertisement observedAd = captor.getValue();
        assertEquals(title, observedAd.getTitel());
    }
}