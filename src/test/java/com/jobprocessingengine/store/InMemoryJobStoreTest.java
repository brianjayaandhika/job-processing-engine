package com.jobprocessingengine.store;

import com.jobprocessingengine.job.Job;
import com.jobprocessingengine.job.JobStatus;
import com.jobprocessingengine.job.JobType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link InMemoryJobStore}.
 */
class InMemoryJobStoreTest {

    private InMemoryJobStore store;

    @BeforeEach
    void setUp() {
        store = new InMemoryJobStore();
    }

    @Test
    void saveShouldRegisterJobWithPendingStatus() {
        Job job = new Job(JobType.PRINT, "payload");
        store.save(job);

        Optional<JobStatus> status = store.getStatus(job.getId());
        assertTrue(status.isPresent());
        assertEquals(JobStatus.PENDING, status.get());
    }

    @Test
    void findByIdShouldReturnSavedJob() {
        Job job = new Job(JobType.EMAIL, "test@example.com");
        store.save(job);

        Optional<Job> found = store.findById(job.getId());
        assertTrue(found.isPresent());
        assertEquals(job.getId(), found.get().getId());
    }

    @Test
    void findByIdShouldReturnEmptyForUnknownId() {
        Optional<Job> found = store.findById("nonexistent-id");
        assertFalse(found.isPresent());
    }

    @Test
    void updateStatusShouldChangeJobStatus() {
        Job job = new Job(JobType.PRINT, "payload");
        store.save(job);
        store.updateStatus(job.getId(), JobStatus.COMPLETED);

        Optional<JobStatus> status = store.getStatus(job.getId());
        assertTrue(status.isPresent());
        assertEquals(JobStatus.COMPLETED, status.get());
    }

    @Test
    void updateStatusShouldThrowForUnknownId() {
        assertThrows(NoSuchElementException.class,
                () -> store.updateStatus("unknown-id", JobStatus.FAILED));
    }

    @Test
    void findAllShouldReturnAllSavedJobs() {
        store.save(new Job(JobType.PRINT, "p1"));
        store.save(new Job(JobType.EMAIL, "p2"));

        assertEquals(2, store.findAll().size());
    }
}
