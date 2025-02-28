package com.ats;

import com.ats.jobadvertisementService.JobAdvertisementCreater;
import com.ats.jobadvertisementService.JobAdvertisementDeleter;
import com.ats.repositories.JobAdvertisementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.UUID;
import java.util.logging.Logger;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class JobAdvertisementDeleterTest {

    private String id;
    private JobAdvertisementDeleter jobAdvertisementDeleter;
    @Mock
    private JobAdvertisementRepository jobAdvertisementRepositoryMock;

    @BeforeEach
    public void setUp() {
        jobAdvertisementRepositoryMock = mock(JobAdvertisementRepository.class);
        jobAdvertisementDeleter = new JobAdvertisementDeleter(jobAdvertisementRepositoryMock, Logger.getLogger(JobAdvertisementCreater.class.getName()));
    }

    @Test
    public void testJobAdvertisementgetDeleted() {
        this.id = UUID.randomUUID().toString();
        jobAdvertisementDeleter.deleteJobAdvertisement(this.id);
        verify(jobAdvertisementRepositoryMock).deleteJobAdvertisementById(this.id);
    }

}
