package com.jobprocessingengine.worker;

import com.jobprocessingengine.job.Job;
import com.jobprocessingengine.job.JobStatus;
import com.jobprocessingengine.processor.JobProcessor;
import com.jobprocessingengine.processor.JobProcessingException;
import com.jobprocessingengine.queue.JobQueue;
import com.jobprocessingengine.store.JobStore;

import java.util.List;

/**
 * Runnable unit of concurrency that continuously dequeues and processes jobs.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Run on its own thread (implements {@link Runnable})</li>
 *   <li>Dequeue jobs from the shared {@link JobQueue}</li>
 *   <li>Delegate processing to the correct {@link JobProcessor} based on the job type</li>
 *   <li>Update job status in {@link JobStore} before and after processing</li>
 *   <li>Handle {@link JobProcessingException} and mark jobs as
 *       {@link JobStatus#FAILED} accordingly</li>
 * </ul>
 *
 * <p>Workers must not contain any if-else chains for job type discrimination;
 * they iterate over the supplied {@link JobProcessor} list and delegate.
 */
public class Worker implements Runnable {

    /** Unique name identifying this worker (e.g. "worker-1"). */
    private final String name;

    /** Shared queue from which this worker pulls jobs. */
    private final JobQueue jobQueue;

    /** Centralised store used to update job status. */
    private final JobStore jobStore;

    /** Ordered list of processors; the first one that {@link JobProcessor#supports} the job is used. */
    private final List<JobProcessor> processors;

    /** Flag controlling the worker's run loop; set to false to request graceful shutdown. */
    private volatile boolean running;

    /**
     * Creates a new Worker.
     *
     * @param name       a human-readable identifier for this worker
     * @param jobQueue   the shared queue to dequeue jobs from
     * @param jobStore   the shared store to update job statuses in
     * @param processors the ordered list of processors available to this worker
     */
    public Worker(String name, JobQueue jobQueue, JobStore jobStore, List<JobProcessor> processors) {
        this.name = name;
        this.jobQueue = jobQueue;
        this.jobStore = jobStore;
        this.processors = List.copyOf(processors);
        this.running = true;
    }

    /**
     * Main execution loop: dequeues jobs and processes them until {@link #stop()} is called.
     *
     * <p>TODO: Implement the run loop:
     * <ol>
     *   <li>Dequeue a job from {@link #jobQueue}</li>
     *   <li>Update status to {@link JobStatus#IN_PROGRESS} in {@link #jobStore}</li>
     *   <li>Find a supporting processor and invoke {@link JobProcessor#process}</li>
     *   <li>Update status to {@link JobStatus#COMPLETED} or {@link JobStatus#FAILED}</li>
     *   <li>Handle {@link InterruptedException} to exit the loop gracefully</li>
     * </ol>
     */
    @Override
    public void run() {
        // TODO: Implement worker run loop
        throw new UnsupportedOperationException("Worker.run() not yet implemented");
    }

    /**
     * Signals this worker to stop processing after the current job completes.
     */
    public void stop() {
        // TODO: Set running = false and interrupt the thread if blocked on dequeue
        this.running = false;
    }

    /** @return the name of this worker */
    public String getName() {
        return name;
    }

    /** @return {@code true} if this worker is currently active */
    public boolean isRunning() {
        return running;
    }
}
