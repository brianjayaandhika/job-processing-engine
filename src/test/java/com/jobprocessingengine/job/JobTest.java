package com.jobprocessingengine.job;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic smoke tests for the {@link Job} model and its associated enums.
 * These tests verify that the data-carrier classes behave correctly.
 */
class JobTest {

    @Test
    void jobShouldHaveUniqueIds() {
        Job job1 = new Job(JobType.PRINT, "payload1");
        Job job2 = new Job(JobType.EMAIL, "payload2");
        assertNotEquals(job1.getId(), job2.getId());
    }

    @Test
    void jobShouldPreserveTypeAndPayload() {
        Job job = new Job(JobType.PRINT, "hello");
        assertEquals(JobType.PRINT, job.getType());
        assertEquals("hello", job.getPayload());
    }

    @Test
    void jobShouldRecordSubmissionTime() {
        Job job = new Job(JobType.EMAIL, "test@example.com");
        assertNotNull(job.getSubmittedAt());
    }

    @Test
    void jobStatusEnumShouldContainExpectedValues() {
        assertNotNull(JobStatus.valueOf("PENDING"));
        assertNotNull(JobStatus.valueOf("QUEUED"));
        assertNotNull(JobStatus.valueOf("IN_PROGRESS"));
        assertNotNull(JobStatus.valueOf("COMPLETED"));
        assertNotNull(JobStatus.valueOf("FAILED"));
    }

    @Test
    void jobTypeEnumShouldContainExpectedValues() {
        assertNotNull(JobType.valueOf("PRINT"));
        assertNotNull(JobType.valueOf("EMAIL"));
    }
}
