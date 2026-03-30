package com.jobprocessingengine.coordinator;

import com.jobprocessingengine.job.Job;
import com.jobprocessingengine.job.JobStatus;
import com.jobprocessingengine.queue.JobQueue;
import com.jobprocessingengine.store.JobStore;
import com.jobprocessingengine.worker.WorkerPool;

/**
 * Central orchestrator of the job processing engine.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Accept job submissions from external callers (e.g., {@link com.jobprocessingengine.main.Main})</li>
 *   <li>Register jobs in {@link JobStore} and enqueue them to {@link JobQueue}</li>
 *   <li>Start and shut down the {@link WorkerPool}</li>
 *   <li>Expose engine lifecycle management (start / shutdown)</li>
 * </ul>
 *
 * <p>This class is the only entry point for submitting work; it must not be
 * bypassed by other components.
 */
public class JobCoordinator {

    /** Queue that holds jobs waiting to be processed. */
    private final JobQueue jobQueue;

    /** Centralised store for job state. */
    private final JobStore jobStore;

    /** Pool of worker threads that consume jobs from the queue. */
    private final WorkerPool workerPool;

    /**
     * Creates a new {@code JobCoordinator}.
     *
     * @param jobQueue   the queue to use for pending jobs
     * @param jobStore   the store to use for job state tracking
     * @param workerPool the pool of workers that will process jobs
     */
    public JobCoordinator(JobQueue jobQueue, JobStore jobStore, WorkerPool workerPool) {
        this.jobQueue = jobQueue;
        this.jobStore = jobStore;
        this.workerPool = workerPool;
    }

    /**
     * Starts the engine by launching all worker threads.
     *
     * <p>TODO: Delegate to {@link WorkerPool#start()}.
     */
    public void start() {
        // TODO: Start the worker pool
        throw new UnsupportedOperationException("JobCoordinator.start() not yet implemented");
    }

    /**
     * Submits a job to the engine.
     *
     * <p>The job is saved to {@link JobStore} with status {@link JobStatus#PENDING}
     * and then placed on the {@link JobQueue} with status {@link JobStatus#QUEUED}.
     *
     * <p>TODO:
     * <ol>
     *   <li>Call {@link JobStore#save(Job)}</li>
     *   <li>Call {@link JobStore#updateStatus(String, JobStatus)} with {@link JobStatus#QUEUED}</li>
     *   <li>Call {@link JobQueue#enqueue(Job)}</li>
     * </ol>
     *
     * @param job the job to submit; must not be {@code null}
     * @throws InterruptedException if the thread is interrupted while waiting to enqueue
     */
    public void submit(Job job) throws InterruptedException {
        // TODO: Persist job state and enqueue it
        throw new UnsupportedOperationException("JobCoordinator.submit() not yet implemented");
    }

    /**
     * Shuts down the engine gracefully.
     *
     * <p>TODO: Delegate to {@link WorkerPool#shutdown()}.
     */
    public void shutdown() {
        // TODO: Stop the worker pool
        throw new UnsupportedOperationException("JobCoordinator.shutdown() not yet implemented");
    }
}
