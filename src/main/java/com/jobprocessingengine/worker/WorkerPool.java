package com.jobprocessingengine.worker;

import com.jobprocessingengine.processor.JobProcessor;
import com.jobprocessingengine.queue.JobQueue;
import com.jobprocessingengine.store.JobStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the lifecycle of a fixed set of {@link Worker} threads.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Create and start a configurable number of Worker threads</li>
 *   <li>Provide a clean shutdown mechanism that stops all workers gracefully</li>
 *   <li>Be the sole owner of worker thread references</li>
 * </ul>
 */
public class WorkerPool {

    /** The workers managed by this pool. */
    private final List<Worker> workers;

    /** The threads backing each worker. */
    private final List<Thread> threads;

    /**
     * Creates a {@code WorkerPool} with the specified number of workers.
     *
     * @param poolSize   the number of concurrent workers to create
     * @param jobQueue   the shared queue all workers will consume from
     * @param jobStore   the shared store all workers will update
     * @param processors the ordered list of processors available to each worker
     */
    public WorkerPool(int poolSize, JobQueue jobQueue, JobStore jobStore, List<JobProcessor> processors) {
        this.workers = new ArrayList<>(poolSize);
        this.threads = new ArrayList<>(poolSize);
        // TODO: Instantiate 'poolSize' Worker objects and their backing threads
    }

    /**
     * Starts all worker threads in the pool.
     *
     * <p>TODO: Start each thread in {@link #threads}.
     */
    public void start() {
        // TODO: Implement pool start logic
        throw new UnsupportedOperationException("WorkerPool.start() not yet implemented");
    }

    /**
     * Signals all workers to stop and waits for their threads to finish.
     *
     * <p>TODO: Call {@link Worker#stop()} on each worker and {@link Thread#join()} each thread.
     */
    public void shutdown() {
        // TODO: Implement graceful shutdown
        throw new UnsupportedOperationException("WorkerPool.shutdown() not yet implemented");
    }

    /**
     * Returns the number of workers in this pool.
     *
     * @return pool size
     */
    public int getPoolSize() {
        return workers.size();
    }
}
