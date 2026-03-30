package com.jobprocessingengine.queue;

import com.jobprocessingengine.job.Job;

/**
 * Abstraction over the underlying queue mechanism used to hold pending jobs.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Accept jobs submitted by the coordinator</li>
 *   <li>Provide jobs to workers on demand</li>
 *   <li>Hide the concrete queue implementation (e.g., {@link java.util.concurrent.BlockingQueue})
 *       from the rest of the system</li>
 * </ul>
 *
 * <p>Implementations must be thread-safe.
 */
public interface JobQueue {

    /**
     * Adds a job to the queue.
     * Blocks if the queue has a bounded capacity and is currently full.
     *
     * @param job the job to enqueue
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    void enqueue(Job job) throws InterruptedException;

    /**
     * Retrieves and removes the next job from the queue.
     * Blocks until a job is available.
     *
     * @return the next job
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    Job dequeue() throws InterruptedException;

    /**
     * Returns the number of jobs currently waiting in the queue.
     *
     * @return current queue size
     */
    int size();

    /**
     * Returns {@code true} if the queue contains no jobs.
     *
     * @return {@code true} if empty
     */
    boolean isEmpty();
}
