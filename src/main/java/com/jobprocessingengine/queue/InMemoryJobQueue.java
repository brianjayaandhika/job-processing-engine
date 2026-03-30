package com.jobprocessingengine.queue;

import com.jobprocessingengine.job.Job;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * In-memory {@link JobQueue} implementation backed by a {@link LinkedBlockingQueue}.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Provide thread-safe enqueue and dequeue operations</li>
 *   <li>Encapsulate the {@link BlockingQueue} so it is not exposed directly to the rest
 *       of the system</li>
 * </ul>
 */
public class InMemoryJobQueue implements JobQueue {

    /** Underlying thread-safe queue; capacity is unbounded by default. */
    private final BlockingQueue<Job> queue;

    /** Creates an {@code InMemoryJobQueue} with unbounded capacity. */
    public InMemoryJobQueue() {
        this.queue = new LinkedBlockingQueue<>();
    }

    /**
     * Creates an {@code InMemoryJobQueue} with the specified maximum capacity.
     *
     * @param capacity the maximum number of jobs the queue can hold
     */
    public InMemoryJobQueue(int capacity) {
        this.queue = new LinkedBlockingQueue<>(capacity);
    }

    /** {@inheritDoc} */
    @Override
    public void enqueue(Job job) throws InterruptedException {
        // TODO: Optionally update job status to QUEUED via JobStore before putting
        queue.put(job);
    }

    /** {@inheritDoc} */
    @Override
    public Job dequeue() throws InterruptedException {
        // TODO: Block until a job is available and return it
        return queue.take();
    }

    /** {@inheritDoc} */
    @Override
    public int size() {
        return queue.size();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
