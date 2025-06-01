package com.ats;

import com.ats.jobadvertisementService.JobAdvertisementCreater;
import com.ats.jobadvertisementService.JobAdvertisementLoader;
import com.ats.repositories.JobAdvertisementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.logging.Logger;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class JobAdvertisementLoaderTest {
    private JobAdvertisementLoader jobAdvertisementLoader;
    @Mock
    private JobAdvertisementRepository jobAdvertisementRepositoryMock;

    @BeforeEach
    public void setUp() {
        jobAdvertisementRepositoryMock = mock(JobAdvertisementRepository.class);
        jobAdvertisementLoader = new JobAdvertisementLoader(jobAdvertisementRepositoryMock, Logger.getLogger(JobAdvertisementCreater.class.getName()));
    }

    @Test
    public void testLoadJobAdvertisement() {
        jobAdvertisementLoader.loadAllJobAdvertisement();
        verify(jobAdvertisementRepositoryMock).loadJobAdvertisement();
    }
}
