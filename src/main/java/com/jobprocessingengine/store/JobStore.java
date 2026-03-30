package com.jobprocessingengine.store;

import com.jobprocessingengine.job.Job;
import com.jobprocessingengine.job.JobStatus;

import java.util.List;
import java.util.Optional;

/**
 * Contract for the centralised, thread-safe registry of all jobs and their statuses.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Persist job state across the lifecycle of the engine (in-memory)</li>
 *   <li>Be the <em>single source of truth</em> for job status; no other component
 *       may store or mutate job status directly</li>
 *   <li>Guarantee thread-safe reads and writes for concurrent Workers</li>
 * </ul>
 */
public interface JobStore {

    /**
     * Registers a new job in the store with an initial status of
     * {@link JobStatus#PENDING}.
     *
     * @param job the job to register; must not be {@code null}
     */
    void save(Job job);

    /**
     * Updates the status of an existing job.
     *
     * @param jobId  the identifier of the job to update
     * @param status the new status to assign
     * @throws java.util.NoSuchElementException if no job with the given ID exists
     */
    void updateStatus(String jobId, JobStatus status);

    /**
     * Returns the job with the specified ID, if present.
     *
     * @param jobId the identifier to look up
     * @return an {@link Optional} containing the job, or empty if not found
     */
    Optional<Job> findById(String jobId);

    /**
     * Returns all jobs currently registered in the store.
     *
     * @return an unmodifiable snapshot of all jobs
     */
    List<Job> findAll();

    /**
     * Returns the current status of the job with the specified ID.
     *
     * @param jobId the identifier to look up
     * @return an {@link Optional} containing the status, or empty if not found
     */
    Optional<JobStatus> getStatus(String jobId);
}
