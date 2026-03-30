package com.jobprocessingengine.store;

import com.jobprocessingengine.job.Job;
import com.jobprocessingengine.job.JobStatus;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-safe, in-memory implementation of {@link JobStore}.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Store jobs and their statuses in {@link ConcurrentHashMap} instances</li>
 *   <li>Provide atomic status updates visible to all concurrent Workers</li>
 * </ul>
 */
public class InMemoryJobStore implements JobStore {

    /** Maps job ID to the corresponding Job object. */
    private final Map<String, Job> jobs = new ConcurrentHashMap<>();

    /** Maps job ID to the current JobStatus. Kept separate for atomic updates. */
    private final Map<String, JobStatus> statuses = new ConcurrentHashMap<>();

    /** {@inheritDoc} */
    @Override
    public void save(Job job) {
        // TODO: Store job and initialise its status to PENDING
        jobs.put(job.getId(), job);
        statuses.put(job.getId(), JobStatus.PENDING);
    }

    /** {@inheritDoc} */
    @Override
    public void updateStatus(String jobId, JobStatus status) {
        // TODO: Atomically update the status; throw NoSuchElementException if unknown
        if (!statuses.containsKey(jobId)) {
            throw new NoSuchElementException("No job found with ID: " + jobId);
        }
        statuses.put(jobId, status);
    }

    /** {@inheritDoc} */
    @Override
    public Optional<Job> findById(String jobId) {
        // TODO: Return the job wrapped in Optional, or Optional.empty()
        return Optional.ofNullable(jobs.get(jobId));
    }

    /** {@inheritDoc} */
    @Override
    public List<Job> findAll() {
        // TODO: Return an unmodifiable snapshot of all stored jobs
        return List.copyOf(jobs.values());
    }

    /** {@inheritDoc} */
    @Override
    public Optional<JobStatus> getStatus(String jobId) {
        // TODO: Return the status wrapped in Optional, or Optional.empty()
        return Optional.ofNullable(statuses.get(jobId));
    }
}
